package com.platform.base.util;

/**
 * @author Muhil
 *
 */
public class BaseUtil {

	/**
	 * @param ip
	 * @return true if its a trusted ip
	 */
	public static boolean isTrustedSubnetIp(String ip) {
		for (String trustedIp : ConfigurationUtil.getTrustedIps()) {
			if (trustedIp.equals(ip)) {
				return true;
			}
		}
		return false;
	}

}
