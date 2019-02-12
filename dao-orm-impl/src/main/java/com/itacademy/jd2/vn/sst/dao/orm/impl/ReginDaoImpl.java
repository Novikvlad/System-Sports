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

import com.itacademy.jd2.vn.sst.dao.api.IRegionDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.dao.api.filter.RegionFilter;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Region;
import com.itacademy.jd2.vn.sst.dao.orm.impl.entity.Region_;

@Repository
public class ReginDaoImpl extends AbstractDaoImpl<IRegion, Integer> implements IRegionDao {

	protected ReginDaoImpl() {
		super(Region.class);
	}

	@Override
	public IRegion createEntity() {
		final Region brand = new Region();
		return brand;
	}

	@Override
	public long getCount(RegionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Region> from = cq.from(Region.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<IRegion> find(RegionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IRegion> cq = cb.createQuery(IRegion.class);
		final Root<Region> from = cq.from(Region.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Region, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IRegion> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Region, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Region_.created;
		case "updated":
			return Region_.updated;
		case "id":
			return Region_.id;
		case "name":
			return Region_.name;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public void save(IRegion... entities) {
		throw new RuntimeException("unsupported method");
	}
}
