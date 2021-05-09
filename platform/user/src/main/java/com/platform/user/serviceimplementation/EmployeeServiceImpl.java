package com.platform.user.serviceimplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.base.service.BaseService;
import com.platform.tenant.entity.Tenant;
import com.platform.user.entity.EmployeeInfo;
import com.platform.user.repository.EmployeeInfoRepository;
import com.platform.user.service.EmployeeService;

/**
 * @author Muhil
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private BaseService baseService;

	@Autowired
	private EmployeeInfoRepository employeeRepository;

	@Override
	public void save(Object obj) {
		employeeRepository.save((EmployeeInfo) obj);
	}

	@Override
	public void saveAndFlush(Object obj) {
		employeeRepository.saveAndFlush((EmployeeInfo) obj);
	}

	@Override
	public Object findById(Object rootId) {
		return employeeRepository.findEmployeeById((long) rootId, (Tenant) baseService.getTenantInfo());
	}

}
