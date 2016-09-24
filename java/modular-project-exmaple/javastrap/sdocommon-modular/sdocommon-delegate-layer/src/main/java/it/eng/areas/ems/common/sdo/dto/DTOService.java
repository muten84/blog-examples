/**
 * 
 */
package it.eng.areas.ems.common.sdo.dto;

import java.util.Collection;

/**
 * DTO conversion service
 * 
 * @author felix
 * 
 * @param <S>
 *            Source class
 * @param <T>
 *            target class
 */
public interface DTOService {

	public <S, T> void convertObject(S source, T target);

	public <S, T> void convertObject(S source, T target, DTORule rule);

	public <S, T> T convertObject(S source, Class<T> targetClass);

	public <S, T> T convertObject(S source, Class<T> targetClass, DTORule rule);

	public <S, T> Collection<T> convertCollection(Collection<S> sourceCollection, Class<T> targetClass, DTORule rule);

	public <S, T> void convertCollection(Collection<S> sourceCollection, Collection<T> targetCollection, Class<T> targetClass, DTORule rule);

	public <S, T> void convertCollection(Collection<S> sourceCollection, Collection<T> targetCollection, Class<T> targetClass);

	public <S, T> Collection<T> convertCollection(Collection<S> sourceCollection, Class<T> targetClass);

}