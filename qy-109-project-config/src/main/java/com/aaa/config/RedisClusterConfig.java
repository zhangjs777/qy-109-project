package com.aaa.config;

import com.aaa.properties.RedisCluterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;


import java.util.HashSet;
import java.util.Set;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-10 15:02
 **/
@Configuration
public class RedisClusterConfig {
        @Autowired
        private RedisCluterProperties redisCluterProperties;
         @Bean
        public JedisCluster getJedisCluster(){
                String nodes = redisCluterProperties.getNodes();
                String[] split = nodes.split(",");
                Set<HostAndPort> hostAndPortSet= new HashSet<HostAndPort>();
                for (String hostPort : split){
                        String[] split1 = hostPort.split(":");
                        HostAndPort hostAndPort= new HostAndPort(split1[0],Integer.parseInt(split1[1]));
                        hostAndPortSet.add(hostAndPort);
                }
                return new JedisCluster(hostAndPortSet,redisCluterProperties.getCommandTimeout(),redisCluterProperties.getMaxAttempts());
        }
}
