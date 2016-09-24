package it.eng.area118.sdocommon.dao.impl;

import it.eng.area118.sdocommon.dao.CachableEntityDAO;
import it.eng.area118.sdocommon.dao.filter.EntityFilter;

import java.io.Serializable;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;

public abstract class CachableEntityDAOImpl<T, ID extends Serializable> extends EntityDAOImpl<T, ID> implements CachableEntityDAO<T, ID> {

	@Override
	protected Session getSession() {
		// TODO Auto-generated method stub
		return super.getSession();
	}

	@Override
	public Criteria createCriteria(EntityFilter filter) {
		Session session = getSession();
		session.setCacheMode(CacheMode.GET);
		return super.createCriteria(filter);
	}

	@Override
	public List<T> putInCache() {
		Session session = getSession();
		session.setCacheMode(CacheMode.PUT);
		Criteria c = session.createCriteria(getEntityClass());
		c.setCacheable(true);
		c.setCacheMode(CacheMode.PUT);
		c.setCacheRegion(getEntityClass().toString());
		Statistics statistic = getSession().getSessionFactory().getStatistics();
		if (statistic.isStatisticsEnabled()) {
			statistic.logSummary();
		}
		return c.list();
	}

	// @Override
	// public List<T> getFromCache(EntityFilter filter) {
	// Criteria c = this.createCriteria(filter);
	// return c.list();
	// }
	//
	// @Override
	// public Criteria getCacheRefreshCriteria() {
	// Session session = getSession();
	// session.setCacheMode(CacheMode.REFRESH);
	// return session.createCriteria(getEntityClass());
	// }
	//
	// @Override
	// public Criteria getCacheIgnoreCriteria() {
	// Session session = getSession();
	// session.setCacheMode(CacheMode.IGNORE);
	// return session.createCriteria(getEntityClass());
	// }
	//
	// @Override
	// public Criteria getCacheNormalCriteria() {
	// Session session = getSession();
	// session.setCacheMode(CacheMode.NORMAL);
	// return session.createCriteria(getEntityClass());
	// }

}
