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

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientConfigurationConstants;
import org.apache.jmeter.config.ConfigElement;
import org.apache.jmeter.config.ConfigTestElement;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testbeans.TestBeanHelper;
import org.apache.jmeter.testelement.TestStateListener;
import org.apache.jmeter.threads.JMeterVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.perfwise.flume.utils.VariableSettings;

public class FlumeConfig extends ConfigTestElement implements ConfigElement, TestBean, TestStateListener, Serializable {

	private static final long serialVersionUID = 4057766664675197344L;
	private static Logger LOGGER = LoggerFactory.getLogger(FlumeConfig.class);

	private transient String flumeAgentHosts;
	private transient String clientTypeValue;
	private transient String batchSize;
	private transient String connectTimeout;
	private transient String requestTimeout;

	private List<VariableSettings> extraConfigs;

	private static final String FLUME_CLIENT = "flumeClient";
	private static RpcClient client;

	@Override
	public void addConfigElement(ConfigElement config) {

	}

	@Override
	public void testStarted() {
		this.setRunningVersion(true);
		TestBeanHelper.prepare(this);
		JMeterVariables variables = getThreadContext().getVariables();
		
		String clientTypeVal = getClientTypeValue();
		int clientTypeInt = FlumeConfigBeanInfo.getClientTypeValueAsInt(clientTypeVal);
		
		if (variables.getObject(FLUME_CLIENT) != null) {
			LOGGER.error("Flume Client connection is already established..");
		} else {
			synchronized (this) {
				try {
					Properties props = new Properties();
					props.put(RpcClientConfigurationConstants.CONFIG_BATCH_SIZE, getBatchSize());
					props.put(RpcClientConfigurationConstants.CONFIG_CONNECT_TIMEOUT, getConnectTimeout());
					props.put(RpcClientConfigurationConstants.CONFIG_REQUEST_TIMEOUT, getRequestTimeout());
					
					LOGGER.debug("Additional Cofig Size::: " + getExtraConfigs().size());
					if (getExtraConfigs().size() >= 1) {
						LOGGER.info("Setting up Additional properties");
						for (int i=0; i<getExtraConfigs().size(); i++) {
							props.put(getExtraConfigs().get(i).getConfigKey(), getExtraConfigs().get(i).getConfigValue());
							LOGGER.debug(String.format("Adding property : %s", getExtraConfigs().get(i).getConfigKey()));
						}
					}
					
					switch (clientTypeInt) {
					
					case FlumeConfigBeanInfo.AVRO_RPC:
						LOGGER.info("AVRO RPC");
						client = FlumeClients.getAvroRpcClient(flumeAgentHosts, props);
						break;
					case FlumeConfigBeanInfo.FAILOVER_RPC:
						client = FlumeClients.getFailoverRpcClient(flumeAgentHosts, props);
						LOGGER.info("FAILOVER RPC");
						break;
					case FlumeConfigBeanInfo.THRIFT_RPC:
						client = FlumeClients.getThriftRpcClient(flumeAgentHosts, props);
						LOGGER.info("THRIFT RPC");
						break;
					case FlumeConfigBeanInfo.LOADBALANCING_RPC:
						client = FlumeClients.getLoadbalancingRpcClient(flumeAgentHosts, props);
						LOGGER.info("LB RPC");
						break;
					default:
						LOGGER.info("Invalid Client Selected - Please check the Flume Config Element in JMeter");
						LOGGER.info("Aborting Test..");
						testEnded();
						break;
					}

					variables.putObject(FLUME_CLIENT, client);
					LOGGER.info("Apache Flume Client Initialized Successfully !");
				} catch (Exception e) {
					LOGGER.error("Error occured while establishing connection with Flume agents!!");
					e.printStackTrace();
				}
			}
		}
		
		
	}
	

	@Override
	public void testStarted(String host) {
		testStarted();

	}

	@Override
	public void testEnded() {
		client.close();
	}

	@Override
	public void testEnded(String host) {
		testEnded();

	}

	// Getters and setters

	public String getFlumeAgentHosts() {
		return flumeAgentHosts;
	}

	public void setFlumeAgentHosts(String flumeAgentHosts) {
		this.flumeAgentHosts = flumeAgentHosts;
	}

	public String getClientTypeValue() {
		return clientTypeValue;
	}

	public void setClientTypeValue(String clientTypeValue) {
		this.clientTypeValue = clientTypeValue;
	}

	public String getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
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

	public List<VariableSettings> getExtraConfigs() {
		return extraConfigs;
	}

	public void setExtraConfigs(List<VariableSettings> extraConfigs) {
		this.extraConfigs = extraConfigs;
	}

}


