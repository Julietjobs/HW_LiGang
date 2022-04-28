package com.tjulab.checkin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tjulab.checkin.controller"))
                .build();

    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("考勤管理系统Restful Apis 详细说明文档")
                .description("说明考勤管理系统的Apis")
                .version("1.0")
                .contact(new Contact("Tju_Zyh" , "https://github.com/TjuZyh" , "tju_zhaoyihan@163.com"))
                .build();
    }


}
