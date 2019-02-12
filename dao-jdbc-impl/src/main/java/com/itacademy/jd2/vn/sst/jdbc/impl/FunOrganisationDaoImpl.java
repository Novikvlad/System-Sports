package com.itacademy.jd2.vn.sst.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.ICityDao;
import com.itacademy.jd2.vn.sst.dao.api.IClubDao;
import com.itacademy.jd2.vn.sst.dao.api.IFunOrganisationDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.filter.FunOrganisationFilter;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.City;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Club;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.FunOrganisation;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.PreparedStatementAction;

@Repository
public class FunOrganisationDaoImpl extends AbstractDaoImpl<IFunOrganisation, Integer> implements IFunOrganisationDao {
	private ICityDao cityDao;
	private IClubDao clubDao;

	@Autowired
	public FunOrganisationDaoImpl(ICityDao cityDao, IClubDao clubDao) {

		super();
		this.cityDao = cityDao;
		this.clubDao = clubDao;

	}

	@Override
	public IFunOrganisation createEntity() {
		return new FunOrganisation();
	}

	@Override
	public void insert(final IFunOrganisation entity) {

		executeStatement(new PreparedStatementAction<IFunOrganisation>(String.format(
				"insert into %s(name, club_id, city_id 	, deposit,  created, updated) values(?,?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IFunOrganisation doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getClub().getId());
				pStmt.setInt(3, entity.getCity().getId());
				pStmt.setBigDecimal(4, entity.getDeposit());
				pStmt.setObject(5, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(IFunOrganisation entity) {
		executeStatement(new PreparedStatementAction<IFunOrganisation>(String.format(
				"update set %s name=?, club_id=?, city_id=?, deposit=?,  updated=? where id=?", getTableName())) {

			@Override
			public IFunOrganisation doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getClub().getId());
				pStmt.setInt(3, entity.getCity().getId());
				pStmt.setBigDecimal(4, entity.getDeposit());
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setObject(6, entity.getId());

				pStmt.executeUpdate();

				return entity;
			}
		});
	}

	@Override
	protected IFunOrganisation parseRow(ResultSet resultSet) throws SQLException {
		final IFunOrganisation entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setDeposit(resultSet.getBigDecimal("deposit"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IClub club = new Club();
		club.setId((Integer) resultSet.getObject("club_id"));
		entity.setClub(club);

		final ICity city = new City();
		city.setId((Integer) resultSet.getObject("city_id"));
		entity.setCity(city);

		return entity;
	}

	@Override
	public IFunOrganisation getFulInfo(final Integer id) {

		final IFunOrganisation funOrganisation = get(id);
		if (funOrganisation.getClub() != null) {
			funOrganisation.setClub(clubDao.get(funOrganisation.getClub().getId()));
		}
		if (funOrganisation.getCity() != null) {
			funOrganisation.setCity(cityDao.get(funOrganisation.getCity().getId()));
		}

		return null;
	}

	@Override
	public List<IFunOrganisation> find(final FunOrganisationFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendPaging(filter, sql);
		appendSort(filter, sql);

		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final FunOrganisationFilter filter) {
		return executeCountQuery("");
	}

	@Override
	protected String getTableName() {
		return "fun_organisation";
	}

}
