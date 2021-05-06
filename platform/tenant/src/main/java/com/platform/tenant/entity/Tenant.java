package com.platform.tenant.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platform.base.entity.AbstractEntity;

/**
 * @author Muhil
 *
 */
@Entity
@Table(name = "TENANT")
//uniqueConstraints = {@UniqueConstraint(columnNames = {"tenantId"})}
public class Tenant extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "TENANTID", unique = true)
	private String tenantId;

	@OneToOne(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TenantDetail tenantDetail;

	@JsonIgnore
	@OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<TenantOrigins> tenantOrigin;

	@Column(name = "TENANTNAME")
	private String tenantName;

	@Column(name = "ACTIVE")
	private boolean active;

	@Column(name = "PURGETENANT")
	private boolean purge;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isPurge() {
		return purge;
	}

	public void setPurge(boolean purge) {
		this.purge = purge;
	}

	public TenantDetail getTenantDetail() {
		return tenantDetail;
	}

	public void setTenantDetail(TenantDetail tenantDetail) {
		this.tenantDetail = tenantDetail;
	}

	public List<TenantOrigins> getTenantOrigin() {
		return tenantOrigin;
	}

	public void setTenantOrigin(List<TenantOrigins> tenantOrigin) {
		this.tenantOrigin = tenantOrigin;
	}

	@Override
	public String toString() {
		return "Tenant [tenantId=" + tenantId + ", tenantName=" + tenantName + ", active=" + active + "]";
	}

}
