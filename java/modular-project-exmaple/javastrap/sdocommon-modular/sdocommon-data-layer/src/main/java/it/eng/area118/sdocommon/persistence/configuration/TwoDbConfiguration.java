/**
 * 
 */
package it.eng.area118.sdocommon.persistence.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Bifulco Luigi
 *
 */
@Configuration
@Import(PersistenceConfig.class)
public class TwoDbConfiguration {

	@Value("${storicoDataSource.url}")
	protected String storicoDataSourceUrl;

	@Value("${storicoDataSource.username}")
	protected String storicoDataSourcUsername;

	@Value("${storicoDataSource.password}")
	protected String storicoDataSourcePassword;

	@Autowired
	private PersistenceConfigBean bean;

	public TwoDbConfiguration() {

	}

	@PostConstruct
	public void init() {
		bean.setStoricoDataSourcePassword(storicoDataSourcePassword);
		bean.setStoricoDataSourceUrl(storicoDataSourceUrl);
		bean.setStoricoDataSourcUsername(storicoDataSourcUsername);
	}

}
