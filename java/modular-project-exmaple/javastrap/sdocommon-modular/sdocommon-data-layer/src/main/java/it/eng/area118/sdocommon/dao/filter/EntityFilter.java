package it.eng.area118.sdocommon.dao.filter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityFilter {

	@XmlElement(required = false, nillable = true)
	protected Paging paging;

	@XmlElement(required = false, nillable = true)
	protected String fetchRule;

	@XmlElement(required = false, nillable = true)
	protected OrderBy orderBy;

	@XmlElement(required = false, nillable = true)
	protected Boolean cacheable;

	@XmlElement(required = false, nillable = true)
	protected String cacheRegion;

	@XmlElement(required = false, nillable = true)
	protected String filter;
	
	protected int maxResult;

	public EntityFilter() {
	}

	public EntityFilter(Paging paging, String fetchRule) {
		this.paging = paging;
		this.fetchRule = fetchRule;
	}

	public EntityFilter(String fetchRule) {
		this.fetchRule = fetchRule;
	}

	public EntityFilter(Paging paging) {
		this.paging = paging;
	}

	public EntityFilter(OrderBy orderBy) {
		this.orderBy = orderBy;
	}

	public EntityFilter(Paging paging, String fetchRule, OrderBy orderBy) {
		this.paging = paging;
		this.fetchRule = fetchRule;
		this.orderBy = orderBy;
	}

	public Paging getPaging() {
		return paging;
	}

	public boolean hasPaging() {
		return paging != null;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public void setMaxResults(int maxResults) {
		if (paging == null) {
			paging = new Paging();
		}
		paging.setMaxResults(maxResults);
	}

	public String getFetchRule() {
		return fetchRule;
	}

	public boolean hasFetchRule() {
		return fetchRule != null && fetchRule.length() > 0;
	}

	public void setFetchRule(String fetchRule) {
		this.fetchRule = fetchRule;
	}

	public OrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}

	public boolean hasOrderBy() {
		return orderBy != null;
	}

	public Boolean getCacheable() {
		return cacheable;
	}

	public void setCacheable(Boolean cacheable) {
		this.cacheable = cacheable;
	}

	public boolean hasCacheable() {
		return cacheable != null;
	}

	public void setCacheRegion(String cacheRegion) {
		this.cacheRegion = cacheRegion;
	}

	public String getCacheRegion() {
		return cacheRegion;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * @return the maxResult
	 */
	public int getMaxResult() {
		return maxResult;
	}

	/**
	 * @param maxResult the maxResult to set
	 */
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	
	
}
