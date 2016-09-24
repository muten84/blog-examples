package it.eng.area118.sdocommon.dao.filter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderBy implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String property;

	protected boolean ascending;

	public OrderBy() {
	}

	public OrderBy(String property) {
		this(property, true);
	}

	public OrderBy(String property, boolean ascending) {
		this.property = property;
		this.ascending = ascending;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

}
