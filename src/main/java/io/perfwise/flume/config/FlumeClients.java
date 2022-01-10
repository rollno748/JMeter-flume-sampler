package io.perfwise.flume.config;

import java.util.Properties;

import org.apache.flume.FlumeException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientConfigurationConstants;
import org.apache.flume.api.RpcClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlumeClients {

	private static Logger LOGGER = LoggerFactory.getLogger(FlumeClients.class);

	public static RpcClient getAvroRpcClient(Properties props) {
		RpcClient client = null;
		String[] temp = props.getProperty("hosts").split(":");
		String hostname = temp[0];
		int port = Integer.parseInt(temp[1]);

		try {
			if (props.containsKey("batch-size")) {
				client = RpcClientFactory.getDefaultInstance(hostname, port,
						Integer.parseInt(props.getProperty("batch-size")));
			} else {
				client = RpcClientFactory.getDefaultInstance(hostname, port);
			}
			LOGGER.info("Flume Connection established successfully!");
		} catch (FlumeException fe) {
			LOGGER.info("Exception occurred while establishing connection to Flume hosts :: " + hostname + ":" + port
					+ "\n" + fe);
		}
		return client;
	}

	public static RpcClient getThriftRpcClient(Properties props) {
		RpcClient client = null;
		try {
			if (props.containsKey("batch-size")) {
				client = RpcClientFactory.getThriftInstance(props);
			} else {
				client = RpcClientFactory.getThriftInstance(props);
			}
			LOGGER.info("Flume Connection established successfully!");
		} catch (FlumeException fe) {
			LOGGER.info("Exception occurred while establishing connection to Flume hosts :: \n" + fe);
		}
		return client;
	}

	public static RpcClient getFailoverRpcClient(Properties props) {
		props.setProperty(RpcClientConfigurationConstants.CONFIG_CLIENT_TYPE, "default_failover");

		RpcClient client = null;
		try {
			client = RpcClientFactory.getInstance(props);
			LOGGER.info("Flume Connection established successfully!");
		} catch (FlumeException fe) {
			LOGGER.info("Exception occurred while establishing connection to Flume hosts :: \n" + fe);
		}
		return client;
	}

	public static RpcClient getLoadbalancingRpcClient(Properties props) {
		props.setProperty(RpcClientConfigurationConstants.CONFIG_CLIENT_TYPE, "default_loadbalance");
		RpcClient client = null;
		try {
			client = RpcClientFactory.getInstance(props);
			LOGGER.info("Flume Connection established successfully!");
		} catch (FlumeException fe) {
			LOGGER.info("Exception occurred while establishing connection to Flume hosts :: \n" + fe);
		}
		return client;
	}

}
