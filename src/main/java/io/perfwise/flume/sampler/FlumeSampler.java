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
package io.perfwise.flume.sampler;

import java.nio.charset.StandardCharsets;

import org.apache.jmeter.config.ConfigTestElement;
import org.apache.jmeter.engine.util.ConfigMergabilityIndicator;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.testbeans.TestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlumeSampler extends FlumeTestElement implements Sampler, TestBean, ConfigMergabilityIndicator {

	
	private static final long serialVersionUID = -2503797037225437773L;
	private static final Logger LOGGER = LoggerFactory.getLogger(FlumeSampler.class);

	public void testStarted() {
		// TODO Auto-generated method stub
		
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

	public boolean applies(ConfigTestElement configElement) {
		// TODO Auto-generated method stub
		return false;
	}

	public SampleResult sample(Entry e) {
		SampleResult result = new SampleResult();
		result.setSampleLabel(getName());
		//result.setSamplerData(request());
		result.setDataType(SampleResult.TEXT);
		result.setContentType("text/plain");
		result.setDataEncoding(StandardCharsets.UTF_8.name());
		
		result.sampleStart();

		try {
			//publish(template, result);
		} catch (Exception ex) {
			LOGGER.info("Exception occurred while publishing message");
			result = handleException(result, ex);
		} finally {
			result.sampleEnd();
		}
		return result;
	}
	
	private SampleResult handleException(SampleResult result, Exception ex) {
		result.setResponseMessage("Message Publish Error");
		result.setResponseCode("500");
		result.setResponseData(String.format("Error in publishing message to PubSub topic : %s", ex.toString()).getBytes());// PublisherConfig.getTopic()
		result.setSuccessful(false);
		return result;
	}

	
}
