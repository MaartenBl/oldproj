/*
package com.example.springcloudgatewaydemo2;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder rlb) {
        return rlb
                .routes()
                .route("zonnestroom", routeSpec ->
                        routeSpec
                                .path("/zonnestroom/**")
                                .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/zonnestroom/(?<year>.*)",
                                        "/zonnestroom/${year}"
                                ))
                                .uri("http://localhost:8081/"))
                .route("zongateway", routeSpec ->
                        routeSpec
                                .path("/zongateway") // intercept calls to the /get path
                                .filters(gatewayFilterSpec ->
                                        gatewayFilterSpec
                                                .setPath("/zonnestroom/zongateway"))
                                .uri("http://localhost:8081/"))
                .route(routeSpec ->
                        routeSpec
                            .path("/testlocal") // intercept calls to the /get path
                            .filters(gatewayFilterSpec ->
                                    gatewayFilterSpec
                                            .setPath("/windenergie/all"))
                            .uri("http://localhost:8081")) // forward to httpbin
                .build();
    }
}
*/
