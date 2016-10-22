package com.example.datasource;

public class DataSourceManager {

	private String connectionUrl;
	private int connectionTimeout;
	private String username;
	private String password;

	public DataSourceManager(String connectionUrl, String username, String password,
			int connectionTimeout) {
		this.connectionUrl = connectionUrl;
		this.username = username;
		this.password = password;
		this.connectionTimeout = connectionTimeout;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void doConnect() {
		// Nothing to do here
	}
}
