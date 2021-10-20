package io.perfwise.flume.config;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.jmeter.testbeans.BeanInfoSupport;
import org.apache.jmeter.testbeans.gui.TableEditor;
import org.apache.jmeter.testbeans.gui.TypeEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.perfwise.flume.utils.VariableSettings;

public class FlumeConfigBeanInfo extends BeanInfoSupport {

	private static Logger LOGGER = LoggerFactory.getLogger(FlumeConfigBeanInfo.class);
	
	private static final String FLUME_HOSTS="flumeAgentHosts";
	private static final String CLIENTTYPE_VALUE="clientTypeValue";
	private static final String BATCHSIZE="batchSize";
	private static final String CONN_TIMEOUT= "connectTimeout";
	private static final String REQ_TIMEOUT="requestTimeout";

	private static final String[] CLIENTTYPEVALUE_TAGS = new String[4];
	static final int AVRO_RPC = 0;
	static final int THRIFT_RPC = 1;
	static final int FAILOVER_RPC = 2;
	static final int LOADBALANCING_RPC = 3;
	
	static {
		CLIENTTYPEVALUE_TAGS[AVRO_RPC] = "clientTypeValue.avroRpc";
		CLIENTTYPEVALUE_TAGS[THRIFT_RPC] = "clientTypeValue.thriftRpc";
		CLIENTTYPEVALUE_TAGS[FAILOVER_RPC] = "clientTypeValue.failoverRpc";
		CLIENTTYPEVALUE_TAGS[LOADBALANCING_RPC] = "clientTypeValue.loadbalancingRpc";
	}


	public FlumeConfigBeanInfo() {
		super(FlumeConfig.class);

		createPropertyGroup("Flume Client Config ",
				new String[] { FLUME_HOSTS, CLIENTTYPE_VALUE, BATCHSIZE, CONN_TIMEOUT, REQ_TIMEOUT});
		// Extra configs Table
		createPropertyGroup("Additional Configs which are specific to Client Type ", new String[] { "extraConfigs" });

		PropertyDescriptor propDesc = property(FLUME_HOSTS);
		propDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
		propDesc.setValue(DEFAULT, "localhost:9091");
		propDesc.setDisplayName("Flume Hosts");
		propDesc.setShortDescription("List of Flume Agents/Hosts - comma separated");


		propDesc = property(BATCHSIZE);
		propDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
		propDesc.setValue(DEFAULT, "1");
		propDesc.setDisplayName("Batch Size");
		propDesc.setShortDescription("Batch Size");

		propDesc = property(CONN_TIMEOUT);
		propDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
		propDesc.setValue(DEFAULT, "");
		propDesc.setDisplayName("Connection Timeout");
		propDesc.setShortDescription("Timeout Setting for Connection");


		propDesc = property(REQ_TIMEOUT);
		propDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
		propDesc.setValue(DEFAULT, "");
		propDesc.setDisplayName("Request Timeout");
		propDesc.setShortDescription("Request Setting for Connection");
		
		propDesc = property(CLIENTTYPE_VALUE, TypeEditor.ComboStringEditor);
		propDesc.setValue(RESOURCE_BUNDLE, getBeanDescriptor().getValue(RESOURCE_BUNDLE));
		propDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
		propDesc.setValue(DEFAULT, CLIENTTYPEVALUE_TAGS[AVRO_RPC]);
		propDesc.setValue(NOT_OTHER, Boolean.FALSE);
		propDesc.setValue(NOT_EXPRESSION, Boolean.FALSE);
		propDesc.setValue(TAGS, CLIENTTYPEVALUE_TAGS);


		PropertyDescriptor configProps = property("extraConfigs", TypeEditor.TableEditor);
		configProps.setValue(TableEditor.CLASSNAME, VariableSettings.class.getName());
		configProps.setValue(TableEditor.HEADERS, new String[] { "Config Key", "Config Value" });
		configProps.setValue(TableEditor.OBJECT_PROPERTIES,
				new String[] { VariableSettings.CONFIG_KEY, VariableSettings.CONFIG_VALUE });
		configProps.setValue(DEFAULT, new ArrayList<>());
		configProps.setValue(NOT_UNDEFINED, Boolean.TRUE);
		configProps.setDisplayName("Flume Additional Configs (Optional)");
		

		if (LOGGER.isDebugEnabled()) {
			String pubDescriptorsAsString = Arrays.stream(getPropertyDescriptors())
					.map(pd -> pd.getName() + "=" + pd.getDisplayName()).collect(Collectors.joining(" ,"));
			LOGGER.debug(pubDescriptorsAsString);
		}

		
	}
	
	public static int getClientTypeValueAsInt(String mode) {
		if (mode == null || mode.length() == 0) {
			return AVRO_RPC;
		}
		for (int i = 0; i < CLIENTTYPEVALUE_TAGS.length; i++) {
			if (CLIENTTYPEVALUE_TAGS[i].equals(mode)) {
				return i;
			}
		}
		return -1;
	}
	
	public static String[] getClientTypeValueTags() {
		String[] copy = new String[CLIENTTYPEVALUE_TAGS.length];
		System.arraycopy(CLIENTTYPEVALUE_TAGS, 0, copy, 0, CLIENTTYPEVALUE_TAGS.length);
		return copy;
	}

}
