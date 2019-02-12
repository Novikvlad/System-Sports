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

import com.itacademy.jd2.vn.sst.dao.api.IStadiumDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.filter.StadiumFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.City_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Stadium;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Stadium_;

@Repository
public class StadiumDaoImpl extends AbstractDaoImpl<IStadium, Integer> implements IStadiumDao {

	protected StadiumDaoImpl() {
		super(Stadium.class);
	}

	@Override
	public IStadium createEntity() {
		return new Stadium();
	}

	@Override
	public IStadium getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IStadium> cq = cb.createQuery(IStadium.class); // define returning result
		final Root<Stadium> from = cq.from(Stadium.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Stadium_.city, JoinType.LEFT);
		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Stadium_.id), id)); // where id=?

		final TypedQuery<IStadium> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IStadium> find(StadiumFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		// create empty query and define returning type
		final CriteriaQuery<IStadium> cq = cb.createQuery(IStadium.class);

		// define target entity(table)
		final Root<Stadium> from = cq.from(Stadium.class); // select from model

		// define what will be added to result set
		cq.select(from); // select * from model

		if (filter.getFetchCity()) {
			// select m, b from model m left join brand b ...
			from.fetch(Stadium_.city, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IStadium> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IStadium> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(StadiumFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Stadium> from = cq.from(Stadium.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<Stadium> from, final String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(Stadium_.name);
		case "created":
			return from.get(Stadium_.created);
		case "updated":
			return from.get(Stadium_.updated);
		case "id":
			return from.get(Stadium_.id);
		case "city":
			return from.get(Stadium_.city).get(City_.name);
		case "capacity":
			return from.get(Stadium_.capacity);
		case "address":
			return from.get(Stadium_.address);
		case "based":
			return from.get(Stadium_.based);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
