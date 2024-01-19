package com.snowline.spzx.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Snow
 * @create 2023-12-30 21:40
 */

@Configuration
public class Knife4jConfig {

    @Bean
    public GroupedOpenApi adminApi() {          //创建一个api接口的分组
        return GroupedOpenApi.builder()
                .group("admin-api")             //分组名称
                .pathsToMatch("/admin/**")      //设置接口请求路径规则
                .build();
    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("尚品甄选API接口文档")
                        .version("1.0")
                        .description("尚品甄选API接口文档")
                        .contact(new Contact().name("snowline"))); //设置作者
    }
}
