package com.itacademy.jd2.vn.sst.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.IPartnerDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerFilter;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.Partner;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.PreparedStatementAction;
import com.itacademy.jd2.vn.sst.jdbc.impl.until.SQLExecutionException;

@Repository
public class PartnerDaoImpl extends AbstractDaoImpl<IPartner, Integer> implements IPartnerDao {

	@Override
	public IPartner createEntity() {
		return new Partner();
	}

	@Override
	public void insert(final IPartner entity) {
		executeStatement(new PreparedStatementAction<IPartner>(
				String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()), true) {
			@Override
			public IPartner doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(final IPartner entity) {
		executeStatement(new PreparedStatementAction<IPartner>(
				String.format("update %s set name=?, updated=? where id=?", getTableName())) {
			@Override
			public IPartner doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected String getTableName() {
		return "partner";
	}

	@Override
	protected IPartner parseRow(final ResultSet resultSet) throws SQLException {
		final IPartner entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public void save(IPartner... entities) {
		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (IPartner entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(
							String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()),
							Statement.RETURN_GENERATED_KEYS);

					pStmt.setString(1, entity.getName());
					pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
					pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

					pStmt.executeUpdate();

					final ResultSet rs = pStmt.getGeneratedKeys();
					rs.next();
					final int id = rs.getInt("id");

					rs.close();
					pStmt.close();
					entity.setId(id);
				}

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
	public List<IPartner> find(PartnerFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(PartnerFilter filter) {
		return executeCountQuery("");
	}

}
