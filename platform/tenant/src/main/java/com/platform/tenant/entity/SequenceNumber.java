package com.platform.tenant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platform.base.entity.AbstractEntity;

/**
 * @author Muhil
 *
 */
@Entity
@Table(name = "SEQUENCENUMBER")
public class SequenceNumber extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TENANTROOTID", nullable = false)
	private Tenant tenant;

    @Column(name = "CLASSNAME", nullable = false)
    private String className;

    @Column(name = "NEXTVALUE")
    private long nextValue = 1L;

    @Column(name = "INCREMENTVALUE")
    private long incrementValue = 10L;

    public SequenceNumber() {
		super();
	}
    
	public SequenceNumber(Tenant tenant, String className, long nextValue, long incrementValue) {
		super();
		this.tenant = tenant;
		this.className = className;
		this.nextValue = nextValue;
		this.incrementValue = incrementValue;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getNextValue() {
		return nextValue;
	}

	public void setNextValue(long nextValue) {
		this.nextValue = nextValue;
	}

	public long getIncrementValue() {
		return incrementValue;
	}

	public void setIncrementValue(long incrementValue) {
		this.incrementValue = incrementValue;
	}
	
}
