package it.vitalegi.workshifts.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "it.vitalegi.workshifts.controller" })
public class MvcConfig implements WebMvcConfigurer {

	@Value("${cors.allowedOrigins}")
	String corsAllowedOrigins;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").setCachePeriod(0);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		CorsRegistration cors = registry.addMapping("/**");
		log.info("Cors - allowed origins: {}", corsAllowedOrigins);
		cors.allowedOrigins(corsAllowedOrigins);
	}

}