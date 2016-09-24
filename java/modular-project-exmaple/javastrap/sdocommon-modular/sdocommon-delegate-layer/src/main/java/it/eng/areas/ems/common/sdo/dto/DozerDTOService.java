/**
 * 
 */
package it.eng.areas.ems.common.sdo.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.dozer.DozerBeanMapper;

public class DozerDTOService implements DTOService {

	protected DozerBeanMapper dozer;

	public DozerDTOService() {
	}

	public DozerDTOService(DozerBeanMapper dozer) {
		this.dozer = dozer;
	}

	public void setDozer(DozerBeanMapper dozer) {
		this.dozer = dozer;
	}

	@Override
	public <S, T> Collection<T> convertCollection(Collection<S> sourceCollection, Class<T> targetClass, DTORule rule) {
		Collection<T> list = new ArrayList<T>();

		for (S source : sourceCollection) {
			T tgt = convertObject(source, targetClass, rule);
			list.add(tgt);
		}

		return list;
	}

	@Override
	public <S, T> void convertCollection(Collection<S> sourceCollection, Collection<T> targetCollection, Class<T> targetClass, DTORule rule) {
		for (S source : sourceCollection) {
			T tgt = convertObject(source, targetClass, rule);
			targetCollection.add(tgt);
		}
	}

	@Override
	public <S, T> void convertCollection(Collection<S> sourceCollection, Collection<T> targetCollection, Class<T> targetClass) {
		for (S source : sourceCollection) {
			T tgt = convertObject(source, targetClass);
			targetCollection.add(tgt);
		}
	}

	@Override
	public <S, T> void convertObject(S source, T target, DTORule rule) {
		if (source != null && target != null) {
			dozer.map(source, target, rule.getRule());
		}
	}

	@Override
	public <S, T> T convertObject(S source, Class<T> targetClass) {
		if (source == null) {
			return null;
		}
		return dozer.map(source, targetClass);
	}

	@Override
	public <S, T> T convertObject(S source, Class<T> targetClass, DTORule rule) {
		if (source == null) {
			return null;
		}
		return dozer.map(source, targetClass, rule.getRule());
	}

	@Override
	public <S, T> void convertObject(S source, T target) {
		if (source != null && target != null) {
			dozer.map(source, target);
		}
	}

	@Override
	public <S, T> Collection<T> convertCollection(Collection<S> sourceCollection, Class<T> targetClass) {
		Collection<T> list = new ArrayList<T>();

		for (S source : sourceCollection) {
			T tgt = convertObject(source, targetClass);
			list.add(tgt);
		}

		return list;

	}

}
