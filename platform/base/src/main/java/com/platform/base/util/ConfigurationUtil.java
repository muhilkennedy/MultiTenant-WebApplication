package com.platform.base.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author muhil
 *
 */
@Component
public class ConfigurationUtil {

	private static String appName;
	private static String deploymentMode;
	private static String serverPort;
	private static String allowedOrigin;
	private static boolean aopLogging;
	private static List<String> trustedIps;
	private static boolean flywayEnabled;

	private static enum deploymentModes {
		prod, dev
	}

	@Value("${app.deployment.mode}")
	public void setDeployment(String mode) {
		ConfigurationUtil.deploymentMode = mode;
	}

	@Value("${server.port}")
	public void setPort(String port) {
		ConfigurationUtil.serverPort = port;
	}

	@Value("${spring.application.name}")
	public void setAppName(String appName) {
		ConfigurationUtil.appName = appName;
	}

	@Value("${cors.allowed-origin}")
	public void setAllowedOrigin(String mode) {
		ConfigurationUtil.allowedOrigin = allowedOrigin;
	}

	@Value("${app.logging.aop}")
	public void setAOPLogging(boolean logging) {
		ConfigurationUtil.aopLogging = logging;
	}

	@Value("${spring.trusted.ips}")
	public void setTrustedSubnets(List<String> ips) {
		ConfigurationUtil.trustedIps = ips;
	}

	@Value("${spring.flyway.enabled}")
	public void setPort(boolean flywayEnabled) {
		ConfigurationUtil.flywayEnabled = flywayEnabled;
	}

	public static String getApplicationName() {
		return appName;
	}

	public static String getDeploymentMode() {
		return deploymentMode;
	}

	public static boolean isProdMode() {
		return deploymentMode.equals(deploymentModes.prod.toString());
	}

	public static String getAllowedOrigin() {
		return allowedOrigin;
	}

	public static boolean isAopLogging() {
		return aopLogging;
	}

	public static void setAopLogging(boolean aopLogging) {
		ConfigurationUtil.aopLogging = aopLogging;
	}

	public static String getAppName() {
		return appName;
	}

	public static List<String> getTrustedIps() {
		return trustedIps;
	}

	public static String getServerPort() {
		return serverPort;
	}

	public static boolean isFlywayEnabled() {
		return flywayEnabled;
	}
}
