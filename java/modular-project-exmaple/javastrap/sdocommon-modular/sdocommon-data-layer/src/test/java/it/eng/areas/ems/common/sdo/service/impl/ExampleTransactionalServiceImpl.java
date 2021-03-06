package it.eng.areas.ems.common.sdo.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.eng.areas.ems.common.sdo.dao.ExampleDAO;
import it.eng.areas.ems.common.sdo.entity.ExampleDO;
import it.eng.areas.ems.common.sdo.service.ExampleTransactionalService;

/**
 * @author Bifulco Luigi
 *
 */

@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ExampleTransactionalServiceImpl implements ExampleTransactionalService {

	@Autowired
	private ExampleDAO exampleDAO;

	public ExampleTransactionalServiceImpl() {

	}

	@PostConstruct
	public void init() {
		// TODO: all dependendecies injected, init your stateless service here
		// TODO: tutte le dipendenze sono state inettate inizializza qui il tuo stateless service
	}

	@Override
	public ExampleDO getExampleById(String id) {
		ExampleDO example = exampleDAO.get(id);
//		if (example == null) {
//			throw new NullPointerException("The entity does not exist");
//		}
		return example;
	}
}
