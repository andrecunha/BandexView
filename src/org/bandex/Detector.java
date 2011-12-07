package org.bandex;

import javax.servlet.http.HttpServletRequest;

/**
 * A class to detect characteristics from the requesting browser.
 */
public class Detector {

	/**
	 * Checks if the requesting browser is from a mobile device.
	 * 
	 * @param request
	 *            The request from the browser.
	 * @return True if the requestor is a mobile browser; false otherwise.
	 */
	protected static boolean isMobileBrowser(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		if (userAgent.matches(".*Android.*") || userAgent.matches(".*iPhone.*")
				|| userAgent.matches(".*iPad.*"))
			return true;

		return false;
	}

}
