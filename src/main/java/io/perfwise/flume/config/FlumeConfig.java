package io.perfwise.flume.config;

import org.apache.jmeter.config.ConfigElement;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.TestStateListener;

public class FlumeConfig extends AbstractTestElement implements ConfigElement, TestStateListener, TestBean{

	private static final long serialVersionUID = 4057766664675197344L;

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

	public void addConfigElement(ConfigElement config) {
		// TODO Auto-generated method stub
		
	}

	public boolean expectsModification() {
		// TODO Auto-generated method stub
		return false;
	}

}
