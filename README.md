# Jmeter-flume-sampler
Jmeter plugin to push messages or data to flume to help Hadoop/Bigdata testing

## Introduction
Jmeter Plugins to push events to flume.

Goal:
We are trying to create a sample Event and send it to Flume (Source) using Jmeter. 

Flume supports 2 types of Clients
1. AVRO RPC Client(Default)
2. Thrift RPC Client


To send message using Flume client, User has to specify the Source Type which is configured in the Flume Agent. Typical below setting in Flume Agent

##################### Rpc Client - Single Host Supported #########################

	client.type = default (for avro) or thrift (for thrift)
	hosts = h1                           # default client accepts only 1 host, additional hosts will be ignored
	hosts.h1 = localhost:8800			 # host and port must both be specified, it doesn't has a default port value
	batch-size = 100                     # Must be >=1 (default: 100)
	connect-timeout = 20000              # Must be >=1000 (default: 20000)
	request-timeout = 20000              # Must be >=1000 (default: 20000)

The Flume Client SDK also supports an RpcClient which load-balances among multiple hosts.


################################ LoadBalancing Rpc Client #########################

	client.type = default_loadbalance
	hosts = h1 h2 h3                     # At least 2 hosts are required
	hosts.h1 = host1.example.org:41414
	hosts.h2 = host2.example.org:41414
	hosts.h3 = host3.example.org:41414
	backoff = false                      # client should back-off from (i.e. temporarily blacklist) a failed host(default: false).
	maxBackoff = 0                       # Max timeout in ms that a will remain inactive due to failure with host (defaultis 0, effectively becomes 30000)
	host-selector = round_robin          # The host selection strategy used when load-balancing among hosts
	                                     # (default: round_robin) Other values are include "random"
	batch-size = 100                     # Must be >=1 (default: 100)
	connect-timeout = 20000              # Must be >=1000 (default: 20000)
	request-timeout = 20000              # Must be >=1000 (default: 20000)

################################ Failover Rpc Client #############################

        client.type = default_failover
        hosts = h1 h2 h3                     # At least 2 hosts are required
        hosts.h1 = host1.example.org:41414
        hosts.h2 = host2.example.org:41414
        hosts.h3 = host3.example.org:41414
	    max-attempts = 3                     # Must be >=0 (default: number of hosts specified, 3 in this case)
        backoff = false                      # client should back-off from (i.e. temporarily blacklist) a failed host(default: false).
        maxBackoff = 0                       # Max timeout in ms that a will remain inactive due to failure with host (defaultis 0, effectively becomes 30000) 
                                             # (default: round_robin) Other values are include "random"
        batch-size = 100                     # Must be >=1 (default: 100)
        connect-timeout = 20000              # Must be >=1000 (default: 20000)
        request-timeout = 20000              # Must be >=1000 (default: 20000)


Reference:
https://flume.apache.org/FlumeDeveloperGuide.html#client

