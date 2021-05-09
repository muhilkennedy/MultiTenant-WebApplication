package com.platform.tenant.entity;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.io.ByteStreams;
import com.platform.base.entity.AbstractEntity;

/**
 * @author Muhil
 *
 */
@Entity
@Table(name = "TENANTDETAILS")
public class TenantDetail extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TENANTROOTID", nullable = false)
	private Tenant tenant;

	@Column(name = "TENANTCONTACT")
	private String tenantContact;

	@Column(name = "TENANTEMAIL")
	private String tenantEmail;

	@Column(name = "TENANTSTREET")
	private String tenantStreet;

	@Column(name = "TENANTCITY")
	private String tenantCity;

	@Column(name = "TENANTPIN")
	private String tenantPin;

	@Column(name = "TENANTLOGO")
	private Blob tenantLogo;

	@Column(name = "TENANTFACEBOOK")
	private String tenantFacebook;

	@Column(name = "TENANTTWITTER")
	private String tenantTwitter;

	@Column(name = "TENANTINSTA")
	private String tenantInsta;

	@Column(name = "TENANTBUSINESSEMAIL")
	private String businessEmail;

	@Column(name = "GSTIN")
	private String gstIn;

	@Column(name = "FSSAI")
	private String fssai;

	@Column(name = "TAGLINE")
	private String tagLine;

	@Column(name = "GMAPLOCATION")
	private String gmapLocation;

	@JsonIgnore
	@Column(name = "BUSINESSEMAILPASSWORD")
	private String businessEmailPassword;

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public String getTenantContact() {
		return tenantContact;
	}

	public void setTenantContact(String tenantContact) {
		this.tenantContact = tenantContact;
	}

	public String getTenantEmail() {
		return tenantEmail;
	}

	public void setTenantEmail(String tenantEmail) {
		this.tenantEmail = tenantEmail;
	}

	public String getTenantStreet() {
		return tenantStreet;
	}

	public void setTenantStreet(String tenantStreet) {
		this.tenantStreet = tenantStreet;
	}

	public String getTenantCity() {
		return tenantCity;
	}

	public void setTenantCity(String tenantCity) {
		this.tenantCity = tenantCity;
	}

	public String getTenantPin() {
		return tenantPin;
	}

	public void setTenantPin(String tenantPin) {
		this.tenantPin = tenantPin;
	}

	public Blob getTenantLogo() {
		return tenantLogo;
	}

	public void setTenantLogo(Blob tenantLogo) {
		this.tenantLogo = tenantLogo;
	}

	public String getTenantFacebook() {
		return tenantFacebook;
	}

	public void setTenantFacebook(String tenantFacebook) {
		this.tenantFacebook = tenantFacebook;
	}

	public String getTenantTwitter() {
		return tenantTwitter;
	}

	public void setTenantTwitter(String tenantTwitter) {
		this.tenantTwitter = tenantTwitter;
	}

	public String getTenantInsta() {
		return tenantInsta;
	}

	public void setTenantInsta(String tenantInsta) {
		this.tenantInsta = tenantInsta;
	}

	public String getBusinessEmail() {
		return businessEmail;
	}

	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}

	public String getGstIn() {
		return gstIn;
	}

	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}

	public String getFssai() {
		return fssai;
	}

	public void setFssai(String fssai) {
		this.fssai = fssai;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public String getGmapLocation() {
		return gmapLocation;
	}

	public void setGmapLocation(String gmapLocation) {
		this.gmapLocation = gmapLocation;
	}

	public String getBusinessEmailPassword() {
		return businessEmailPassword;
	}

	public void setBusinessEmailPassword(String businessEmailPassword) {
		this.businessEmailPassword = businessEmailPassword;
	}

	public String fetchTenantLogo() {
		if (tenantLogo != null) {
			InputStream in;
			StringBuilder base64 = new StringBuilder();
			try {
				in = tenantLogo.getBinaryStream();
				base64 = new StringBuilder("data:image/jpeg;base64,");
				base64.append(Base64.getEncoder().encodeToString(ByteStreams.toByteArray(in)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return base64.toString();
		}
		return null;
	}
}
