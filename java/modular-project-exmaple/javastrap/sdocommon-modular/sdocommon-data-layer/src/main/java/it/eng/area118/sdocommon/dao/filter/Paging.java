package it.eng.area118.sdocommon.dao.filter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Paging {

	@XmlElement(required = false, nillable = true)
	protected Integer firstResult;

	@XmlElement(required = false, nillable = true)
	protected Integer maxResults;

	public Paging() {
	}

	public Paging(Integer firstResult, Integer maxResults) {
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}

	public boolean hasFirstResult() {
		return firstResult != null;
	}

	public boolean hasMaxResults() {
		return maxResults != null;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

}