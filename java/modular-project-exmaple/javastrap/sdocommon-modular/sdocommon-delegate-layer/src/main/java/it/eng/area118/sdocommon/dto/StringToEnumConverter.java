/**
 * 
 */
package it.eng.area118.sdocommon.dto;

import org.dozer.CustomConverter;

/**
 * @author Bifulco Luigi
 *
 */
public class StringToEnumConverter implements CustomConverter {

	@Override
	public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
		if (source == null) {
			return null;
		}
		if (source instanceof String) {
			return Enum.valueOf((Class<Enum>) destinationClass, (String) source);
		} else {
			return ((Enum) source).name();
		}
	}

}
