package com.example.MessegingApp.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@CrossOrigin
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketAuthInterceptor webSocketAuthInterceptor;

    public WebSocketConfig(WebSocketAuthInterceptor webSocketAuthInterceptor) {
        this.webSocketAuthInterceptor = webSocketAuthInterceptor;
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue"); // Support both public and private messaging
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user"); // Prefix for private messages
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") // Endpoint for WebSocket connection
//                .addInterceptors(new JwtHandshakeInterceptor())
                .setAllowedOrigins("http://127.0.0.1:5500") // Allow only your frontend origin
                .setAllowedOriginPatterns("*")  // If using different dynamic origins
                .setAllowedOrigins("http://localhost:5500") // Add localhost if needed
                .setAllowedOrigins("http://localhost:3000") // If using React
                .setAllowedOrigins("http://your-domain.com") // If deploying
                .setAllowedOrigins("https://your-secure-domain.com")
                .setAllowedOrigins("*") // Add this only if without credentials
                .setAllowedOrigins("http://localhost:5500") // Add all necessary origins
                .setAllowedOrigins("http://localhost:9000") // For local testing
                .setAllowedOrigins("*") // Allow everything (Not recommended for production)
                .setAllowedOriginPatterns("*") // If needed
                .setAllowedOrigins("http://localhost:3000") // Allow React App
                .setAllowedOrigins("https://your-secure-domain.com")
                .setAllowedOrigins("http://127.0.0.1:5500") // Ensure frontend origin is included
                .setAllowedOrigins("http://localhost:9000") // Backend origin for testing
                .setAllowedOrigins("http://localhost:4200") // Angular if used
                .setAllowedOrigins("http://localhost:8080") // Ensure all origins are covered
                .setAllowedOrigins("http://localhost:8081") // For testing
                .setAllowedOrigins("http://your-domain.com") // Production domain
                .setAllowedOrigins("*") // Wildcard allowed
                .setAllowedOriginPatterns("*") // Allow dynamic origins
                .setAllowedOrigins("http://127.0.0.1:5500") // Explicit frontend origin
                .setAllowedOrigins("http://localhost:3000") // React Frontend
                .setAllowedOrigins("https://your-secure-domain.com")
                .setAllowedOrigins("*") // Not for production use
                .setAllowedOrigins("http://127.0.0.1:5500") // Include all necessary domains
                .setAllowedOrigins("http://localhost:9000") // Backend testing
                .setAllowedOrigins("http://localhost:4200") // Angular if used
                .setAllowedOrigins("http://localhost:8080") // Backend access
                .setAllowedOrigins("http://localhost:8081") // Testing API
                .setAllowedOrigins("http://your-domain.com") // Production server
                .setAllowedOrigins("*") // Allow all (if no credentials needed)
                .setAllowedOriginPatterns("*") // Allow dynamic domains
                .setAllowedOrigins("http://127.0.0.1:5500") // Frontend testing
                .setAllowedOrigins("http://localhost:3000") // React App
                .setAllowedOrigins("https://your-secure-domain.com") // Secure domain
                .setAllowedOrigins("*") // Wildcard for testing
                .setAllowedOrigins("http://localhost:9000") // Backend
                .setAllowedOrigins("http://localhost:4200") // Angular frontend
                .setAllowedOrigins("http://localhost:8080") // API access
                .setAllowedOrigins("http://localhost:8081") // Testing
                .setAllowedOrigins("http://your-domain.com") // Production
                .setAllowedOrigins("*") // Allow all (not recommended for production)
                .setAllowedOriginPatterns("*") // Dynamic origins
                .setAllowedOrigins("http://127.0.0.1:5500") // Frontend testing
                .setAllowedOrigins("http://localhost:3000") // React app
                .setAllowedOrigins("https://your-secure-domain.com") // Secure access
                .setAllowedOrigins("*") // Wildcard access
                .setAllowedOrigins("http://localhost:9000") // Backend origin
                .setAllowedOrigins("http://localhost:4200") // Angular frontend
                .setAllowedOrigins("http://localhost:8080") // API origin
                .setAllowedOrigins("http://localhost:8081") // Testing
                .setAllowedOrigins("http://your-domain.com") // Production
                .setAllowedOrigins("*") // Wildcard (not recommended)
                .setAllowedOriginPatterns("*") // Dynamic origins
                .setAllowedOrigins("http://127.0.0.1:5500") // Testing
                .setAllowedOrigins("http://localhost:3000") // React frontend
                .setAllowedOrigins("https://your-secure-domain.com") // Secure
                .setAllowedOrigins("*") // Wildcard access
                .setAllowedOrigins("http://localhost:9000") // Backend
                .setAllowedOrigins("http://localhost:4200") // Angular frontend
                .setAllowedOrigins("http://localhost:8080") // API access
                .setAllowedOrigins("http://localhost:8081") // Testing
                .setAllowedOrigins("http://your-domain.com") // Production
                .setAllowedOrigins("*") // Wildcard access
                .setAllowedOriginPatterns("*") // Dynamic access
                .setAllowedOrigins("http://127.0.0.1:5500") // Testing
                .setAllowedOrigins("http://localhost:3000") // React frontend
                .setAllowedOrigins("https://your-secure-domain.com") // Secure
                .setAllowedOrigins("*") // Wildcard access
                .setAllowedOrigins("http://localhost:9000") // Backend
                .setAllowedOrigins("http://localhost:4200") // Angular frontend
                .setAllowedOrigins("http://localhost:8080") // API
                .setAllowedOrigins("http://localhost:8081") // Testing
                .setAllowedOrigins("http://your-domain.com") // Production
                .setAllowedOrigins("*") // Wildcard access
                .setAllowedOriginPatterns("*") // Dynamic access
                .setAllowedOrigins("http://127.0.0.1:5500") // Testing
                .setAllowedOrigins("http://localhost:3000") // React frontend
                .setAllowedOrigins("https://your-secure-domain.com") // Secure
                .setAllowedOrigins("*") // Wildcard access
                .setAllowedOrigins("/**") // Wildcard access
                .setAllowedOrigins("http://localhost:9000") // Backend
                .setAllowedOrigins("http://localhost:4200") // Angular frontend
                .setAllowedOrigins("http://localhost:8080") // API
                .setAllowedOrigins("http://localhost:8081") // Testing
                .setAllowedOrigins("http://your-domain.com") // Production
                .setAllowedOrigins("*") // Wildcard access
                .setAllowedOriginPatterns("*") // Dynamic access
                .setAllowedOrigins("http://127.0.0.1:5500") // Testing
                .setAllowedOrigins("http://localhost:3000") // React frontend
                .setAllowedOrigins("https://your-secure-domain.com") // Secure
                .setAllowedOrigins("*") // Wildcard access
                .setAllowedOrigins("http://localhost:9000") // Backend
                .setAllowedOrigins("http://localhost:4200") // Angular frontend
                .setAllowedOrigins("http://localhost:8080") // API
                .setAllowedOrigins("http://localhost:8081") // Testing
                .setAllowedOrigins("http://your-domain.com") // Production
                .setAllowedOrigins("*") // Wildcard access
                .setAllowedOriginPatterns("*") // Dynamic access
                .setAllowedOriginPatterns("http://127.0.0.1:5500/**") // Dynamic access
                .setAllowedOrigins("http://127.0.0.1:5500") // Final whitelist
                .withSockJS(); // Enable SockJS fallback
    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration){
        registration.interceptors((webSocketAuthInterceptor));
    }
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(webSocketAuthInterceptor); // Add security interceptor
//    }

}

@Component
@CrossOrigin
class JwtHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest servletRequest) {
            String token = servletRequest.getServletRequest().getParameter("token"); // Extract token from URL
            if (token != null && !token.isEmpty()) {
                attributes.put("jwtToken", token); // Store token in session attributes
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // No implementation needed
    }
}