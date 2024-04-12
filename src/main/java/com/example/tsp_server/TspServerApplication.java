package com.example.tsp_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.tsp_server.model"})
@EnableWebSocket
public class TspServerApplication implements WebSocketConfigurer {

	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TspServerApplication.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatHandler(), "/chat").setAllowedOrigins("*");
	}

	@Bean
	public TextWebSocketHandler chatHandler() {
		return new TextWebSocketHandler() {
			@Override
			public void afterConnectionEstablished(WebSocketSession session) throws Exception {
				sessions.put(session.getId(), session);
			}

			@Override
			protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
				for (WebSocketSession webSocketSession : sessions.values()) {
					if (webSocketSession.isOpen()) {
						webSocketSession.sendMessage(message);
					}
				}
			}

			@Override
			public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
				sessions.remove(session.getId());
			}
		};
	}
}
