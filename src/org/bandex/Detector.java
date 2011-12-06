package org.bandex;

import javax.servlet.http.HttpServletRequest;

public class Detector {

	protected static boolean isMobileBrowser(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		if (userAgent.matches(".*Android.*") || userAgent.matches(".*iPhone.*")
				|| userAgent.matches(".*iPad.*"))
			return true;

		return false;
	}

}
