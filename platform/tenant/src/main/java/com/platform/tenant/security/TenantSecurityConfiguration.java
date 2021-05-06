package com.platform.tenant.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.platform.base.util.ConfigurationUtil;
import com.platform.tenant.filter.TenantFilter;

/**
 * @author muhil
 *
 */
@Configuration
@EnableWebSecurity
public class TenantSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static Logger logger = LoggerFactory.getLogger(TenantSecurityConfiguration.class);

	@Autowired
	private ConfigurationUtil configUtil;

	@Autowired
	private TenantFilter tenantFilter;

	/*
	 * overrides spring default /login authentication.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("<<<<<<<<<<<<< App Running in " + configUtil.getDeploymentMode() + " mode >>>>>>>>>>>>>");
		http.csrf().disable();
		http.cors().and().authorizeRequests().antMatchers("/**").permitAll().and().headers().frameOptions().disable();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowedOrigins(Arrays.asList(configUtil.getAllowedOrigin()));
		configuration.setAllowedMethods(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public FilterRegistrationBean<TenantFilter> RealmFilterRegistration() {
		FilterRegistrationBean<TenantFilter> registration = new FilterRegistrationBean<TenantFilter>();
		registration.setFilter(tenantFilter);
		registration.addUrlPatterns("*");
		return registration;
	}
}
