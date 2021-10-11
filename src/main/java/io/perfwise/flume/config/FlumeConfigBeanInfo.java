package io.perfwise.flume.config;

import org.apache.jmeter.testbeans.BeanInfoSupport;
import org.apache.jmeter.testbeans.gui.TableEditor;
import org.apache.jmeter.testbeans.gui.TypeEditor;
import org.apache.xpath.operations.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import io.perfwise.flume.utils.VariableSettings;


public class FlumeConfigBeanInfo extends BeanInfoSupport {

    private static Logger LOGGER = LoggerFactory.getLogger(FlumeConfigBeanInfo.class);

    public FlumeConfigBeanInfo() {
        super(FlumeConfig.class);

        createPropertyGroup("FlumeConnectionConfigs", new String[] {"flumeAgentHosts", "clientType", "batchSize", "connectTimeout", "requestTimeout"});
        //Additional Configs
        createPropertyGroup("AdditionalConfigs", new String[] {"extraConfigs"});

        PropertyDescriptor propDesc =  property("flumeAgentHosts");
        propDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
        propDesc.setValue(DEFAULT, "localhost:9091");
        propDesc.setDisplayName("Flume Hosts");
        propDesc.setShortDescription("List of Flume Agents/Hosts - comma separated");

        propDesc =  property("clientType");
        propDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
        propDesc.setValue(DEFAULT, "Select a Client");
        propDesc.setDisplayName("Client Type");
        propDesc.setShortDescription("Client Type - Used to connect to the Flume Source");

        propDesc =  property("batchSize");
        propDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
        propDesc.setValue(DEFAULT, "1");
        propDesc.setDisplayName("Batch Size");
        propDesc.setShortDescription("Batch Size");

        propDesc =  property("connectTimeout");
        propDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
        propDesc.setValue(DEFAULT, "");
        propDesc.setDisplayName("Connection Timeout");
        propDesc.setShortDescription("Timeout Setting for Connection");

        propDesc =  property("requestTimeout");
        propDesc.setValue(NOT_UNDEFINED, Boolean.TRUE);
        propDesc.setValue(DEFAULT, "");
        propDesc.setDisplayName("Request Timeout");
        propDesc.setShortDescription("Request Setting for Connection");


        PropertyDescriptor configProps = property("extraConfigs", TypeEditor.TableEditor);
        configProps.setValue(TableEditor.CLASSNAME, VariableSettings.class.getName());
        configProps.setValue(TableEditor.HEADERS, new String[]{ "Config Key", "Config Value" } );
        configProps.setValue(TableEditor.OBJECT_PROPERTIES, new String[]{ VariableSettings.CONFIG_KEY, VariableSettings.CONFIG_VALUE } );
        configProps.setValue(DEFAULT, new ArrayList<>());
        configProps.setValue(NOT_UNDEFINED, Boolean.TRUE);
        configProps.setDisplayName("Flume Additional Configs (Optional)");


        if (LOGGER.isDebugEnabled()) {
            String pubDescriptorsAsString = Arrays.stream(getPropertyDescriptors())
                    .map(pd -> pd.getName() + "=" + pd.getDisplayName()).collect(Collectors.joining(" ,"));
            LOGGER.debug(pubDescriptorsAsString);
        }

    }

}
