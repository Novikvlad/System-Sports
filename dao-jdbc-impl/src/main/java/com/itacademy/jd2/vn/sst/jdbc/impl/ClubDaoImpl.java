package com.itacademy.jd2.vn.sst.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.IClubDao;
import com.itacademy.jd2.vn.sst.dao.api.IStadiumDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.filter.ClubFilter;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Club;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Stadium;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.PreparedStatementAction;

@Repository
public class ClubDaoImpl extends AbstractDaoImpl<IClub, Integer> implements IClubDao {

	private IStadiumDao stadiumDao;

	public ClubDaoImpl(final IStadiumDao stadiumDao) {
		super();
		this.stadiumDao = stadiumDao;

	}

	@Override
	public IClub createEntity() {
		return new Club();
	}

	@Override
	public void insert(final IClub entity) {

		executeStatement(new PreparedStatementAction<IClub>(String.format(
				"insert into %s(name, based, stadium_id, created, updated) values(?,?,?,?,?)", getTableName()), true) {

			@Override
			public IClub doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getBased(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getStadium().getId());
				pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);
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
	public void update(final IClub entity) {
		executeStatement(new PreparedStatementAction<IClub>(String.format(
				"update set %s name=?, based=?, stadium_id=?, created=?, updated=? where id=?", Types.TIMESTAMP)) {

			@Override
			public IClub doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getBased(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getStadium().getId());
				pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected IClub parseRow(ResultSet resultSet, Set<String> columns) throws SQLException {

		final IClub entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setBased(resultSet.getTimestamp("based"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IStadium stadium = new Stadium();
		stadium.setId((Integer) resultSet.getObject("stadium_id"));
		entity.setStadium(stadium);
		return entity;
	}

	@Override
	public IClub getFullInfo(final Integer id) {

		final IClub club = get(id);
		if (club.getStadium() != null) {
			club.setStadium(stadiumDao.get(club.getStadium().getId()));
		}
		return null;
	}

	@Override
	public List<IClub> find(final ClubFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendPaging(filter, sql);
		appendSort(filter, sql);

		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final ClubFilter filter) {
		return executeCountQuery("");

	}

	@Override
	protected String getTableName() {
		return "club";
	}

}
