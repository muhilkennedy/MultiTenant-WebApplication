package com.platform.base.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

/**
 * @author Muhil
 *
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROOTID")
	private long rootId;

	@Column(name = "TIMEUPDATED")
	private long timeUpdated;

	@Column(name = "TIMECREATED")
	private long timeCreated;

	public long getRootId() {
		return rootId;
	}

	public void setRootId(long rootId) {
		this.rootId = rootId;
	}

	public long getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(long timeUpdated) {
		this.timeUpdated = timeUpdated;
	}

	public long getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(long timeCreated) {
		this.timeCreated = timeCreated;
	}

	@PrePersist
	private void prePersist() {
		if (timeCreated <= 0L) {
			this.setTimeCreated(System.currentTimeMillis());
		}
		this.setTimeUpdated(System.currentTimeMillis());
	}

}
