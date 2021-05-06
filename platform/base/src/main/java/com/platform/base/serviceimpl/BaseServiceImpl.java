package com.platform.base.serviceimpl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.platform.base.service.BaseService;

/**
 * @author Muhil
 *
 */
@Component
@Service
public class BaseServiceImpl implements BaseService {

	private ThreadLocal<Object> tenantInfo = new ThreadLocal<Object>();

	@Override
	public Object getTenantInfo() {
		return tenantInfo.get();
	}

	@Override
	public void setTenantInfo(Object tenantInfo) {
		this.tenantInfo.set(tenantInfo);
	}

	@Override
	public void clear() {
		setTenantInfo(null);
	}
}
