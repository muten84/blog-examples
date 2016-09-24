/**
 * 
 */
package it.eng.area118.sdocommon.time.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import it.eng.area118.sdocommon.time.TimeService;

/**
 * @author Bifulco Luigi
 *
 */
public class LocalTimeService implements TimeService {

	@Override
	public Calendar getCurrentTime() throws TimeException {
		Calendar calendar = GregorianCalendar.getInstance();
		if (calendar == null) {
			throw new TimeException("CALENDAR INSTANCE IS NULL");
		}
		return calendar;
	}

	@Override
	public Date getCurrentUTCDate() throws TimeException {
		return new Date();
	}
}
