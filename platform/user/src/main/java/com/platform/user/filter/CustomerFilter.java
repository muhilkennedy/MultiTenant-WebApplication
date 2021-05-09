package com.platform.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.platform.base.service.BaseService;
import com.platform.tenant.filter.TenantFilter;

/**
 * @author Muhil
 *
 */
@Component
@Order(2)
public class CustomerFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(TenantFilter.class);

	@Autowired
	private BaseService baseService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}


}
