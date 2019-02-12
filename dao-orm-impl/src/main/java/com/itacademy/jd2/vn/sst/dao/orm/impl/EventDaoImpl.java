package com.itacademy.jd2.vn.sst.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.itacademy.jd2.vn.sst.dao.api.IEventDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.filter.EventFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Event;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Event_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Stadium_;

@Repository
public class EventDaoImpl extends AbstractDaoImpl<IEvent, Integer> implements IEventDao {

	protected EventDaoImpl() {
		super(Event.class);
	}

	@Override
	public IEvent createEntity() {
		return new Event();
	}

	@Override
	public IEvent getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IEvent> cq = cb.createQuery(IEvent.class); // define returning result
		final Root<Event> from = cq.from(Event.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Event_.stadium, JoinType.LEFT);

		from.fetch(Event_.seasonTicket, JoinType.LEFT);
		cq.distinct(true); // to avoid duplicate rows in result

		cq.where(cb.equal(from.get(Event_.id), id)); // where id=?

		final TypedQuery<IEvent> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IEvent> find(final EventFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IEvent> cq = cb.createQuery(IEvent.class);
		final Root<Event> from = cq.from(Event.class); // select from model
		cq.select(from); // select * from model

		if (filter.getFetchStadium()) {
			// select m, b from model m left join brand b ...
			from.fetch(Event_.stadium, JoinType.LEFT);
		}
		applyFilter(filter, cb, cq, from);
		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IEvent> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IEvent> resultList = q.getResultList();
		return resultList;
	}
	private void applyFilter(final EventFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
            final Root<Event> from) {
        final List<Predicate> ands = new ArrayList<>();

        final String vin = filter.getName();
        if (!StringUtils.isEmpty(vin)) {
            ands.add(cb.equal(from.get(Event_.name), vin));
        }
       

        if (!ands.isEmpty()) {
            cq.where(cb.and(ands.toArray(new Predicate[0])));
        }
    }
	@Override
	public long getCount(final EventFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Event> from = cq.from(Event.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(Root<Event> from, String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(Event_.name);
		case "created":
			return from.get(Event_.created);
		case "updated":
			return from.get(Event_.updated);
		case "id":
			return from.get(Event_.id);
		case "date":
			return from.get(Event_.date);
		case "stadium":
			return from.get(Event_.stadium).get(Stadium_.name);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
