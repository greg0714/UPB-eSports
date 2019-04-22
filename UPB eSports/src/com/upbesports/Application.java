package com.upbesports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import com.upbesports.auth.CustomUserAuthenticationConverter;

@SpringBootApplication
@EnableResourceServer
@EnableWebSecurity
@PropertySource(value={"classpath:/main/resources/application.properties"})
public class Application extends SpringBootServletInitializer 
{
    public static void main(String[] args) 
    {
    	
        SpringApplication.run(Application.class, args);
    }
//    
//    @Bean
//    JwtDecoder jwtDecoder() {
//    	String jwkSetUri = "https://auth.upbesports.com/oxauth/restv1/jwks";
//        NimbusJwtDecoderJwkSupport jwtDecoder = new NimbusJwtDecoderJwkSupport(jwkSetUri);
//        return jwtDecoder;
//    }
//    
    @Configuration
    static class ResourceServerConfig extends ResourceServerConfigurerAdapter  
    {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.anonymous()
            	.and()
            		.authorizeRequests().antMatchers(HttpMethod.POST).authenticated()
            	.and()
            		.authorizeRequests().antMatchers("/**").permitAll();
        }
        
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
             resources.tokenServices(tokenService());
             resources.resourceId("@!32F2.B458.9918.7996!0001!D0B3.8E33!0008!4A76.F928.A912.3956");
        }
        
        @Primary
        @Bean
        public RemoteTokenServices tokenService() {
            RemoteTokenServices tokenService = new RemoteTokenServices();
            DefaultAccessTokenConverter converter = new DefaultAccessTokenConverter();
            converter.setUserTokenConverter(new CustomUserAuthenticationConverter());
            
            tokenService.setCheckTokenEndpointUrl("https://auth.upbesports.com/oxauth/restv1/introspection");
            tokenService.setClientId("@!32F2.B458.9918.7996!0001!D0B3.8E33!0008!4A76.F928.A912.3956");
            tokenService.setClientSecret("Panther#");
            tokenService.setAccessTokenConverter(converter);
            return tokenService;
        }
    }
}