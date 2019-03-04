package com.upbesports.authentication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
 
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    @Override
    public void commence(final HttpServletRequest request, 
            final HttpServletResponse response, 
            final AuthenticationException authException) throws IOException, ServletException {
        //Authentication failed, send error response.
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
        PrintWriter writer = response.getWriter();
    	if(authException.getCause() != null)
    	{
    		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.println("HTTP Status 500 : " + authException.getMessage());
    	}
    	else
    	{
    		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            writer.println("HTTP Status 401 : " + authException.getMessage());
    	}
    }
     
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("BASE_REALM");
        super.afterPropertiesSet();
    }
}