package com.platform.tenant.service;

import com.platform.base.service.AbstractService;
import com.platform.tenant.entity.SequenceNumber;

public interface SequenceGeneratorService extends AbstractService {

	SequenceNumber getAndUpdateSequenceNumber();

}
