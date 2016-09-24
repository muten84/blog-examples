package it.eng.area118.sdocommon.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Repository;

import it.eng.area118.sdocommon.dao.BaseDAO;
import it.eng.area118.sdocommon.dao.filter.EntityFilter;
import it.eng.area118.sdocommon.dao.filter.OrderBy;
import it.eng.area118.sdocommon.dao.filter.Paging;
import it.esel.parsley.lang.StringUtils;

@Repository
public abstract class BaseDAOImpl<T, ID extends Serializable> implements BaseDAO<T, ID> {

	protected EntityManagerFactory emf;

	// @Autowired
	// protected SessionFactory sessionFactory;

	public BaseDAOImpl() {
	}

	// nome del persistence del dinamico
	@PersistenceUnit(unitName = "default")
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	protected Session getSession() {
		EntityManager em = EntityManagerFactoryUtils.getTransactionalEntityManager(emf);
		if (em == null) {
			em = emf.createEntityManager();
		}
		// sessionFactory.getCurrentSession();
		return em.unwrap(Session.class);
	}

	/**
	 * Create an hibernate query
	 * 
	 * @param query
	 * @return
	 */
	protected Query createQuery(String query) {
		return createQuery(query, new Object[] {});
	}

	/**
	 * Create an hibernate query
	 * 
	 * @param query
	 * @param parameters
	 * @return
	 */
	protected Query createQuery(String query, Object... parameters) {
		Query sessionQuery = getSession().createQuery(query);
		for (int idx = 0; parameters != null && idx < parameters.length; idx++) {
			sessionQuery.setParameter(idx, parameters[idx]);
		}
		return sessionQuery;
	}

	public Criteria createCriteria() {
		return createCriteria(getEntityClass(), null);
		// return getSession().createCriteria(getEntityClass());
	}

	public Criteria createCriteria(String fetchRule) {
		// Criteria criteria = getSession().createCriteria(getEntityClass());
		Criteria criteria = createCriteria(getEntityClass(), null);
		decorateFetchRule(fetchRule, criteria);
		return criteria;
	}

	public Criteria createCriteria(String fetchRule, String alias) {
		// Criteria criteria = getSession().createCriteria(getEntityClass(), alias);
		Criteria criteria = createCriteria(getEntityClass(), alias);
		if (!StringUtils.isEmpty(fetchRule))
			decorateFetchRule(fetchRule, criteria);
		return criteria;
	}

	public Criteria createCriteria(Class<?> entityClass) {
		// return getSession().createCriteria(entityClass);
		return createCriteria(entityClass, null);
	}

	public Criteria createCriteria(Class<?> entityClass, String alias) {
		Criteria criteria = null;
		if (entityClass == null) {
			entityClass = getEntityClass();
		}
		if (!StringUtils.isEmpty(alias)) {
			criteria = getSession().createCriteria(entityClass, alias);
		} else {
			criteria = getSession().createCriteria(entityClass);
		}
		return criteria;
	}

