package com.platform.user.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platform.base.entity.AbstractEntity;
import com.platform.base.security.AttributeEncryptor;
import com.platform.tenant.entity.Tenant;

/**
 * @author Muhil
 *
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TENANTROOTID", nullable = false)
	private Tenant tenant;

	@Column(name = "FNAME")
	private String firstName;

	@Column(name = "LNAME")
	private String lastName;

	@Convert(converter = AttributeEncryptor.class)
	@Column(name = "EMAILID")
	private String emailId;

	@Column(name = "PASSWORD")
	private String password;

	@Convert(converter = AttributeEncryptor.class)
	@Column(name = "MOBILE")
	private String mobile;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "PROFILEPIC")
	private Blob profilePic;

	@Column(name = "LASTLOGIN")
	private long lastLogin;

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Blob getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(Blob profilePic) {
		this.profilePic = profilePic;
	}

	public long getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}

}
