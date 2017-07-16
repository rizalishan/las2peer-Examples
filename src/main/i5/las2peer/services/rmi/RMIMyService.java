package i5.las2peer.services.rmi;

import java.io.Serializable;

import i5.las2peer.api.Context;
import i5.las2peer.api.Service;
import i5.las2peer.api.logging.MonitoringEvent;
import i5.las2peer.services.rmiForeign.RMIForeignService;

/**
 * This is the developer defined service used for the RMI call example. It represents your own LAS2peer service that
 * should call other services.
 *
 */
public class RMIMyService extends Service {

	// these constants are used to validate test results
	public static final int VAL1 = 5;
	public static final int VAL2 = 7;
	public static final int TEST_RESULT = 5 + 7;

	/**
	 * This method uses an RMI call to retrieve it's result.
	 * 
	 * @return Returns the result of the RMI call as String.
	 */
	public String callRMIOne() {
		try {
			// RMI call without parameters
			// !!! Remember to set the first parameter to the canonical name of the invoked service class:
			Object result = Context.get().invoke(RMIForeignService.class.getCanonicalName(), "serviceMethodOne",
					new Serializable[] {});
			if (result != null) {
				return (String) result;
			}
		} catch (Exception e) {
			// one may want to handle some exceptions differently
			Context.get().monitorEvent(this, MonitoringEvent.SERVICE_ERROR, e.toString());
		}
		return null;
	}

	/**
	 * This methods uses an RMI call to calculate the sum of two values.
	 * 
	 * @return Returns the sum of both values or null if an error occurred.
	 */
	public Integer callRMITwo() {
		try {
			// RMI call with parameters
			Object result = Context.get().invoke(RMIForeignService.class.getCanonicalName(), "serviceMethodTwo",
					new Serializable[] { VAL1, VAL2 });
			if (result != null) {
				return (int) result;
			}
		} catch (Exception e) {
			// one may want to handle some exceptions differently
			Context.get().monitorEvent(this, MonitoringEvent.SERVICE_ERROR, e.toString());
		}
		return null;
	}

}
