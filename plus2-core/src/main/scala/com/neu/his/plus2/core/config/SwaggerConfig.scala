package com.neu.his.plus2.core.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}
import springfox.documentation.builders.{ApiInfoBuilder, PathSelectors, RequestHandlerSelectors}
import springfox.documentation.oas.annotations.EnableOpenApi
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

/**
  * @author tanchy
  */
@Configuration
@EnableOpenApi
class SwaggerConfig {

  @Value("${spring.application.name}") val applicationName:String = null

  @Bean
  def openPlus2Api(): Docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfoBuilder()
      .title(s"$applicationName restful api documentation for HIS Plus2 project")
      .description("This documentation is limited to the use of the front and backend development debugging.<br/><br/> If you encounter problems, please contact the backend development engineer.")
      .version("Development debugging version").build)
    .select.apis(RequestHandlerSelectors.any).paths(PathSelectors.any).build
}
