package com.hailu.cloud.api.mall.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhijie
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hailu.cloud"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(Lists.newArrayList(
                        new ParameterBuilder().name("Access-token").modelRef(new ModelRef("string")).parameterType("header").required(false).build()
                ));
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("海露", "https://www.hailu1688.com", "zhijie_94@163.com");
        return new ApiInfoBuilder()
                .title("海露商城")
                .description("接口文档")
                .contact(contact)
                .version("v1.0.0")
                .build();
    }
}