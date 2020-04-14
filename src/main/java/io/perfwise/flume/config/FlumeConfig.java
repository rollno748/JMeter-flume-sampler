/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.perfwise.flume.config;

import java.util.Properties;

import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.jmeter.config.ConfigElement;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testbeans.TestBeanHelper;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.TestStateListener;
import org.apache.jmeter.threads.JMeterVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlumeConfig extends AbstractTestElement implements ConfigElement, TestStateListener, TestBean {

	private static final long serialVersionUID = 4057766664675197344L;

	private static Logger LOGGER = LoggerFactory.getLogger(FlumeConfig.class);
	private static RpcClient client = null;
	private static int maxIOWorker = Runtime.getRuntime().availableProcessors() * 2;

	private String[] hostsLists;
	private String flumePort;

	private String connectTimeout;
	private String requestTimeout;
	private String maxAttempts;
	private String batchSize;
	private String backOff;
	private String maxBackOff;
	private String clientType;

	private static final String FlumeClient = "flumeClient";

	public void addConfigElement(ConfigElement config) {
		// TODO Auto-generated method stub

	}

	public boolean expectsModification() {
		// TODO Auto-generated method stub
		return false;
	}

	public void testStarted() {
		this.setRunningVersion(true);
		TestBeanHelper.prepare(this);
		JMeterVariables variables = getThreadContext().getVariables();

		if (variables.getObject(FlumeClient) != null) {
			LOGGER.error("Flume client connect is already established, Skipping client initiation");
		} else {
			synchronized (this) {
				client = RpcClientFactory.getInstance(getFlumeProperties());
				LOGGER.info("Flume client successfully Initialized");
				variables.putObject(FlumeClient, client);
				LOGGER.info(String.format("Flume client %s has been successfully initialized !", clientType));
			}
		}

	}

	private Properties getFlumeProperties() {
		final Properties props = null;
		return props;
	}

	public void testStarted(String host) {
		// TODO Auto-generated method stub

	}

	public void testEnded() {
		// TODO Auto-generated method stub

	}

	public void testEnded(String host) {
		// TODO Auto-generated method stub

	}

	// ===== Getters and Setters ====

	public String[] getHostsLists() {
		return hostsLists;
	}

	public void setHostsLists(String[] hostsLists) {
		this.hostsLists = hostsLists;
	}

	public String getFlumePort() {
		return flumePort;
	}

	public void setFlumePort(String flumePort) {
		this.flumePort = flumePort;
	}

	public String getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(String connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public String getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(String requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	public String getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(String maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public String getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}

	public String getBackOff() {
		return backOff;
	}

	public void setBackOff(String backOff) {
		this.backOff = backOff;
	}

}
