package com.itacademy.jd2.vn.sst.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.ICityDao;
import com.itacademy.jd2.vn.sst.dao.api.IStadiumDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.filter.StadiumFilter;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Stadium;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.City;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.PreparedStatementAction;

@Repository
public class StadiumDaoImpl extends AbstractDaoImpl<IStadium, Integer> implements IStadiumDao {

	private ICityDao cityDao;

	@Autowired
	public StadiumDaoImpl(ICityDao cityDao) {
		super();
		this.cityDao = cityDao;
	}

	@Override
	public IStadium createEntity() {
		return new Stadium();
	}

	@Override
	public void insert(final IStadium entity) {

		executeStatement(new PreparedStatementAction<IStadium>(String.format(
				"insert into %s(name, capacity, address, based, city_id, created, updated) values(?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IStadium doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getCapacity());
				pStmt.setString(3, entity.getAddress());
				pStmt.setObject(4, entity.getBased(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getCity().getId());
				pStmt.setObject(6, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(7, entity.getUpdated(), Types.TIMESTAMP);
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
	public void update(final IStadium entity) {
		executeStatement(new PreparedStatementAction<IStadium>(
				String.format("update  %s set name=?, capacity=?, address=?, based=?, city_id=?, updated=? where id=?",
						getTableName())) {

			@Override
			public IStadium doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getCapacity());
				pStmt.setString(3, entity.getAddress());
				pStmt.setObject(4, entity.getBased(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getCity().getId());
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setObject(7, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected IStadium parseRow(final ResultSet resultSet) throws SQLException {

		final IStadium entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCapacity(resultSet.getInt("capacity"));
		entity.setAddress(resultSet.getString("address"));
		entity.setBased(resultSet.getTimestamp("based"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final ICity city = new City();
		city.setId((Integer) resultSet.getObject("city_id"));
		entity.setCity(city);
		return entity;
	}

	@Override
	public IStadium getFullInfo(final Integer id) {
		final IStadium stadium = get(id);
		if (stadium.getCity() != null) {
			stadium.setCity(cityDao.get(stadium.getCity().getId()));
		}
		return stadium;
	}

	@Override
	public List<IStadium> find(final StadiumFilter filter) {

		final StringBuilder sql = new StringBuilder("");
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final StadiumFilter filter) {
		return executeCountQuery("");
	}

	@Override
	protected String getTableName() {
		return "stadium";
	}

}
