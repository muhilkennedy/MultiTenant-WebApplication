package com.platform.user.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.platform.user.filter.CustomerFilter;
import com.platform.user.filter.EmployeeFilter;
import com.platform.user.filter.EmployeePermissionsFilter;

/**
 * @author muhil
 *
 */
@Configuration
@EnableWebSecurity
public class UserSecurityConfiguration {

	private static Logger logger = LoggerFactory.getLogger(UserSecurityConfiguration.class);

	@Autowired
	private EmployeeFilter employeeFilter;

	@Autowired
	private EmployeePermissionsFilter employeePermissionsFilter;

	@Autowired
	private CustomerFilter customerFilter;

	@Bean
	public FilterRegistrationBean<EmployeeFilter> EmployeeFilterRegistration() {
		logger.info("Registering Employee Filter");
		FilterRegistrationBean<EmployeeFilter> registration = new FilterRegistrationBean<EmployeeFilter>();
		registration.setFilter(employeeFilter);
		registration.addUrlPatterns("/employee/*");
		return registration;
	}

	@Bean
	public FilterRegistrationBean<EmployeePermissionsFilter> EmployeePermissionsFilterRegistration() {
		logger.info("Registering EmployeePermissions Filter");
		FilterRegistrationBean<EmployeePermissionsFilter> registration = new FilterRegistrationBean<EmployeePermissionsFilter>();
		registration.setFilter(employeePermissionsFilter);
		registration.addUrlPatterns("/employee/*");
		return registration;
	}

	@Bean
	public FilterRegistrationBean<CustomerFilter> CustomerFilterRegistration() {
		logger.info("Registering Customer Filter");
		FilterRegistrationBean<CustomerFilter> registration = new FilterRegistrationBean<CustomerFilter>();
		registration.setFilter(customerFilter);
		registration.addUrlPatterns("/customer/*");
		return registration;
	}
}
