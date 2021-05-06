package com.platform.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.platform.tenant.entity.Tenant;

/**
 * @author Muhil
 *
 */
@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

	final String findTenantByNameQuery = "select realm from Tenant realm where realm.tenantName = :tenantName";
	final String findTenantByIDQuery = "select realm from Tenant realm where realm.tenantId = :tenantId";

	@Query(findTenantByNameQuery)
	List<Tenant> findTenantByName(@Param("tenantName") String tenantName);

	@Query(findTenantByIDQuery)
	Tenant findTenantById(@Param("tenantId") String tenantId);

}
