package com.platform.tenant.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.platform.base.service.BaseService;
import com.platform.base.util.ConfigurationUtil;
import com.platform.base.util.Constants;
import com.platform.tenant.entity.Tenant;
import com.platform.tenant.util.TenantUtil;

@Component
@Order(1)
public class TenantFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(TenantFilter.class);

	@Autowired
	private BaseService baseService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String tenantId = req.getHeader(Constants.Header_TenantId);
		Tenant tenantInfo = null;
		// load tenant info from cache
		if (StringUtils.isNotEmpty(tenantId)) {
			tenantInfo = TenantUtil.getTenantInfo(tenantId);
			if (tenantInfo == null && ConfigurationUtil.isProdMode()) {
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Tenant Not Found!");
				return;
			}
		} else {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Required Headers Missing!");
			return;
		}
		String origin = req.getHeader(Constants.Header_RequestOrigin);
		String requestIP = getIPFromRequest(req);
		logger.info("doFilter :: Tenant Filter :: TenantId - " + tenantId + " URI - " + req.getRequestURI()
				+ " (Requested IP : " + requestIP + ")");
		// Allow access for cross site request due to multiple deployments.
		res.setHeader("Access-Control-Allow-Origin", req.getHeader(Constants.Header_Origin));
		res.setHeader("Access-Control-Allow-Credentials", "true");
		if (ConfigurationUtil.isProdMode()) {
			if (StringUtils.isNotEmpty(origin) && TenantUtil.isAllowedOriginForTenant(tenantInfo, origin)) {
				baseService.setTenantInfo(tenantInfo);
				chain.doFilter(request, response);
			} else {
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN,
						"Access to Realm Restricted");
				return;
			}
		} else {
			if (tenantInfo != null && TenantUtil.isAllowedOriginForTenant(tenantInfo, origin)) {
				baseService.setTenantInfo(tenantInfo);
				chain.doFilter(request, response);
			} else {
				// set default tenantId for dev purposes.
				tenantInfo = TenantUtil.getTenantInfo("devTenant");
				baseService.setTenantInfo(tenantInfo);
				chain.doFilter(request, response);
			}
		}

	}

	private String getIPFromRequest(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		for (String header : Constants.IP_HEADER_CANDIDATES) {
			String ipList = request.getHeader(header);
			if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
				return ipList.split(",")[0];
			}
		}
		return request.getRemoteAddr();
	}

}