	protected final long rowCount(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	public Criteria createCriteria(EntityFilter filter) {
		final Criteria criteria = createCriteria();
		decorateEntityFilter(filter, criteria);
		return criteria;
	}

	public Criteria createCriteria(EntityFilter filter, String alias) {
		final Criteria criteria = createCriteria("", alias);
		if (!StringUtils.isEmpty(filter.getFilter()))
			getSession().enableFilter(filter.getFilter());
		decorateEntityFilter(filter, criteria);
		return criteria;
	}

	@Override
	public void evict(T entity) {
		getSession().evict(entity);
	}

	@Override
	public T reattach(T object) {
		getSession().lock(object, LockMode.NONE);
		return object;
	}

	@Override
	public void refresh(T entity) {
		getSession().refresh(entity);
	}

	protected void decorateEntityFilter(EntityFilter entityFilter, Criteria criteria) {
		if (entityFilter != null) {

			// check paging presence
			if (entityFilter.hasPaging()) {
				Paging paging = entityFilter.getPaging();
				if (paging.hasFirstResult()) {
					criteria.setFirstResult(paging.getFirstResult());
				}
				if (paging.hasMaxResults()) {
					criteria.setMaxResults(paging.getMaxResults());
				}
			}

			// check order by presence
			if (entityFilter.hasOrderBy()) {
				OrderBy orderby = entityFilter.getOrderBy();
				if (orderby.isAscending()) {
					criteria.addOrder(Order.asc(orderby.getProperty()));
				} else {
					criteria.addOrder(Order.desc(orderby.getProperty()));
				}
			}

			// the result is cacheable
			if (entityFilter.hasCacheable() && entityFilter.getCacheable()) {
				criteria.setCacheable(true);
				// criteria.setCacheMode(CacheMode.GET);
				// only if cache is enabled
				if (!StringUtils.isEmpty(entityFilter.getCacheRegion())) {
					criteria.setCacheRegion(entityFilter.getCacheRegion());
				}
			}

			// decorate fetch mode
			decorateFetchRule(entityFilter.getFetchRule(), criteria);
		}
	}

	public void flushAndClear() {
		getSession().flush();
		// entityManager.clear();
	}

	/**
	 * decorate criteria with a given fetch rule name
	 * 
	 * @param fetchRuleName
	 * @param criteria
	 */
	protected void decorateFetchRule(String fetchRuleName, Criteria criteria) {
		if (fetchRuleName == null || fetchRuleName.length() == 0) {
			// invalid rule name
			return;
		}
		// decorate criteria with fetch rule
		decorateFetchRule(getFetchRule(fetchRuleName), criteria);
	}

	protected void decorateFetchRule(FetchRule rule, Criteria criteria) {
		if (rule == null) {
			return;
		}
		rule.applyRule(criteria);
	}

	/**
	 * Returns fetch rule by name
	 * 
	 * @param name
	 * @return fetch rule
	 */
	protected abstract FetchRule getFetchRule(String name);

	// private static class EntityManagerSynchronization extends ResourceHolderSynchronization<EntityManagerHolder,
	// EntityManagerFactory> implements Ordered {
	//
	// private final Object transactionData;
	//
	// private final JpaDialect jpaDialect;
	//
	// private final boolean newEntityManager;
	//
	// public EntityManagerSynchronization(EntityManagerHolder emHolder, EntityManagerFactory emf, Object txData,
	// boolean newEm) {
	// super(emHolder, emf);
	// this.transactionData = txData;
	// this.jpaDialect = (emf instanceof EntityManagerFactoryInfo ? ((EntityManagerFactoryInfo) emf).getJpaDialect() :
	// null);
	// this.newEntityManager = newEm;
	// }
	//
	// public int getOrder() {
	// return EntityManagerFactoryUtils.ENTITY_MANAGER_SYNCHRONIZATION_ORDER;
	// }
	//
	// @Override
	// protected void flushResource(EntityManagerHolder resourceHolder) {
	// try {
	// resourceHolder.getEntityManager().flush();
	// } catch (RuntimeException ex) {
	// if (this.jpaDialect != null) {
	// throw this.jpaDialect.translateExceptionIfPossible(ex);
	// } else {
	// throw EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(ex);
	// }
	// }
	// }
	//
	// @Override
	// protected boolean shouldUnbindAtCompletion() {
	// return this.newEntityManager;
	// }
	//
	// @Override
	// protected void releaseResource(EntityManagerHolder resourceHolder, EntityManagerFactory resourceKey) {
	// EntityManagerFactoryUtils.closeEntityManager(resourceHolder.getEntityManager());
	// }
	//
	// @Override
	// protected void cleanupResource(EntityManagerHolder resourceHolder, EntityManagerFactory resourceKey, boolean
	// committed) {
	// if (!committed) {
	// // Clear all pending inserts/updates/deletes in the EntityManager.
	// // Necessary for pre-bound EntityManagers, to avoid inconsistent state.
	// resourceHolder.getEntityManager().clear();
	// }
	// cleanupTransaction(this.transactionData, resourceKey);
	// }
	// }

	// private static void cleanupTransaction(Object transactionData, EntityManagerFactory emf) {
	// if (emf instanceof EntityManagerFactoryInfo) {
	// EntityManagerFactoryInfo emfInfo = (EntityManagerFactoryInfo) emf;
	// JpaDialect jpaDialect = emfInfo.getJpaDialect();
	// if (jpaDialect != null) {
	// jpaDialect.cleanupTransaction(transactionData);
	// }
	// }
	// }

	// private static Object prepareTransaction(EntityManager em, EntityManagerFactory emf) {
	// if (emf instanceof EntityManagerFactoryInfo) {
	// EntityManagerFactoryInfo emfInfo = (EntityManagerFactoryInfo) emf;
	// JpaDialect jpaDialect = emfInfo.getJpaDialect();
	// if (jpaDialect != null) {
	// return jpaDialect.prepareTransaction(em, TransactionSynchronizationManager.isCurrentTransactionReadOnly(),
	// TransactionSynchronizationManager.getCurrentTransactionName());
	// }
	// }
	// return null;
	// }

}