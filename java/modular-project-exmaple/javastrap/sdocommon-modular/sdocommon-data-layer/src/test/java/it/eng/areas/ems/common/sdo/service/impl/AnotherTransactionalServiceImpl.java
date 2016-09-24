/**
 * 
 */
package it.eng.areas.ems.common.sdo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.eng.areas.ems.common.sdo.dao.ExampleDAO;
import it.eng.areas.ems.common.sdo.entity.ExampleDO;
import it.eng.areas.ems.common.sdo.service.AnotherTransactionalService;

/**
 * @author Bifulco Luigi
 *
 */
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AnotherTransactionalServiceImpl implements AnotherTransactionalService {

	@Autowired
	private ExampleDAO exampleDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.eng.areas.ems.common.sdo.service.AnotherTransactionalService#saveExample(it.eng.areas.ems.common.sdo.entity.
	 * ExampleDO)
	 */
	@Override
	public ExampleDO saveOrUpdateExample(ExampleDO example) {
		ExampleDO dbExample = exampleDAO.get(example.getId());
		if (dbExample != null) {
			return exampleDAO.update(example);
		}
		return exampleDAO.save(example);
	}
	
	@Override
	public ExampleDO update(ExampleDO example) {
		return exampleDAO.update(example);
	}
	
	

}
