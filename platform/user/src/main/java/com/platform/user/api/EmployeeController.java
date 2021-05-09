package com.platform.user.api;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.platform.base.service.BaseService;
import com.platform.base.util.GenericResponse;
import com.platform.base.util.Response;
import com.platform.tenant.entity.Tenant;
import com.platform.user.entity.EmployeeInfo;
import com.platform.user.service.EmployeeService;

/**
 * @author Muhil
 *
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {

	private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private BaseService baseService;
	
	@Autowired
	private EmployeeService empService;
	
	@RequestMapping(value = "/testempping", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public GenericResponse<Tenant> pingTenant(HttpServletRequest request) {
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
