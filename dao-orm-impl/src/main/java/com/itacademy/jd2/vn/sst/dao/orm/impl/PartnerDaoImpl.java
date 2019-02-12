package com.itacademy.jd2.vn.sst.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vn.sst.dao.api.IPartnerDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Partner;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Partner_;

@Repository
public class PartnerDaoImpl extends AbstractDaoImpl<IPartner, Integer> implements IPartnerDao {

	protected PartnerDaoImpl() {
		super(Partner.class);
	}

	@Override
	public IPartner createEntity() {
		final Partner partner = new Partner();
		return partner;
	}

	@Override
	public long getCount(PartnerFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Partner> from = cq.from(Partner.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<IPartner> find(PartnerFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IPartner> cq = cb.createQuery(IPartner.class);
		final Root<Partner> from = cq.from(Partner.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Partner, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IPartner> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Partner, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Partner_.created;
		case "updated":
			return Partner_.updated;
		case "id":
			return Partner_.id;
		case "name":
			return Partner_.name;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public void save(IPartner... entities) {
		throw new RuntimeException("unsupported method");

	}
}
