/**
 * 
 */
package it.eng.areas.ems.common.sdo.mail;

/**
 * @author Bifulco Luigi
 *
 */
public interface MailService {

	/**
	 * @param dest
	 * @param text
	 * @return
	 */
	boolean sendMail(String dest, String text);

	/**
	 * @param dest
	 * @param text
	 * @return
	 */
	boolean sendMail(String[] dest, String text);

	/**
	 * @param dest
	 * @param text
	 * @param attachName
	 * @param attachment
	 */
	boolean sendMail(String dest, String text, String attachName, byte[] attachment);

}
