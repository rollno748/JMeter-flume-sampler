package io.perfwise.flume.config;

import java.util.Properties;

import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;

public class FlumeClients {

	public static RpcClient getAvroRpcClient(String flumeAgentHosts, Properties props) {
		String hostname = null;
		int port = 0;
		RpcClient client = RpcClientFactory.getDefaultInstance(hostname, port);
		return client;
	}

	public static RpcClient getThriftRpcClient(String flumeAgentHosts, Properties props) {
		String hostname = null;
		int port = 0;
		RpcClient client = RpcClientFactory.getThriftInstance(hostname, port);
		return client;
	}

	public static RpcClient getFailoverRpcClient(String flumeAgentHosts, Properties props) {
		RpcClient client = RpcClientFactory.getInstance(props);
		return client;
	}

	public static RpcClient getLoadbalancingRpcClient(String flumeAgentHosts, Properties props) {
		RpcClient client = RpcClientFactory.getInstance(props);
		return client;
	}

}
