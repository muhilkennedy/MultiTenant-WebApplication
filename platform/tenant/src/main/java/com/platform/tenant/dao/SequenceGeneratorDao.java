package com.platform.tenant.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.tenant.entity.SequenceNumber;
import com.platform.tenant.entity.Tenant;
import com.platform.tenant.repository.SequenceRepository;
import com.platform.tenant.util.TenantUtil;

/**
 * @author Muhil
 *
 */
@Component
public class SequenceGeneratorDao {
	
	private static SequenceRepository sequenceRepository;

	@Autowired
	private void setSequenceRepo(SequenceRepository seqRepo) {
		SequenceGeneratorDao.sequenceRepository = seqRepo;
	}

	public synchronized static SequenceNumber getNextSequenceNumber(String className, String tenantId)
			throws Exception {
		Tenant tenantInfo = TenantUtil.getTenantInfo(tenantId);
		SequenceNumber seqNum = sequenceRepository.findNextSequenceByClassNameNative(className, tenantInfo.getRootId());
		if (seqNum == null) {
			SequenceNumber newSequenceNumber = new SequenceNumber(tenantInfo, className, 1000, 1);
			newSequenceNumber = sequenceRepository.saveAndFlush(newSequenceNumber);
			seqNum = newSequenceNumber;
		}
		sequenceRepository.updateNextSequenceByClassNameNative(seqNum.getNextValue() + seqNum.getIncrementValue(),
				className, tenantInfo.getRootId());
		return seqNum;
	}

}
