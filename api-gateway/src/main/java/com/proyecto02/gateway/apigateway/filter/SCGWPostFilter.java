package com.proyecto02.gateway.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SCGWPostFilter extends AbstractGatewayFilterFactory<SCGWPostFilter.Config> {

    final static Logger logger= LoggerFactory.getLogger(SCGWPostFilter.class);

    public SCGWPostFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        logger.info("inside SCGWPostFilter.apply method...");
        return(exchange, chain)->{
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                ServerHttpResponse response = exchange.getResponse();
                HttpHeaders headers = response.getHeaders();
                headers.forEach((k,v)->{
                    System.out.println(k + " : " + v);
                });
            }));
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
