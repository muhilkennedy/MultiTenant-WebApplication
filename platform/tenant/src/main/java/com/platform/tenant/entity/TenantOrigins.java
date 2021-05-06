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
@Table(name = "TENANTORIGINS")
public class TenantOrigins extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TENANTROOTID", nullable = false)
	private Tenant tenant;

	@Column(name = "ORIGIN")
	private String origin;

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
