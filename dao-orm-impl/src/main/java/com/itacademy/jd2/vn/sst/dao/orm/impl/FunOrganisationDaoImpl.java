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

import com.itacademy.jd2.vn.sst.dao.api.IFunOrganisationDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.filter.FunOrganisationFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.City_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Club_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.FunOrganisation;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.FunOrganisation_;

@Repository
public class FunOrganisationDaoImpl extends AbstractDaoImpl<IFunOrganisation, Integer> implements IFunOrganisationDao {

	protected FunOrganisationDaoImpl() {
		super(FunOrganisation.class);
	}

	@Override
	public IFunOrganisation createEntity() {
		return new FunOrganisation();
	}

	@Override
	public IFunOrganisation getFulInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IFunOrganisation> cq = cb.createQuery(IFunOrganisation.class); // define returning result
		final Root<FunOrganisation> from = cq.from(FunOrganisation.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(FunOrganisation_.club, JoinType.LEFT);

		from.fetch(FunOrganisation_.city, JoinType.LEFT);
		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(FunOrganisation_.id), id)); // where id=?

		final TypedQuery<IFunOrganisation> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IFunOrganisation> find(FunOrganisationFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		// create empty query and define returning type
		final CriteriaQuery<IFunOrganisation> cq = cb.createQuery(IFunOrganisation.class);

		// define target entity(table)
		final Root<FunOrganisation> from = cq.from(FunOrganisation.class); // select from model

		// define what will be added to result set
		cq.select(from); // select * from model

		if (filter.getFetchClub()) {
			// select m, b from model m left join brand b ...
			from.fetch(FunOrganisation_.club, JoinType.LEFT);
		}
		if (filter.getFetchCity()) {
			// select m, b from model m left join brand b ...
			from.fetch(FunOrganisation_.city, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IFunOrganisation> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IFunOrganisation> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(FunOrganisationFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<FunOrganisation> from = cq.from(FunOrganisation.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(Root<FunOrganisation> from, String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(FunOrganisation_.name);
		case "created":
			return from.get(FunOrganisation_.created);
		case "updated":
			return from.get(FunOrganisation_.updated);
		case "id":
			return from.get(FunOrganisation_.id);
		case "club":
			return from.get(FunOrganisation_.club).get(Club_.name);
		case "city":
			return from.get(FunOrganisation_.city).get(City_.name);
		case "deposit":
			return from.get(FunOrganisation_.deposit);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
