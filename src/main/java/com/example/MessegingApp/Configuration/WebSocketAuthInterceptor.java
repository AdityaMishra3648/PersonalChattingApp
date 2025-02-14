package com.example.MessegingApp.Configuration;

import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collections;
import java.util.Map;

@CrossOrigin
@Component
public class WebSocketAuthInterceptor implements ChannelInterceptor {
    private final JwtHelper jwtHelper; // Your JWT helper class

    public WebSocketAuthInterceptor(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        // Check if this is a CONNECT frame (initial WebSocket connection)
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = null;

            // Try getting token from session attributes
            Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
            if (sessionAttributes != null) {
                token = (String) sessionAttributes.get("jwtToken");
            }

            // If token not found in session attributes, try getting from headers
            if (token == null) {
                token = accessor.getFirstNativeHeader("Authorization");
                if (token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7); // Remove "Bearer " prefix
                }
            }

            if (token != null && !jwtHelper.isTokenExpired(token)) { // Validate token expiry
                String username = jwtHelper.getUsernameFromToken(token);

                if (username != null) {
                    // Manually create UserDetails object (assuming no extra roles are needed)
                    UserDetails userDetails = new User(username, "", Collections.emptyList());

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // Set authentication in SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // Attach user to WebSocket session
                    accessor.setUser(authentication);
                }
            }
        }

        return message;
    }
}
