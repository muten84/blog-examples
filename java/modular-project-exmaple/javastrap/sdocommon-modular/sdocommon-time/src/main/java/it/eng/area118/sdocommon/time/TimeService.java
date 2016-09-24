/**
 * 
 */
package it.eng.area118.sdocommon.time;

import java.util.Calendar;
import java.util.Date;

import it.eng.area118.sdocommon.time.impl.TimeException;

/**
 * @author Bifulco Luigi
 *
 */
public interface TimeService {

	public Calendar getCurrentTime() throws TimeException;

	public Date getCurrentUTCDate() throws TimeException;

}
