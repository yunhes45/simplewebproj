package com.simpleweb.simpleweb.websocket;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler{
	
	List<WebSocketSession> loginsession = new ArrayList<WebSocketSession>();
	
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		System.out.println("session : " + session);
		System.out.println("msg : " + message);
		
		String msg = message.getPayload();
		JSONObject obj = socketInfo(msg);
		
			for(String key : sessionMap.keySet()) {
				WebSocketSession wss = sessionMap.get(key);
				try {
					wss.sendMessage(new TextMessage(msg));
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
	}
	
	@Override
	public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
		System.out.println("session : " + session);
		System.out.println("msg : " + message);
		
		ByteBuffer byteBuffer = message.getPayload();
		byteBuffer.position(0);
		
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				wss.sendMessage(new BinaryMessage(byteBuffer));

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static JSONObject socketInfo(String socketInfo) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;

			try {
				obj = (JSONObject) parser.parse(socketInfo);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		return obj;
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// socket conn
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		System.out.println("sessionid" + sessionMap.get(session.getId()));
		System.out.println("session = " + session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// socket close
		System.out.println("sessionid" + sessionMap.get(session.getId()));
		System.out.println("session = " + session);
		System.out.println("status : " + status);
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);		
	}
}
