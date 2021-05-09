package com.platform.base.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.base.config.DataSourceProperties;

/**
 * @author Muhil
 *
 */
@Component
public class DatabaseUtil {

	@Autowired
	private DataSourceProperties dbProperties;

	public Connection getConnectionInstance() throws Exception {
		return DriverManager.getConnection(dbProperties.getUrl(), dbProperties.getUsername(),
				dbProperties.getPassword());
	}
}
