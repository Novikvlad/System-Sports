package com.itacademy.jd2.vn.sst.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.IClubDao;
import com.itacademy.jd2.vn.sst.dao.api.IPartnerContractDao;
import com.itacademy.jd2.vn.sst.dao.api.IPartnerDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerContractFilter;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Club;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Partner;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.PartnerContract;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.PreparedStatementAction;

@Repository
public class PartnerContractImpl extends AbstractDaoImpl<IPartnerContract, Integer> implements IPartnerContractDao {

	private IClubDao clubDao;
	private IPartnerDao partnerDao;

	public PartnerContractImpl(IClubDao clubDao, IPartnerDao partnetDao) {
		super();
		this.clubDao = clubDao;
		this.partnerDao = partnerDao;
	}

	@Override
	public IPartnerContract createEntity() {
		return new PartnerContract();
	}

	@Override
	public void insert(final IPartnerContract entity) {
		executeStatement(new PreparedStatementAction<IPartnerContract>(String.format(
				"insert into %s(club_id, partner_id, date_signing, contract_term, contract_value, created, updated) values(?,?,?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IPartnerContract doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {

				pStmt.setInt(1, entity.getClub().getId());
				pStmt.setInt(2, entity.getPartner().getId());
				pStmt.setObject(3, entity.getDateSigning(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getContractTerm());
				pStmt.setBigDecimal(5, entity.getContractValue());
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
	public void update(final IPartnerContract entity) {

		executeStatement(new PreparedStatementAction<IPartnerContract>(String.format(
				"update into %s(club_id, partner_id, date_signing, date_termination, contract_value, created, updated) values(?,?,?,?,?,?,?)",
				getTableName())) {

			@Override
			public IPartnerContract doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {

				pStmt.setInt(1, entity.getClub().getId());
				pStmt.setInt(2, entity.getPartner().getId());
				pStmt.setObject(3, entity.getDateSigning(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getContractTerm());
				pStmt.setBigDecimal(5, entity.getContractValue());
				pStmt.setObject(6, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(7, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				return entity;
			}
		});
	}

	@Override
	protected IPartnerContract parseRow(ResultSet resultSet) throws SQLException {

		final IPartnerContract entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setDateSigning(resultSet.getDate("date_signing"));
		entity.setContractTerm(resultSet.getDate("contract_term"));
		entity.setContractValue(resultSet.getBigDecimal("contractValue"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IClub club = new Club();
		club.setId((Integer) resultSet.getObject("club_id"));
		entity.setClub(club);

		final IPartner partner = new Partner();
		partner.setId((Integer) resultSet.getObject("partner_id"));
		entity.setPartner(partner);
		return entity;
	}

	@Override
	public IPartnerContract getFulInfo(Integer id) {

		final IPartnerContract partnerContract = get(id);
		if (partnerContract.getClub() != null) {
			partnerContract.setClub(clubDao.get(partnerContract.getClub().getId()));
		}
		if (partnerContract.getPartner() != null) {
			partnerContract.setPartner(partnerDao.get(partnerContract.getPartner().getId()));
			;
		}
		return null;
	}

	@Override
	public List<IPartnerContract> find(final PartnerContractFilter filter) {

		final StringBuilder sql = new StringBuilder("");
		appendPaging(filter, sql);
		appendSort(filter, sql);

		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final PartnerContractFilter filter) {
		return executeCountQuery("");
	}

	@Override
	protected String getTableName() {
		return "partner_contract";
	}

}
