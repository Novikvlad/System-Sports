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

import com.itacademy.jd2.vn.sst.dao.api.ICityDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.filter.CityFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.City;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.City_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Country_;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<ICity, Integer> implements ICityDao {

	protected CityDaoImpl() {
		super(City.class);
	}

	@Override
	public ICity createEntity() {
		return new City();
	}

	@Override
	public ICity getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICity> cq = cb.createQuery(ICity.class); // define returning result
		final Root<City> from = cq.from(City.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(City_.country, JoinType.LEFT);
		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(City_.id), id)); // where id=?

		final TypedQuery<ICity> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<ICity> find(CityFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		// create empty query and define returning type
		final CriteriaQuery<ICity> cq = cb.createQuery(ICity.class);

		// define target entity(table)
		final Root<City> from = cq.from(City.class); // select from model

		// define what will be added to result set
		cq.select(from); // select * from model

		// select m, b from model m left join brand b ...
		from.fetch(City_.country, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ICity> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<ICity> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(CityFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<City> from = cq.from(City.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(Root<City> from, String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(City_.name);
		case "created":
			return from.get(City_.created);
		case "updated":
			return from.get(City_.updated);
		case "id":
			return from.get(City_.id);
		case "country":
			return from.get(City_.country).get(Country_.name);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
