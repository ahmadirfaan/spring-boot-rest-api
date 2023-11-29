
package com.irfaan.restapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: AuthEntryPointJwt.java, v 0.1 2022‐01‐06 16.44 Ahmad Irfaan Hibatullah Exp $$
 */

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        LOGGER.error("Unauthorized error: {}", authException.getMessage());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String, String> map = new HashMap<>();
        map.put("status", "fail");
        map.put("message", "Anda tidak terautorisasi");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(map);
        response.getOutputStream().println(jsonStr);
    }

}