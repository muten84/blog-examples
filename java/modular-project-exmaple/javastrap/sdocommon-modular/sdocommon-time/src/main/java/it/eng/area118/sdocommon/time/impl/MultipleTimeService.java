/**
 * 
 */
package it.eng.area118.sdocommon.time.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.eng.area118.sdocommon.time.TimeService;

/**
 * @author Bifulco Luigi
 *
 */
public class MultipleTimeService implements TimeService {

	protected List<TimeService> timeServices;

	public MultipleTimeService() {
		timeServices = new ArrayList<TimeService>();
	}

	public void setTimeServices(List<TimeService> timeServices) {
		this.timeServices = timeServices;
	}

	public void addTimeService(TimeService timeService) {
		timeServices.add(timeService);
	}

	@Override
	public Calendar getCurrentTime() throws TimeException {
		if (timeServices.size() > 0) {
			for (TimeService timeService : timeServices) {
				try {
					return timeService.getCurrentTime();
				} catch (TimeException e) {
				}
			}
		}
		throw new TimeException("UNAVAILABLE TIME");
	}

	@Override
	public Date getCurrentUTCDate() throws TimeException {
		if (timeServices.size() > 0) {
			for (TimeService timeService : timeServices) {
				try {
					return timeService.getCurrentUTCDate();
				} catch (TimeException e) {
				}
			}
		}
		throw new TimeException("UNAVAILABLE TIME");
	}

}