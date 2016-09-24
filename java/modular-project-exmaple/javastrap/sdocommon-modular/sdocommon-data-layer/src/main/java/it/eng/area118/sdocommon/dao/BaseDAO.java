package it.eng.area118.sdocommon.dao;

import java.io.Serializable;

public interface BaseDAO<T, ID extends Serializable> {

	public Class<T> getEntityClass();

	public void evict(T entity);

	public T reattach(T object);

	public void refresh(T entity);

	public void flushAndClear();

}
