package com.proyecto02.gateway.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class SCGWGlobalFilter extends AbstractGatewayFilterFactory<SCGWGlobalFilter.Config> {

    final static Logger logger= LoggerFactory.getLogger(SCGWGlobalFilter.class);

    public SCGWGlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        logger.info("inside SCGWGlobalFilter.apply method");
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest().mutate().header("scgw-global-header", Math.random()*10+"").build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    public static class Config {
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

