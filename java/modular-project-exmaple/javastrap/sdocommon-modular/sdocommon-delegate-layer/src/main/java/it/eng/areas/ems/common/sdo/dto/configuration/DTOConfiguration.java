/**
 * 
 */
package it.eng.areas.ems.common.sdo.dto.configuration;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import it.eng.areas.ems.common.sdo.dto.DTOService;
import it.eng.areas.ems.common.sdo.dto.DozerDTOService;

/**
 * @author Bifulco Luigi
 *
 */
@Configuration
public class DTOConfiguration {

	private static String DEFAULT_MAPPING_FILES = "classpath*:/META-INF/dto-*.xml";

	// default classpath*:/META-INF/dto-*.xml
	@Value("${dozer.mappingFiles}")
	private String dozerFiles;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public DTOService getDTOService() throws Exception {
		return new DozerDTOService((DozerBeanMapper) mapper());
	}

	@Bean
	public Mapper mapper() throws Exception {
		// DozerBeanMapper mapper = new DozerBeanMapper
		DozerBeanMapperFactoryBean factory = new DozerBeanMapperFactoryBean();
		// Resource r = new ClassPathResource("classpath*:/META-INF/dto-*.xml");
		if (dozerFiles == null || dozerFiles.isEmpty() || dozerFiles.equals("${dozer.mappingFiles}")) {
			dozerFiles = DEFAULT_MAPPING_FILES;
		}
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] res = resolver.getResources(dozerFiles);
		factory.setMappingFiles(res);
		factory.setApplicationContext(applicationContext);
		factory.afterPropertiesSet();
		return factory.getObject();
	}
}
