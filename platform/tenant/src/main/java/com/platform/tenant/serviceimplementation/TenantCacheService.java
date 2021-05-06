package com.platform.tenant.serviceimplementation;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.platform.tenant.entity.Tenant;
import com.platform.tenant.service.TenantService;

/**
 * @author Muhil
 * 
 * Can be used as temporary cache
 *
 */
@Service
public class TenantCacheService {
	private static Logger logger = LoggerFactory.getLogger(TenantCacheService.class);

	private static LoadingCache<String, Tenant> tenantInfoCache;
	
	private static TenantService tenantService;

	@Autowired
	public void setTenantService(TenantService tenantService) {
		TenantCacheService.tenantService = tenantService;
	}

	static {
		tenantInfoCache = CacheBuilder.newBuilder()
				.refreshAfterWrite(30, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Tenant>() {
			@Override
			public Tenant load(String key) throws Exception {
				return loadTenant(key);
			}
		});
	}

	public static void setTenantInfo(Tenant tenantInfo) {
		tenantInfoCache.put(tenantInfo.getTenantId(), tenantInfo);
	}

	public static Tenant getTenantInfo(String tenantId) {
		try {
			return tenantInfoCache.get(tenantId);
		} catch (Exception e) {
			logger.error("Requested Item released from Cache - " + e.getMessage());
			return null;
		}
	}

	public static void clearTenantCache() {
		tenantInfoCache.invalidateAll();
	}
	
	public static Tenant loadTenant(String tenantId) {
		return tenantService.findByTenantId(tenantId);
	}
}
