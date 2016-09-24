package it.eng.area118.sdocommon.dao;

import it.eng.area118.sdocommon.dao.filter.EntityFilter;
import it.eng.area118.sdocommon.dao.filter.ExampleFilter;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;

public interface EntityDAO<T, ID extends Serializable> extends BaseDAO<T, ID> {

	public long count(EntityFilter filter);

	public long countAll();

	public boolean delete(ID id);

	public void delete(T entity);

	public void delete(T... entities);

	public int deleteAll();

	public List<T> findByExample(T example);

	public List<T> findByExample(ExampleFilter<T> filter);

	public List<T> findByFilter(EntityFilter filter);

	public List<T> get(String fetchRuleName, ID... ids);

	public T get(ID id);

	public List<T> get(ID... ids);

	public List<T> get(String fetchRuleName, List<ID> ids);

	public List<T> get(List<ID> ids);

	public T get(String fetchRuleName, ID id);

	public List<T> getAll();

	public List<T> getAll(EntityFilter filter);

	public T save(T entity);

	public T merge(T entity);

	public T insert(T entity);

	public T uniqueResult(EntityFilter filter);

	public T load(ID id) throws EntityNotFoundException;

	public T update(T entity);

	public void replicate(T entity);

	public T get(Class<?> entityClass, ID id);

	public boolean delete(Class<?> entityClass, ID id);

	/**
	 * get entity with a given fetch rule name
	 * 
	 * @param fetchRuleName
	 * @param criteria
	 */
	public T get(String fetchRuleName, ID id, Class<?> entityClass);

	@Deprecated
	public void addDeletedFilter(Criteria c);

	public void clear();

	boolean contains(T object);

}