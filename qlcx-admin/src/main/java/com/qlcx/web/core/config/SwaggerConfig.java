package com.qlcx.web.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qlcx.common.config.Global;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 interface configuration
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    /**
     * Create API
     */
    @Bean
    public Docket createRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                // The basic information used to create the API is displayed on the document page (custom display information)
                .apiInfo(apiInfo())
                // Set which interfaces are exposed to Swagger
                .select()
                // Scan all annotated APIs, this way is more flexible
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // Scan swagger annotations in the specified package
                //.apis(RequestHandlerSelectors.basePackage("com.qlcx.project.tool.swagger"))
                // Scan all .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Add summary information
     */
    private ApiInfo apiInfo()
    {
        // Customize with ApiInfoBuilder
        return new ApiInfoBuilder()
                // Set the title
                .title("Title: Ruoyi Management System_Interface Document")
                // description
                .description("Description: Used to manage the personnel information of the companies under the group, including XXX, XXX modules...")
                // author information
                .contact(new Contact(Global.getName(), null, null))
                // version
                .version("Version number:" + Global.getVersion())
                .build();
    }
}