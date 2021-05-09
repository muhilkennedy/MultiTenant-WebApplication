package com.platform.tenant.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.platform.tenant.entity.Tenant;
import com.platform.tenant.entity.TenantOrigins;
import com.platform.tenant.serviceimplementation.TenantCacheService;

/**
 * @author muhil
 *
 */
@Component
public class TenantUtil {

	private static Logger logger = LoggerFactory.getLogger(TenantUtil.class);

	public static boolean isAllowedOriginForTenant(Tenant tenant, String origin) {
		try {
			List<TenantOrigins> allowedOrigins = tenant.getTenantOrigin();
			for (TenantOrigins tenantOrigin : allowedOrigins) {
				if (tenantOrigin.getOrigin().equals(origin)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			logger.error("Exception in fetching origins - " + e.getMessage());
		}
		return false;
	}

	public static Tenant getTenantInfo(String tenantId) {
		return TenantCacheService.getTenantInfo(tenantId);
	}

	public static String getTenantIdFromBase(Object tenantObject) {
		return ((Tenant) tenantObject).getTenantId();
	}

}
