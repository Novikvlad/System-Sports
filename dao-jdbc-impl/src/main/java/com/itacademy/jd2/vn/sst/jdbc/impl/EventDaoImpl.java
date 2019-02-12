package com.itacademy.jd2.vn.sst.jdbc.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.IEventDao;
import com.itacademy.jd2.vn.sst.dao.api.ISeasonTicketDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.EventFilter;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Event;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Stadium;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.SQLExecutionException;

@Repository
public class EventDaoImpl extends AbstractDaoImpl<IEvent, Integer> implements IEventDao {

	private ISeasonTicketDao seasonTicketDao;

	@Autowired
	public EventDaoImpl(ISeasonTicketDao seasonTicketDao) {
		super();
		this.seasonTicketDao = seasonTicketDao;
	}

	@Override
	public IEvent createEntity() {
		return new Event();
	}

	@Override
	public void insert(final IEvent entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("insert into &s (name, date, stadium_id, created, updated) values(?, ?, ?, ?, ?)",
								getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {

				pStmt.setString(1, entity.getName());
				pStmt.setDate(2, (Date) entity.getDate());
				pStmt.setInt(3, entity.getStadium().getId());
				pStmt.setObject(4, entity.getCreated());
				pStmt.setObject(5, entity.getUpdated());

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();
				entity.getId();
				updateSeasonTicke(entity, c);
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}
		} catch (final Exception e) {

			throw new SQLExecutionException(e);
		}

	}

	@Override
	public void update(final IEvent entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String
						.format("update set &s (name=?, date=?, stadium_id=?, updated=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setDate(2, (Date) entity.getDate());
				pStmt.setInt(3, entity.getStadium().getId());
				pStmt.setObject(4, entity.getUpdated());
				pStmt.setInt(5, entity.getId());
				pStmt.executeUpdate();

				updateSeasonTicke(entity, c);
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}
		} catch (final Exception e) {

			throw new SQLExecutionException(e);
		}

	}

	@Override
	protected IEvent parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {

		final IEvent entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setDate(resultSet.getDate("date"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer stadiunId = (Integer) resultSet.getObject("stadium_id");
		if (stadiunId != null) {
			final Stadium stadium = new Stadium();
			stadium.setId(stadiunId);
			if (columns.contains("stadium_name")) {
				stadium.setName(resultSet.getString("stadium_name"));
			}
			entity.setStadium(stadium);
		}

		return entity;
	}

	@Override
	public void deleteAll() {
		try (Connection c = getConnection();
				PreparedStatement deleteSeasonTickeRefsStmt = c.prepareStatement("delete from season_ticket_2_event");
				PreparedStatement deleteAllStmt = c.prepareStatement("delete from " + getTableName());) {
			c.setAutoCommit(false);
			try {
				deleteSeasonTickeRefsStmt.executeUpdate();
				deleteAllStmt.executeUpdate();

				deleteSeasonTickeRefsStmt.close();
				deleteAllStmt.close();

				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}

	}

	@Override
	public List<IEvent> find(final EventFilter filter) {

		final StringBuilder sql;
		if (filter.getFetchStadium()) {
			sql = new StringBuilder(
					String.format("select event.*, stadium..name as stadium_name from %s", getTableName()));

		} else {
			sql = new StringBuilder(String.format("select model.* from &s", getTableName()));
		}
		appendJOINs(sql, filter);
		appendWHEREs(sql, filter);
		appendSort(filter, sql);
		appendPaging(filter, sql);

		return executeFindQueryWithCustomSelect(sql.toString());
	}

	@Override
	public long getCount(final EventFilter filter) {

		final StringBuilder sql = new StringBuilder("");
		appendJOINs(sql, filter);
		appendWHEREs(sql, filter);

		return executeCountQuery(sql.toString());
	}

	@Override
	protected String getTableName() {
		return "event";
	}

	@Override
	public IEvent getFullInfo(final Integer id) {

		final IEvent event = get(id);
		final Set<ISeasonTicket> seasonTickets = seasonTicketDao.getByEvent(id);
		event.setSeasonTicket(seasonTickets);

		return event;
	}

	private void updateSeasonTicke(final IEvent entity, final Connection c) throws SQLException {
		final PreparedStatement deleteStmt = c.prepareStatement("delete from season_tickets_2_event whele event_id=?");

		deleteStmt.setInt(1, entity.getId());
		deleteStmt.executeUpdate();
		deleteStmt.close();

		if (entity.getSeasonTicket().isEmpty()) {
			return;
		}
		final PreparedStatement pStmt = c
				.prepareStatement("insert into season_tickets_2_event (season_tickets_id, event_id) valies(?,?)");

		for (final ISeasonTicket e : entity.getSeasonTicket()) {

			pStmt.setInt(1, entity.getId());
			pStmt.setInt(2, e.getId());
			pStmt.addBatch();

		}
		pStmt.executeBatch();
		pStmt.close();

	}

	private void appendJOINs(StringBuilder sb, EventFilter filter) {
		if (filter.getFetchStadium()) {
			sb.append("join stadium on(stadium.id=event.stadium_id)");
		}

	}

	private void appendWHEREs(StringBuilder sb, EventFilter filter) {

	}
}
