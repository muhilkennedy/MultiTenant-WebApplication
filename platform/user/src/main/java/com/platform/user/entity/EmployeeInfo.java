package com.platform.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.platform.tenant.dao.SequenceGeneratorDao;
import com.platform.tenant.entity.SequenceNumber;

/**
 * @author Muhil
 *
 */
@Entity
@Table(name = "EMPLOYEEINFO")
public class EmployeeInfo extends User implements Serializable {

	private static Logger logger = LoggerFactory.getLogger(EmployeeInfo.class);

	private static final long serialVersionUID = 1L;
	private static final String Employee_Prefix = "EMP";

	@Column(name = "EMPLOYEEID", updatable = false, nullable = false)
	private String employeeId;

	@Column(name = "DESIGNATION")
	private String designation;

	@Column(name = "ISLOGGEDIN")
	private boolean isLoggedIn;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	@PrePersist
	public void prePersist() {
		if (StringUtils.isEmpty(this.employeeId)) {
			SequenceNumber seqNumber;
			try {
				seqNumber = SequenceGeneratorDao.getNextSequenceNumber(this.getClass().getName(),
						this.getTenant().getTenantId());
				this.employeeId = Employee_Prefix + seqNumber.getNextValue();
			} catch (Exception e) {
				logger.error("PrePersit Exception in EmployeeInfo : " + e.getMessage());
				e.printStackTrace();
			}
		}
		super.prePersist();
	}

}
