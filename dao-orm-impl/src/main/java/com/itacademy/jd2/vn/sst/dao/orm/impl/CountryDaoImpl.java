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

import com.itacademy.jd2.vn.sst.dao.api.ICountryDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.filter.CountryFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Country;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Country_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Region_;

@Repository
public class CountryDaoImpl extends AbstractDaoImpl<ICountry, Integer> implements ICountryDao {

	protected CountryDaoImpl() {
		super(Country.class);
	}

	@Override
	public ICountry createEntity() {
		return new Country();
	}

	@Override
	public ICountry getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICountry> cq = cb.createQuery(ICountry.class); // define returning result
		final Root<Country> from = cq.from(Country.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Country_.region, JoinType.LEFT);
		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Country_.id), id)); // where id=?

		final TypedQuery<ICountry> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<ICountry> find(CountryFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		// create empty query and define returning type
		final CriteriaQuery<ICountry> cq = cb.createQuery(ICountry.class);

		// define target entity(table)
		final Root<Country> from = cq.from(Country.class); // select from model

		// define what will be added to result set
		cq.select(from); // select * from model

		if (filter.isFetchRegion()) {
			// select m, b from model m left join brand b ...
			from.fetch(Country_.region, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}
		final TypedQuery<ICountry> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<ICountry> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(CountryFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Country> from = cq.from(Country.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<Country> from, final String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(Country_.name);
		case "created":
			return from.get(Country_.created);
		case "updated":
			return from.get(Country_.updated);
		case "id":
			return from.get(Country_.id);
		case "country":
			return from.get(Country_.region).get(Region_.name);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
