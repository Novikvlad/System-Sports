package com.itacademy.jd2.vn.sst.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.IEventDao;
import com.itacademy.jd2.vn.sst.dao.api.ITicketDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.TicketFilter;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Event;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Ticket;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.PreparedStatementAction;

@Repository
public class TicketDaoImpl extends AbstractDaoImpl<ITicket, Integer> implements ITicketDao {

	private IEventDao eventdao;

	public TicketDaoImpl(IEventDao eventdao) {
		super();
		this.eventdao = eventdao;

	}

	@Override
	public ITicket createEntity() {
		return new Ticket();
	}

	@Override
	public void insert(final ITicket entity) {
		executeStatement(new PreparedStatementAction<ITicket>(String.format(
				"insert into &s(name, sector, raw, seat, price, presence, event_id, created, updated) values(?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public ITicket doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getSector());
				pStmt.setString(3, entity.getRow());
				pStmt.setString(4, entity.getSeat());
				pStmt.setBigDecimal(5, entity.getPrice());
				pStmt.setBoolean(6, entity.getPresence());
				pStmt.setObject(7, entity.getEvent().getId());
				pStmt.setObject(8, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(8, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(final ITicket entity) {
		executeStatement(new PreparedStatementAction<ITicket>(String.format(
				"update set &s (name=?, sector=?, raw=?, seat=?, price=?, presence=?, event_id=?, updated=? where id=?",
				getTableName())) {

			@Override
			public ITicket doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getSector());
				pStmt.setString(3, entity.getRow());
				pStmt.setString(4, entity.getSeat());
				pStmt.setBigDecimal(5, entity.getPrice());
				pStmt.setBoolean(6, entity.getPresence());
				pStmt.setObject(7, entity.getEvent().getId());
				pStmt.setObject(8, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(9, entity.getId());
				pStmt.executeUpdate();

				return entity;
			}
		});

	}

	@Override
	protected ITicket parseRow(ResultSet resultSet, Set<String> columns) throws SQLException {

		final ITicket entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setSector(resultSet.getString("sector"));
		entity.setRow(resultSet.getString("row"));
		entity.setSeat(resultSet.getString("seat"));
		entity.setPrice(resultSet.getBigDecimal("price"));
		entity.setPresence(resultSet.getString("presence"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IEvent event = new Event();
		event.setId((Integer) resultSet.getObject("event_id"));
		entity.setEvent(event);

		return super.parseRow(resultSet, columns);
	}

	@Override
	public ITicket getFullInfo(final Integer id) {
		final ITicket ticket = get(id);
		if (ticket.getEvent() != null) {
			ticket.setEvent(eventdao.get(ticket.getEvent().getId()));
		}

		return ticket;
	}

	@Override
	public List<ITicket> find(final TicketFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendPaging(filter, sql);
		appendSort(filter, sql);

		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final TicketFilter filter) {
		// TODO Auto-generated method stub
		return executeCountQuery("");
	}

	@Override
	protected String getTableName() {
		return "ticket";
	}

}
