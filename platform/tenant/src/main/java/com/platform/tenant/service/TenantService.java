package com.platform.tenant.service;

import com.platform.base.service.AbstractService;
import com.platform.tenant.entity.Tenant;

/**
 * @author Muhil
 *
 */
public interface TenantService extends AbstractService {

	Tenant findByTenantId(String tenantId);

}
