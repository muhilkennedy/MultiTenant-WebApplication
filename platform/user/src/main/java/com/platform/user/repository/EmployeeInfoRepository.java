package com.platform.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.platform.tenant.entity.Tenant;
import com.platform.user.entity.EmployeeInfo;

/**
 * @author Muhil
 *
 */
@Repository
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long> {

	String findEmployeeByEmailQuery = "select emp from EmployeeInfo emp where emp.emailId = :emailId and emp.tenant = :tenant";
	String findEmployeeByIdQuery = "select emp from EmployeeInfo emp where emp.rootId = :rootId and emp.tenant = :tenant";

	@Query(findEmployeeByIdQuery)
	EmployeeInfo findEmployeeById(@Param("rootId") Long rootId, @Param("tenant") Tenant realm);

	@Query(findEmployeeByEmailQuery)
	EmployeeInfo findEmployeeByEmail(@Param("emailId") String emailId, @Param("tenant") Tenant realm);

}
