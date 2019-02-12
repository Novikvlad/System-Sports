package com.itacademy.jd2.vn.sst.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.IUserAccountDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vn.sst.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Country_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.FunOrganisation_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.UserAccount;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.UserAccount_;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	public UserAccountDaoImpl() {
		super(UserAccount.class);
	}

	@Override
	public IUserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	public IUserAccount getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class); // define returning result
		final Root<UserAccount> from = cq.from(UserAccount.class); // define table for select

		cq.select(from);

		from.fetch(UserAccount_.funOrganisation, JoinType.LEFT);
		from.fetch(UserAccount_.country, JoinType.LEFT);
		cq.distinct(true);
		cq.where(cb.equal(from.get(UserAccount_.id), id)); // where id=?

		final TypedQuery<IUserAccount> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class); // select from model
		
		cq.select(from); 
		
		if (filter.getFetchCountry()) {
			from.fetch(UserAccount_.country, JoinType.LEFT);
		}
		if (filter.getFetchFunOrganisation()) {
			from.fetch(UserAccount_.funOrganisation, JoinType.LEFT);
		}
		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IUserAccount> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IUserAccount getByEmail(String email) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);
		cq.select(from);

		cq.where(cb.equal(from.get(UserAccount_.email), email));

		final TypedQuery<IUserAccount> q = em.createQuery(cq);

		final List<IUserAccount> resultList = q.getResultList();
		final int size = resultList.size();
		if (size != 0) {
			return resultList.get(0);
		}
		return null;
	}

	private Path<?> getSortPath(Root<UserAccount> from, String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(UserAccount_.id);
		case "name":
			return from.get(UserAccount_.name);
		case "created":
			return from.get(UserAccount_.created);
		case "updated":
			return from.get(UserAccount_.updated);
		case "email":
			return from.get(UserAccount_.email);
		case "password":
			return from.get(UserAccount_.password);
		case "phone":
			return from.get(UserAccount_.phone);
		case "birthday":
			return from.get(UserAccount_.birthday);
		case "pole":
			return from.get(UserAccount_.pole);
		case "country":
			return from.get(UserAccount_.country).get(Country_.name);
		case "funOrganisation":
			return from.get(UserAccount_.funOrganisation).get(FunOrganisation_.name);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
