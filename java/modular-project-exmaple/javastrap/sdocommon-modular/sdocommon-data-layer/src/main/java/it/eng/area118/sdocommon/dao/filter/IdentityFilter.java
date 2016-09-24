package it.eng.area118.sdocommon.dao.filter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * filter by entity identity
 * 
 * @author felix
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class IdentityFilter<ID extends Serializable> extends EntityFilter {

	protected ID identity;

	public IdentityFilter() {
	}

	public ID getIdentity() {
		return identity;
	}

	public void setIdentity(ID identity) {
		this.identity = identity;
	}

}
