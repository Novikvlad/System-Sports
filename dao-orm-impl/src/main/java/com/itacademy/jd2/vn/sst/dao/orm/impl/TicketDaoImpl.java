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
import com.itacademy.jd2.vn.sst.dao.api.ITicketDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.TicketFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Event_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Ticket;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Ticket_;

@Repository
public class TicketDaoImpl extends AbstractDaoImpl<ITicket, Integer> implements ITicketDao {

	protected TicketDaoImpl() {
		super(Ticket.class);

	}

	@Override
	public ITicket createEntity() {
		return new Ticket();
	}

	@Override
	public ITicket getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ITicket> cq = cb.createQuery(ITicket.class);
		final Root<Ticket> from = cq.from(Ticket.class);

		cq.select(from);

		from.fetch(Ticket_.event, JoinType.LEFT);
		cq.distinct(true);
		cq.where(cb.equal(from.get(Ticket_.id), id));

		final TypedQuery<ITicket> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<ITicket> find(TicketFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ITicket> cq = cb.createQuery(ITicket.class);
		final Root<Ticket> from = cq.from(Ticket.class);
		cq.select(from);

		from.fetch(Ticket_.event, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ITicket> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<Ticket> from, String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Ticket_.id);
		case "name":
			return from.get(Ticket_.name);
		case "created":
			return from.get(Ticket_.created);
		case "updated":
			return from.get(Ticket_.updated);
		case "sector":
			return from.get(Ticket_.sector);
		case "row":
			return from.get(Ticket_.row);
		case "seat":
			return from.get(Ticket_.seat);
		case "price":
			return from.get(Ticket_.price);
		case "presence":
			return from.get(Ticket_.presence);
		case "event":
			return from.get(Ticket_.event).get(Event_.name);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(TicketFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Ticket> from = cq.from(Ticket.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
