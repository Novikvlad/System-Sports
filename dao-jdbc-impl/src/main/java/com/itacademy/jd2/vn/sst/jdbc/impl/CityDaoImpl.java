package com.itacademy.jd2.vn.sst.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.ICityDao;
import com.itacademy.jd2.vn.sst.dao.api.ICountryDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.filter.CityFilter;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.City;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Country;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.PreparedStatementAction;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<ICity, Integer> implements ICityDao {

	private ICountryDao countryDao;

	@Autowired
	public CityDaoImpl(ICountryDao countryDao) {
		super();
		this.countryDao = countryDao;

	}

	@Override
	public ICity createEntity() {
		return new City();
	}

	@Override
	public void insert(final ICity entity) {
		executeStatement(new PreparedStatementAction<ICity>(
				String.format("insert into %s (name, country_id, created, updated) values(?,?,?,?)", getTableName()),
				true) {

			@Override
			public ICity doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getCountry().getId());
				pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
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
	public void update(final ICity entity) {
		executeStatement(new PreparedStatementAction<ICity>(
				String.format("update  %s set name=?, country_id=?,  updated=? where id=?", getTableName())) {

			@Override
			public ICity doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getCountry().getId());
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getId());
				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected ICity parseRow(final ResultSet resultSet) throws SQLException {

		final ICity entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final ICountry country = new Country();
		country.setId((Integer) resultSet.getObject("country_id"));
		entity.setCountry(country);
		return entity;
	}

	@Override
	public ICity getFullInfo(final Integer id) {
		final ICity city = get(id);
		if (city.getCountry() != null) {
			city.setCountry(countryDao.get(city.getCountry().getId()));
		}

		return city;
	}

	@Override
	public List<ICity> find(final CityFilter filter) {

		final StringBuilder sql = new StringBuilder("");
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final CityFilter filter) {
		return executeCountQuery("");
	}

	@Override
	protected String getTableName() {
		return "city";
	}

}
