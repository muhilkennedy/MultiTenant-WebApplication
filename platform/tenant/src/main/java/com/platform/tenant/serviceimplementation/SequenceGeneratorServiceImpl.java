package com.platform.tenant.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.base.service.BaseService;
import com.platform.tenant.entity.SequenceNumber;
import com.platform.tenant.entity.Tenant;
import com.platform.tenant.repository.SequenceRepository;
import com.platform.tenant.service.SequenceGeneratorService;

/**
 * @author Muhil
 * 
 * Can be used as temporary cache
 *
 */
@Service
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private SequenceRepository sequenceNumberRepo;

	@Override
	public void save(Object obj) {
		sequenceNumberRepo.save((SequenceNumber)obj);
	}

	@Override
	public void saveAndFlush(Object obj) {
		sequenceNumberRepo.saveAndFlush((SequenceNumber)obj);
	}

	@Override
	public Object findById(Object rootId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public synchronized SequenceNumber getAndUpdateSequenceNumber() {
		SequenceNumber seqNumber = sequenceNumberRepo.findNextSequenceByClassName(this.getClass().getName(), (Tenant)baseService.getTenantInfo());
		
		return seqNumber;
	}
	
	

}
