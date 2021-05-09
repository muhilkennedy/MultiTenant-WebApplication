package com.platform.tenant.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.tenant.entity.Tenant;
import com.platform.tenant.repository.TenantRepository;
import com.platform.tenant.service.TenantService;

/**
 * @author Muhil
 *
 */
@Service
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantRepository tenantRepo;

	@Override
	public void save(Object tenantInfo) {
		tenantRepo.save((Tenant) tenantInfo);
	}

	@Override
	public void saveAndFlush(Object tenantInfo) {
		tenantRepo.saveAndFlush((Tenant) tenantInfo);
	}

	@Override
	public Object findById(Object rootId) {
		//TODO
		return null;
	}

	@Override
	public Tenant findByTenantId(String tenantId) {
		return tenantRepo.findTenantById(tenantId);
	}

}
