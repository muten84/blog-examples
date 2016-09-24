/**
 * 
 */
package it.eng.area118.sdocommon.persistence.configuration;

/**
 * @author Bifulco Luigi
 *
 */
public class PersistenceConfigBean {

	private String dataSourceDriverClassName;

	private String defaultDataSourceUrl;

	private String defaultDataSourcUsername;

	private String defaultDataSourcePassword;

	private String jpaDatabasePlatform;

	private String storicoDataSourceUrl;

	private String storicoDataSourcUsername;

	private String storicoDataSourcePassword;

	private String persistenceXmlLocation;

	private String bulkIdStrategy;

	/**
	 * @return the dataSourceDriverClassName
	 */
	public String getDataSourceDriverClassName() {
		return dataSourceDriverClassName;
	}

	/**
	 * @param dataSourceDriverClassName
	 *            the dataSourceDriverClassName to set
	 */
	public void setDataSourceDriverClassName(String dataSourceDriverClassName) {
		this.dataSourceDriverClassName = dataSourceDriverClassName;
	}

	/**
	 * @return the defaultDataSourceUrl
	 */
	public String getDefaultDataSourceUrl() {
		return defaultDataSourceUrl;
	}

	/**
	 * @param defaultDataSourceUrl
	 *            the defaultDataSourceUrl to set
	 */
	public void setDefaultDataSourceUrl(String defaultDataSourceUrl) {
		this.defaultDataSourceUrl = defaultDataSourceUrl;
	}

	/**
	 * @return the defaultDataSourcUsername
	 */
	public String getDefaultDataSourcUsername() {
		return defaultDataSourcUsername;
	}

	/**
	 * @param defaultDataSourcUsername
	 *            the defaultDataSourcUsername to set
	 */
	public void setDefaultDataSourcUsername(String defaultDataSourcUsername) {
		this.defaultDataSourcUsername = defaultDataSourcUsername;
	}

	/**
	 * @return the defaultDataSourcePassword
	 */
	public String getDefaultDataSourcePassword() {
		return defaultDataSourcePassword;
	}

	/**
	 * @param defaultDataSourcePassword
	 *            the defaultDataSourcePassword to set
	 */
	public void setDefaultDataSourcePassword(String defaultDataSourcePassword) {
		this.defaultDataSourcePassword = defaultDataSourcePassword;
	}

	/**
	 * @return the jpaDatabasePlatform
	 */
	public String getJpaDatabasePlatform() {
		return jpaDatabasePlatform;
	}

	/**
	 * @param jpaDatabasePlatform
	 *            the jpaDatabasePlatform to set
	 */
	public void setJpaDatabasePlatform(String jpaDatabasePlatform) {
		this.jpaDatabasePlatform = jpaDatabasePlatform;
	}

	/**
	 * @return the storicoDataSourceUrl
	 */
	public String getStoricoDataSourceUrl() {
		return storicoDataSourceUrl;
	}

	/**
	 * @param storicoDataSourceUrl
	 *            the storicoDataSourceUrl to set
	 */
	public void setStoricoDataSourceUrl(String storicoDataSourceUrl) {
		this.storicoDataSourceUrl = storicoDataSourceUrl;
	}

	/**
	 * @return the storicoDataSourcUsername
	 */
	public String getStoricoDataSourcUsername() {
		return storicoDataSourcUsername;
	}

	/**
	 * @param storicoDataSourcUsername
	 *            the storicoDataSourcUsername to set
	 */
	public void setStoricoDataSourcUsername(String storicoDataSourcUsername) {
		this.storicoDataSourcUsername = storicoDataSourcUsername;
	}

	/**
	 * @return the storicoDataSourcePassword
	 */
	public String getStoricoDataSourcePassword() {
		return storicoDataSourcePassword;
	}

	/**
	 * @param storicoDataSourcePassword
	 *            the storicoDataSourcePassword to set
	 */
	public void setStoricoDataSourcePassword(String storicoDataSourcePassword) {
		this.storicoDataSourcePassword = storicoDataSourcePassword;
	}

	/**
	 * @return the persistenceXmlLocation
	 */
	public String getPersistenceXmlLocation() {
		return persistenceXmlLocation;
	}

	/**
	 * @param persistenceXmlLocation
	 *            the persistenceXmlLocation to set
	 */
	public void setPersistenceXmlLocation(String persistenceXmlLocation) {
		this.persistenceXmlLocation = persistenceXmlLocation;
	}

	/**
	 * @return the bulkIdStrategy
	 */
	public String getBulkIdStrategy() {
		return bulkIdStrategy;
	}

	/**
	 * @param bulkIdStrategy
	 *            the bulkIdStrategy to set
	 */
	public void setBulkIdStrategy(String bulkIdStrategy) {
		this.bulkIdStrategy = bulkIdStrategy;
	}

}
