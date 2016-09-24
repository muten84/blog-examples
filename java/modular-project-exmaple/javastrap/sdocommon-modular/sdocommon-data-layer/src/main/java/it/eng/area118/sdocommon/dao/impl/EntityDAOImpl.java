package it.eng.area118.sdocommon.dao.impl;

import it.eng.area118.sdocommon.dao.EntityDAO;
import it.eng.area118.sdocommon.dao.EntityNotFoundException;
import it.eng.area118.sdocommon.dao.filter.EntityFilter;
import it.eng.area118.sdocommon.dao.filter.ExampleFilter;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;

public abstract class EntityDAOImpl<T, ID extends Serializable> extends BaseDAOImpl<T, ID> implements EntityDAO<T, ID> {

	public EntityDAOImpl() {
	}

	@Override
	public long count(EntityFilter filter) {
		final Criteria criteria = createCriteria(filter);
		return rowCount(criteria);
	}

	@Override
	public long countAll() {
		return rowCount(createCriteria());
	}

	protected Example createExample(T entity, boolean useLike) {
		Example example = Example.create(entity);

		if (useLike) {
			example.enableLike(MatchMode.ANYWHERE);
		} else {
			example.enableLike(MatchMode.EXACT);
		}
		example.excludeZeroes();
		example.ignoreCase();
		return example;
	}

	@Override
	public boolean delete(ID id) {
		boolean deleted = false;

		T entity = get(id);
		if (entity != null) {
			delete(entity);

			deleted = true;
		}

		return deleted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(T entity) {
		T savedEntity = null;
		if (!getSession().contains(entity)) {
			savedEntity = (T) getSession().merge(entity);
			getSession().delete(savedEntity);
		} else {
			getSession().delete(entity);
		}
	}

	@Override
	public void delete(T... entities) {
		Session session = getSession();
		for (T t : entities) {
			session.delete(t);
		}
	}

	@Override
	public int deleteAll() {
		final List<T> instancesToDelete = getAll();

		for (T instance : instancesToDelete) {
			delete(instance);
		}

		return instancesToDelete.size();
	}

	@Override
	public List<T> findByExample(T example) {
		return findByExample(new ExampleFilter<T>(example));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(ExampleFilter filter) {
		Criteria criteria = createCriteria(filter);
		criteria.add(createExample((T) filter.getExample(), filter.isUseLike()));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByFilter(EntityFilter filter) {
		return createCriteria(filter).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> get(String fetchRuleName, ID... ids) {
		String identifier = getSession().getSessionFactory().getClassMetadata(getEntityClass()).getIdentifierPropertyName();
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.in(identifier, ids));
		decorateFetchRule(fetchRuleName, criteria);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(ID id) {
		return (T) getSession().get(getEntityClass(), id);
	}

	@Override
	public List<T> get(ID... ids) {
		return get(null, ids);
	}

	public final T load(ID id) throws EntityNotFoundException {
		T entity = get(id);
		if (entity == null) {
			throw new EntityNotFoundException("ENTITY " + getEntityClass() + "#" + id + " NOT FOUND");
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(String fetchRuleName, ID id) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.idEq(id));
		decorateFetchRule(fetchRuleName, criteria);
		return (T) criteria.uniqueResult();
	}

	@Override
	public List<T> getAll() {
		return getAll(new EntityFilter());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(EntityFilter filter) {
		Criteria criteria = createCriteria(filter);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(T entity) {
		T savedEntity = null;
		Session session = getSession();
		if (!session.contains(entity)) {
			// oggetto non in sessione
			savedEntity = (T) session.merge(entity);
			session.saveOrUpdate(savedEntity);
		} else {
			session.saveOrUpdate(entity);
			savedEntity = entity;
		}
		return savedEntity;
	}

	@Override
	public T insert(T entity) {
		getSession().persist(entity);
		return entity;
	};

	@Override
	public void replicate(T entity) {
		Session session = getSession();
		session.replicate(entity, ReplicationMode.OVERWRITE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T uniqueResult(EntityFilter filter) {
		return (T) createCriteria(filter).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T entity) {
		return (T) getSession().merge(entity);
	}

	@Override
	public T update(T entity) {
		T savedEntity = null;
		ClassMetadata metadata = getSession().getSessionFactory().getClassMetadata(getEntityClass());
		Serializable id = metadata.getIdentifier(entity);
		if (id != null) {
			getSession().update(entity);
			savedEntity = entity;
		}
		return savedEntity;
	}

	@SuppressWarnings("unchecked")
	public List<T> get(String fetchRuleName, List<ID> ids) {
		String identifier = getSession().getSessionFactory().getClassMetadata(getEntityClass()).getIdentifierPropertyName();
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.in(identifier, ids));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		decorateFetchRule(fetchRuleName, criteria);
		return criteria.list();
	}

	public List<T> get(List<ID> ids) {
		return get(null, ids);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<?> entityClass, ID id) {
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public boolean delete(Class<?> entityClass, ID id) {
		boolean deleted = false;

		T entity = get(entityClass, id);
		if (entity != null) {
			delete(entity);

			deleted = true;
		}

		return deleted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(String fetchRuleName, ID id, Class<?> entityClass) {
		Criteria criteria = createCriteria(entityClass);
		criteria.add(Restrictions.idEq(id));
		decorateFetchRule(fetchRuleName, criteria);
		return (T) criteria.uniqueResult();
	}

	@Override
	@Deprecated
	public void addDeletedFilter(Criteria c) {
		Class superClass = getEntityClass().getSuperclass();
		String className = superClass.getSimpleName();
		if (className.equals("DeletableEntityDO"))
			c.add(Restrictions.or(Restrictions.eq("deleted", false), Restrictions.isNull("deleted")));

	}

	@Override
	public void clear() {
		getSession().clear();
	}

	@Override
	public boolean contains(T object) {
		return getSession().contains(object);
	}

}