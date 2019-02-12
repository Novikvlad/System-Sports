package com.itacademy.jd2.vn.sst.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.ICountryDao;
import com.itacademy.jd2.vn.sst.dao.api.IRegionDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.dao.api.filter.CountryFilter;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Country;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Region;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.PreparedStatementAction;

@Repository
public class CountryDaoImpl extends AbstractDaoImpl<ICountry, Integer> implements ICountryDao {

	private IRegionDao regionDao;

	public CountryDaoImpl(IRegionDao regionDao) {
		super();
		this.regionDao = regionDao;
	}

	@Override
	public ICountry createEntity() {
		return new Country();
	}

	@Override
	public void insert(final ICountry entity) {
		executeStatement(new PreparedStatementAction<ICountry>(
				String.format("insert into %s (name, region_id, created, updated) values(?,?,?,?)", getTableName()),
				true) {

			@Override
			public ICountry doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getRegion().getId());
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
	public void update(final ICountry entity) {
		executeStatement(new PreparedStatementAction<ICountry>(
				String.format("update %s set name=?, region_id=?, created=?, updated=? where id=?", getTableName())) {

			@Override
			public ICountry doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getRegion().getId());
				pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getId());
				
				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected ICountry parseRow(final ResultSet resultSet) throws SQLException {
		final ICountry entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IRegion region = new Region();
		region.setId((Integer) resultSet.getObject("region_id"));
		entity.setRegion(region);
		;
		return entity;
	}

	@Override
	public ICountry getFullInfo(final Integer id) {
		final ICountry country = get(id);
		if (country.getRegion() != null) {
			country.setRegion(regionDao.get(country.getRegion().getId()));
		}
		return country;
	}

	@Override
	public List<ICountry> find(final CountryFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final CountryFilter filter) {
		return executeCountQuery("");
	}

	@Override
	protected String getTableName() {
		return "country";
	}
}
