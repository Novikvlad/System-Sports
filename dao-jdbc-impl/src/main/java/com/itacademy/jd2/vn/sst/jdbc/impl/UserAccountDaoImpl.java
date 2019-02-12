package com.itacademy.jd2.vn.sst.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.ICountryDao;
import com.itacademy.jd2.vn.sst.dao.api.IUserAccountDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.enums.UserType;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vn.sst.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Country;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.FunOrganisation;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.PreparedStatementAction;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	private ICountryDao countryDao;
	private IFunOrganisation funOrganisationDao;

	@Autowired
	public UserAccountDaoImpl(ICountryDao countryDao, IFunOrganisation funOrganisationDao) {

		super();
		this.countryDao = countryDao;
		this.funOrganisationDao = funOrganisationDao;
	}

	@Override
	public IUserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	public void insert(final IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(String.format(
				"insert into %s (name, email, password, country_id, fun_organisation_id, phone, age, type, created, updated) values(?,?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IUserAccount doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getEmail());
				pStmt.setString(3, entity.getPassword());
				pStmt.setInt(4, entity.getCountry().getId());
				pStmt.setInt(5, entity.getFunOrganisation().getId());
				pStmt.setString(6, entity.getPhone());
				pStmt.setObject(7, entity.getBirthday());
				pStmt.setString(8, entity.getRole().name());
				pStmt.setObject(9, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(10, entity.getUpdated(), Types.TIMESTAMP);
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
	public void update(final IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(String.format(
				"update  %s set name=?, email=?, password=?, country_id=?, fun_organisation_id=?, phone=?, age=?, type=?, updated=? where id=?",
				getTableName())) {

			@Override
			public IUserAccount doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getEmail());
				pStmt.setString(3, entity.getPassword());
				pStmt.setInt(4, entity.getCountry().getId());
				pStmt.setInt(5, entity.getFunOrganisation().getId());
				pStmt.setString(6, entity.getPhone());
				pStmt.setObject(7, entity.getBirthday());
				pStmt.setString(8, entity.getRole().name());
				pStmt.setObject(9, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(10, entity.getId());

				pStmt.executeUpdate();

				return entity;
			}
		});
	}

	@Override
	protected IUserAccount parseRow(final ResultSet resultSet) throws SQLException {

		final IUserAccount entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setEmail(resultSet.getString("email"));
		entity.setPassword(resultSet.getString("password"));
		entity.setPhone(resultSet.getString("phone"));
		entity.setBirthday(resultSet.getTimestamp("birthday"));
		entity.setRole(UserType.valueOf(resultSet.getString("type")));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final ICountry country = new Country();
		country.setId((Integer) resultSet.getObject("country_id"));
		entity.setCountry(country);

		final IFunOrganisation funOrganisation = new FunOrganisation();
		funOrganisation.setId((Integer) resultSet.getObject("fun_organisation_id"));
		entity.setFunOrganisation(funOrganisation);
		return entity;
	}

	@Override
	public IUserAccount getFullInfo(final Integer id) {

		final IUserAccount userAccount = get(id);
		if (userAccount.getCountry() != null) {
			userAccount.setCountry(countryDao.get(userAccount.getCountry().getId()));
		}

		return userAccount;
	}

	@Override
	public List<IUserAccount> find(final UserAccountFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final UserAccountFilter filter) {
		return executeCountQuery("");
	}

	@Override
	protected String getTableName() {
		return "user_account";
	}

	@Override
	public IUserAccount getByEmail(String email) {
		return null;
	}

}
