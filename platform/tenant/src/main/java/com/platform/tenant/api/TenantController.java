package com.platform.tenant.api;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.base.aop.advice.LogExecutionMethod;
import com.platform.base.aop.advice.LogExecutionTime;
import com.platform.base.service.BaseService;
import com.platform.base.util.GenericResponse;
import com.platform.base.util.Response;
import com.platform.tenant.entity.Tenant;
import com.platform.tenant.service.TenantService;

/**
 * @author Muhil
 *
 */
@RestController
@RequestMapping("tenant")
public class TenantController {
	private static Logger logger = LoggerFactory.getLogger(TenantController.class);

	@Autowired
	BaseService baseService;

	@Autowired
	TenantService tenantSetvice;

	@RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public GenericResponse<Tenant> pingTenant(HttpServletRequest request, @RequestParam(name = "test") String test) {
		GenericResponse<Tenant> response = new GenericResponse<>();
		try {
			// ((TenantController) AopContext.currentProxy()).testmethod(test);
			response.setData((Tenant) baseService.getTenantInfo());
			response.setStatus(Response.Status.OK);
		} catch (Exception ex) {
			logger.error("ping : " + ex);
			List<String> msg = Arrays.asList(ex.getMessage());
			response.setErrorMessages(msg);
			response.setStatus(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
