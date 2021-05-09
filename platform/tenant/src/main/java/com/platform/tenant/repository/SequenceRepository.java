package com.platform.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.platform.tenant.entity.SequenceNumber;
import com.platform.tenant.entity.Tenant;

/**
 * @author Muhil
 *
 */
@Repository
public interface SequenceRepository extends JpaRepository<SequenceNumber, Long> {

	final String findNextSequenceByClassNameQuery = "select sn from SequenceNumber sn where sn.className = :className and sn.tenant = :tenant";
	final String findNextSequenceByClassNameNativeQuery = "select * from SequenceNumber where classname = :className and tenantrootid = :tenantrootid";
	final String updateNextSequenceByClassNameNativeQuery = "update SequenceNumber set nextvalue = :nextValue where classname = :className and tenantrootid = :tenantrootid";

	@Query(findNextSequenceByClassNameQuery)
	SequenceNumber findNextSequenceByClassName(@Param("className") String className, @Param("tenant") Tenant tenant);
	
	@Query(value = findNextSequenceByClassNameNativeQuery, nativeQuery = true)
	SequenceNumber findNextSequenceByClassNameNative(@Param("className") String className, @Param("tenantrootid") long tenantrootid);

	@Modifying
	@Query(value = updateNextSequenceByClassNameNativeQuery, nativeQuery = true)
	void updateNextSequenceByClassNameNative(@Param("nextValue") long nextValue, @Param("className") String className, @Param("tenantrootid") long tenantrootid);
	
}
