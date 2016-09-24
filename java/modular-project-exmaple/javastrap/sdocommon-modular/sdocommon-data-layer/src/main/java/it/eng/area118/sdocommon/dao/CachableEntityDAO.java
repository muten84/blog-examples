package it.eng.area118.sdocommon.dao;

import java.io.Serializable;
import java.util.List;

public interface CachableEntityDAO<T, ID extends Serializable> extends EntityDAO<T, ID> {

	public List<T> putInCache();

	// public List<T> getFromCache(EntityFilter filter);
	//
	// public Criteria getCacheRefreshCriteria();
	//
	// public Criteria getCacheIgnoreCriteria();
	//
	// public Criteria getCacheNormalCriteria();

}