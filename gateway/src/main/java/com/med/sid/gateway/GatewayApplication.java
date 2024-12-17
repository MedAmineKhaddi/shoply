package com.med.sid.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    // The first way to configure is with application.yml file
    // This is the second method to configure routes in gateway with a static way
    //@Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                //.route(r -> r.path("/customers/**").uri("http://localhost:8081"))
                .route(r -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
                //.route(r -> r.path("/products/**").uri("http://localhost:8082"))
                .route(r -> r.path("/products/**").uri("LB://INVENTORY-SERVICE"))
                .build();
    }

    // with a dynamic way
    @Bean
    public DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp) {
        return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
    }

}
