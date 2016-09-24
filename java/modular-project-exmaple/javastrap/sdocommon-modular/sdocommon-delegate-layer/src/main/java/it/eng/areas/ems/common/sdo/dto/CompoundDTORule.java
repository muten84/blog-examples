/**
 * 
 */
package it.eng.areas.ems.common.sdo.dto;

/**
 * @author Bifulco Luigi
 *
 */
public class CompoundDTORule implements DTORule {

	protected Class<?> source;
	protected Class<?> target;
	protected String tag;

	public CompoundDTORule(Class<?> source, Class<?> target, String tag) {
		this.source = source;
		this.target = target;
		this.tag = tag;
	}

	@Override
	public String getRule() {
		return source.getSimpleName() + "_" + target.getSimpleName() + "_" + tag;
	}
}
