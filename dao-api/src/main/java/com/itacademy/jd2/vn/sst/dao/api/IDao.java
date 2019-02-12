package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;


public interface IDao<ENTITY, ID> {

	ENTITY createEntity();

	ENTITY get(ID id);

	void insert(ENTITY entity);

	void update(ENTITY entity);

	void delete(ID id);

	void deleteAll();

	List<ENTITY> selectAll();
}
