package com.itacademy.jd2.vn.sst.dao.orm.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.ISeasonTicketDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.SeasonTicketFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.SeasonTicket;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.SeasonTicket_;

@Repository
public class SeasonTicketDaoImpl extends AbstractDaoImpl<ISeasonTicket, Integer> implements ISeasonTicketDao {

	protected SeasonTicketDaoImpl() {
		super(SeasonTicket.class);
	}

	@Override
	public ISeasonTicket createEntity() {
		return new SeasonTicket();
	}

	@Override
	public Set<ISeasonTicket> getByEvent(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ISeasonTicket> find(SeasonTicketFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ISeasonTicket> cq = cb.createQuery(ISeasonTicket.class);
		final Root<SeasonTicket> from = cq.from(SeasonTicket.class);
		cq.select(from);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ISeasonTicket> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<SeasonTicket> from, String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(SeasonTicket_.name);
		case "created":
			return from.get(SeasonTicket_.created);
		case "updated":
			return from.get(SeasonTicket_.updated);
		case "id":
			return from.get(SeasonTicket_.id);
		case "sector":
			return from.get(SeasonTicket_.sector);
		case "row":
			return from.get(SeasonTicket_.row);
		case "seat":
			return from.get(SeasonTicket_.seat);
		case "price":
			return from.get(SeasonTicket_.price);
		case "date":
			return from.get(SeasonTicket_.date);
		case "presence":
			return from.get(SeasonTicket_.presence);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(SeasonTicketFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<SeasonTicket> from = cq.from(SeasonTicket.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
