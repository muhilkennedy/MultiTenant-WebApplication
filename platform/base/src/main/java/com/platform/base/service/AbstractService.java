package com.platform.base.service;

/**
 * @author Muhil
 *
 */
public interface AbstractService {

	public void save(Object obj);

	public void saveAndFlush(Object obj);

	public void findById(Object rootId);

}
