#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao.impl;


import ${package}.entity.*;
import ${package}.dao.*;
import it.eng.area118.sdocommon.dao.EntityDAO;
import it.eng.area118.sdocommon.dao.impl.EntityDAOImpl;
import it.eng.area118.sdocommon.dao.impl.FetchRule;

public class ExampleDAOImpl extends EntityDAOImpl<ExampleDO, String> implements ExampleDAO{
	
	public ExampleDAOImpl() {
	}

	@Override
	public Class<ExampleDO> getEntityClass() {
		return ExampleDO.class;
	}
	
	@Override
	protected FetchRule getFetchRule(String name) {
		return null;
	}
	
}
