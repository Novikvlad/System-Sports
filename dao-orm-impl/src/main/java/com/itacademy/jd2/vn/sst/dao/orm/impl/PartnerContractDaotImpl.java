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

import com.itacademy.jd2.vn.sst.dao.api.IPartnerContractDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerContractFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Club_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.PartnerContract;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.PartnerContract_;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Partner_;

@Repository
public class PartnerContractDaotImpl extends AbstractDaoImpl<IPartnerContract, Integer> implements IPartnerContractDao {

	protected PartnerContractDaotImpl() {
		super(PartnerContract.class);
	}

	@Override
	public IPartnerContract createEntity() {
		return new PartnerContract();
	}

	@Override
	public IPartnerContract getFulInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IPartnerContract> cq = cb.createQuery(IPartnerContract.class); // define returning result
		final Root<PartnerContract> from = cq.from(PartnerContract.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(PartnerContract_.club, JoinType.LEFT);

		from.fetch(PartnerContract_.partner, JoinType.LEFT);
		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(PartnerContract_.id), id)); // where id=?

		final TypedQuery<IPartnerContract> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IPartnerContract> find(PartnerContractFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		// create empty query and define returning type
		final CriteriaQuery<IPartnerContract> cq = cb.createQuery(IPartnerContract.class);

		// define target entity(table)
		final Root<PartnerContract> from = cq.from(PartnerContract.class); // select from model

		// define what will be added to result set
		cq.select(from); // select * from model

		// select m, b from model m left join brand b ...
		from.fetch(PartnerContract_.club, JoinType.LEFT);

		// select m, b from model m left join brand b ...
		from.fetch(PartnerContract_.partner, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}
		Integer clubId = filter.getClubId();
		if (clubId != null) {
			cq.where(cb.equal(from.get(PartnerContract_.club).get(Club_.id), clubId)); // where id=?
		}

		final TypedQuery<IPartnerContract> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IPartnerContract> resultList = q.getResultList();
		return resultList;
	}

	private Path<?> getSortPath(Root<PartnerContract> from, String sortColumn) {
		switch (sortColumn) {

		case "created":
			return from.get(PartnerContract_.created);
		case "updated":
			return from.get(PartnerContract_.updated);
		case "id":
			return from.get(PartnerContract_.id);
		case "club":
			return from.get(PartnerContract_.club).get(Club_.name);
		case "partner":
			return from.get(PartnerContract_.partner).get(Partner_.name);
		case "dateSigning":
			return from.get(PartnerContract_.dateSigning);
		case "contractTerm":
			return from.get(PartnerContract_.contractTerm);
		case "contractValue":
			return from.get(PartnerContract_.contractValue);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(PartnerContractFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<PartnerContract> from = cq.from(PartnerContract.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
