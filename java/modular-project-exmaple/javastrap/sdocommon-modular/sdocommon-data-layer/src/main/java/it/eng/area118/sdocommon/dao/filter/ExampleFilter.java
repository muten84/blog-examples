package it.eng.area118.sdocommon.dao.filter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * filter by entity prototype
 * 
 * @author felix
 * 
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class ExampleFilter<T> extends EntityFilter {

	protected T example;

	protected boolean useLike;

	public ExampleFilter() {
	}

	public ExampleFilter(T example) {
		this.example = example;
	}

	public T getExample() {
		return example;
	}

	public void setExample(T example) {
		this.example = example;
	}

	public boolean isUseLike() {
		return useLike;
	}

	public void setUseLike(boolean useLike) {
		this.useLike = useLike;
	}

}
