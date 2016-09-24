package it.eng.area118.sdocommon.dao.impl;

import org.hibernate.Criteria;

/**
 * Fetch rule interface.
 * 
 * Provide name and rule for decorating criterias
 * 
 * @author felix
 * 
 */
public interface FetchRule {

	/**
	 * unique name
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * decorate criteria
	 * 
	 * @param criteria
	 */
	public void applyRule(Criteria criteria);

}
