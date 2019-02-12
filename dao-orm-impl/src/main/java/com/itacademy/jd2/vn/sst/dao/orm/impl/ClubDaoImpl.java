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

import com.itacademy.jd2.vn.sst.dao.api.IClubDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.filter.ClubFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Club;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Club_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Stadium_;

@Repository
public class ClubDaoImpl extends AbstractDaoImpl<IClub, Integer> implements IClubDao {

	protected ClubDaoImpl() {
		super(Club.class);
	}

	@Override
	public IClub createEntity() {
		return new Club();
	}

	@Override
	public IClub getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IClub> cq = cb.createQuery(IClub.class); // define returning result
		final Root<Club> from = cq.from(Club.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Club_.stadium, JoinType.LEFT);

		cq.where(cb.equal(from.get(Club_.id), id)); // where id=?

		final TypedQuery<IClub> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IClub> find(ClubFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IClub> cq = cb.createQuery(IClub.class);
		final Root<Club> from = cq.from(Club.class);
		cq.select(from); 

		if (filter.getFetchStadium()) {
			from.fetch(Club_.stadium, JoinType.LEFT);
		}
		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IClub> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IClub> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(ClubFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Club> from = cq.from(Club.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
	private Path<?> getSortPath(Root<Club> from, String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(Club_.name);
		case "created":
			return from.get(Club_.created);
		case "updated":
			return from.get(Club_.updated);
		case "id":
			return from.get(Club_.id);
		case "stadium":
			return from.get(Club_.stadium).get(Stadium_.name);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	
}
