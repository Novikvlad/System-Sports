package com.itacademy.jd2.vn.sst.jdbc.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.ISeasonTicketDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.SeasonTicketFilter;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.SeasonTicket;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.PreparedStatementAction;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.StatementAction;

@Repository
public class SeasonTicketDaoImpl extends AbstractDaoImpl<ISeasonTicket, Integer> implements ISeasonTicketDao {

	@Override
	public ISeasonTicket createEntity() {
		return new SeasonTicket();
	}

	@Override
	public void insert(final ISeasonTicket entity) {
		executeStatement(new PreparedStatementAction<ISeasonTicket>(String.format(
				"insert into &s(name, sector, row, seat, price, date, presence, created, updated)values(?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public ISeasonTicket doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getSector());
				pStmt.setString(3, entity.getRow());
				pStmt.setString(4, entity.getSeat());
				pStmt.setBigDecimal(5, entity.getPrice());
				pStmt.setDate(6, (java.sql.Date) entity.getDate());
				pStmt.setBoolean(7, entity.getPresence());
				pStmt.setObject(8, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(9, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();

				entity.setId(id);
				return entity;
			}
		});
	}

	@Override
	public void update(final ISeasonTicket entity) {
		executeStatement(new PreparedStatementAction<ISeasonTicket>(String.format(
				"update  %s set name=?, sector=?, row=?, seat=?, price=?, date=?, presence=?, updated=? where id=?)",
				getTableName()), true) {

			@Override
			public ISeasonTicket doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getSector());
				pStmt.setString(3, entity.getRow());
				pStmt.setString(4, entity.getSeat());
				pStmt.setBigDecimal(5, entity.getPrice());
				pStmt.setDate(6, (java.sql.Date) entity.getDate());
				pStmt.setBoolean(7, entity.getPresence());
				pStmt.setObject(8, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(9, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected ISeasonTicket parseRow(ResultSet resultSet) throws SQLException {
		final ISeasonTicket entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setSector(resultSet.getString("sector"));
		entity.setRow(resultSet.getString("row"));
		entity.setSeat(resultSet.getString("seat"));
		entity.setPrice(resultSet.getBigDecimal("price"));
		entity.setDate(resultSet.getDate("date"));
		entity.setPresence(resultSet.getBoolean("presence"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public Set<ISeasonTicket> getByEvent(final Integer id) {

		return executeStatement(new StatementAction<Set<ISeasonTicket>>() {

			@Override
			public Set<ISeasonTicket> doWithStatement(final Statement statement) throws SQLException {

				// @formatter:off
				statement.executeQuery(String.format("select * from %s e "
						+ "inner join season_ticket_2_event  m2e on s.id=e2s.season_ticket_id " + "where e2s.event_id=%s",
						getTableName(), id));
				// @formatter:on
				final ResultSet resultSet = statement.getResultSet();

				final Set<ISeasonTicket> result = new HashSet<ISeasonTicket>();
				boolean hasNext = resultSet.next();
				while (hasNext) {
					result.add(parseRow(resultSet));
					hasNext = resultSet.next();
				}
				resultSet.close();
				return result;
			}
		});
	}

	@Override
	public List<ISeasonTicket> find(final SeasonTicketFilter filter) {
		final StringBuilder sqlTitle = new StringBuilder("");
		appendSort(filter, sqlTitle);
		appendPaging(filter, sqlTitle);
		return executeFindQuery(sqlTitle.toString());
	}

	@Override
	public long getCount(final SeasonTicketFilter filter) {
		return executeCountQuery("");
	}

	@Override
	protected String getTableName() {
		return "season_ticket";
	}

}
