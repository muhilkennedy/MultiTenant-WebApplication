package com.platform.base.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author muhil
 *
 */
@Component
public class ConfigurationUtil {
	@Value("${spring.application.name}")
	private String appName;

	@Value("${app.deployment.mode}")
	private String deploymentMode;

	@Value("${cors.allowed-origin}")
	private String allowedOrigin;
	
	@Value("${app.logging.aop}")
	private boolean aopLogging;

	private enum deploymentModes {
		prod, dev
	}

	public String getApplicationName() {
		return appName;
	}

	public String getDeploymentMode() {
		return deploymentMode;
	}

	public boolean isProdMode() {
		return deploymentMode.equals(deploymentModes.prod.toString());
	}

	public String getAllowedOrigin() {
		return allowedOrigin;
	}

	public boolean isAopLogging() {
		return aopLogging;
	}

	public void setAopLogging(boolean aopLogging) {
		this.aopLogging = aopLogging;
	}
}

