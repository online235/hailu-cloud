package com.hailu.cloud.gateway.config;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhijie
 */
@Component
@Primary
@AllArgsConstructor
@ConfigurationProperties(prefix = "mapping")
public class SwaggerProvider implements SwaggerResourcesProvider {
    public static final String API_URI = "/v2/api-docs";
    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    @Setter
    private Map<String, String> swaggerDocs;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        //取出gateway的route
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        //结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes
                .contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition
                        .getPredicates()
                        .stream()
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> addSwaggerSources(routeDefinition, predicateDefinition, resources))
                );
        return resources;
    }

    private void addSwaggerSources(RouteDefinition routeDefinition, PredicateDefinition predicateDefinition, List<SwaggerResource> resources) {
        String location = predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", API_URI);
        resources.add(swaggerResource(location, swaggerDocs.get(routeDefinition.getId())));
    }

    private SwaggerResource swaggerResource(String location, String desc) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(desc);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}