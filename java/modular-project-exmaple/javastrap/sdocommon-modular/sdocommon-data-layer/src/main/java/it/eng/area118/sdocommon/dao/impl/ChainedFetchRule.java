package it.eng.area118.sdocommon.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;

/**
 * Chained fetch rule.
 * 
 * It's a compound of several fetching rules. It applies a rule by referenced rules
 * 
 * @author felix
 */
public class ChainedFetchRule extends AbstractFetchRule {

	protected List<FetchRule> rules;

	public ChainedFetchRule() {
		this("");
	}

	public ChainedFetchRule(String name) {
		super(name);
	}

	public void addRule(FetchRule rule) {
		if (rules == null) {
			rules = new ArrayList<FetchRule>();
		}
		rules.add(rule);
	}

	@Override
	public void applyRule(Criteria criteria) {
		if (rules == null) {
			return;
		}

		for (FetchRule rule : rules) {
			rule.applyRule(criteria);
		}
	}

}
