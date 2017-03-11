package com.leeloo.lights.application;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class IpHandshakeInterceptor implements HandshakeInterceptor {
	@Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
            WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

		String ipAddress = request.getHeaders().getFirst("X-FORWARDED-FOR");
		if (ipAddress == null) {
			   ipAddress = request.getRemoteAddress().toString();
		}
        // Set ip attribute to WebSocket session
		String port =Integer.toString(request.getRemoteAddress().getPort());
		//String ip = request.getLocalAddress().getAddress().getHostAddress();
        attributes.put("port", port);
        attributes.put("ip", ipAddress);

        return true;
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
            WebSocketHandler wsHandler, Exception exception) {          
    }

	
}