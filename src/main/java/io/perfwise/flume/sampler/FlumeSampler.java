package io.perfwise.flume.sampler;

import org.apache.jmeter.config.ConfigTestElement;
import org.apache.jmeter.engine.util.ConfigMergabilityIndicator;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestElementTraverser;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.PropertyIterator;
import org.apache.jmeter.threads.JMeterContext;

public class FlumeSampler extends FlumeTestElement implements Sampler, TestBean, ConfigMergabilityIndicator {

	
	private static final long serialVersionUID = -2503797037225437773L;

	public void addTestElement(TestElement child) {
		// TODO Auto-generated method stub
		
	}

	public void clearTestElementChildren() {
		// TODO Auto-generated method stub
		
	}

	public void setProperty(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	public void setProperty(String key, String value, String dflt) {
		// TODO Auto-generated method stub
		
	}

	public void setProperty(String key, boolean value) {
		// TODO Auto-generated method stub
		
	}

	public void setProperty(String key, boolean value, boolean dflt) {
		// TODO Auto-generated method stub
		
	}

	public void setProperty(String key, int value) {
		// TODO Auto-generated method stub
		
	}

	public void setProperty(String key, int value, int dflt) {
		// TODO Auto-generated method stub
		
	}

	public void setProperty(String name, long value) {
		// TODO Auto-generated method stub
		
	}

	public void setProperty(String name, long value, long dflt) {
		// TODO Auto-generated method stub
		
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		
	}

	public boolean isRunningVersion() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isTemporary(JMeterProperty property) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setTemporary(JMeterProperty property) {
		// TODO Auto-generated method stub
		
	}

	public boolean getPropertyAsBoolean(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getPropertyAsBoolean(String key, boolean defaultValue) {
		// TODO Auto-generated method stub
		return false;
	}

	public long getPropertyAsLong(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getPropertyAsLong(String key, long defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPropertyAsInt(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPropertyAsInt(String key, int defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getPropertyAsFloat(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getPropertyAsDouble(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setRunningVersion(boolean run) {
		// TODO Auto-generated method stub
		
	}

	public void recoverRunningVersion() {
		// TODO Auto-generated method stub
		
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public String getPropertyAsString(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPropertyAsString(String key, String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setProperty(JMeterProperty property) {
		// TODO Auto-generated method stub
		
	}

	public JMeterProperty getProperty(String propName) {
		// TODO Auto-generated method stub
		return null;
	}

	public PropertyIterator propertyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeProperty(String key) {
		// TODO Auto-generated method stub
		
	}

	public void traverse(TestElementTraverser traverser) {
		// TODO Auto-generated method stub
		
	}

	public JMeterContext getThreadContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setThreadContext(JMeterContext threadContext) {
		// TODO Auto-generated method stub
		
	}

	public String getThreadName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setThreadName(String threadName) {
		// TODO Auto-generated method stub
		
	}

	public boolean canRemove() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public String getComment() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setComment(String comment) {
		// TODO Auto-generated method stub
		
	}

	public boolean applies(ConfigTestElement configElement) {
		// TODO Auto-generated method stub
		return false;
	}

	public SampleResult sample(Entry e) {
		// TODO Auto-generated method stub
		return null;
	}

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

}
