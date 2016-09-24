package it.eng.area118.sdocommon.dao.impl;

public abstract class AbstractFetchRule implements FetchRule {

	protected String name;

	public AbstractFetchRule() {
	}

	public AbstractFetchRule(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
