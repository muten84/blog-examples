/**
 * 
 */
package it.eng.areas.ems.common.sdo.dto.test;

import java.io.Serializable;

/**
 * @author Bifulco Luigi
 *
 */
public class ExampleDTO implements Serializable {

	private String field1;

	private int field2;

	public ExampleDTO() {

	}

	/**
	 * @return the field1
	 */
	public String getField1() {
		return field1;
	}

	/**
	 * @param field1
	 *            the field1 to set
	 */
	public void setField1(String field1) {
		this.field1 = field1;
	}

	/**
	 * @return the field2
	 */
	public int getField2() {
		return field2;
	}

	/**
	 * @param field2
	 *            the field2 to set
	 */
	public void setField2(int field2) {
		this.field2 = field2;
	}

}
