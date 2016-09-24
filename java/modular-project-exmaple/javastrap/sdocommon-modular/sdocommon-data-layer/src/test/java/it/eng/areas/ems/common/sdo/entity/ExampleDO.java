package it.eng.areas.ems.common.sdo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EXAMPLE")
public class ExampleDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8926302617469190632L;

	@Id
	@Column(name= "ID")
	private String id;

	@Column(name= "COL1")
	private String col1;

	public ExampleDO() {

	}

	public String getId() {
		return this.id;
	}

	public String getCol1() {
		return this.col1;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

}