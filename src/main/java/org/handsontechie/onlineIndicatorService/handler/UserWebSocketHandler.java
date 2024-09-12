package org.handsontechie.onlineIndicatorService.handler;
import org.handsontechie.onlineIndicatorService.service.UserOnlineIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserWebSocketHandler extends TextWebSocketHandler {

    private static Set<WebSocketSession> sessions = new HashSet<>();

    @Autowired
    private UserOnlineIndicatorService userOnlineIndicatorService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        HttpHeaders headers = session.getHandshakeHeaders();
        String userId = headers.getFirst("userId");
        System.out.println("User Id connected: " + userId);
        sessions.add(session);
        userOnlineIndicatorService.updateUserStatus(Integer.valueOf(userId),true);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        HttpHeaders headers = session.getHandshakeHeaders();
        String userId = headers.getFirst("userId");
        System.out.println("User disconnected: " + status);
        userOnlineIndicatorService.updateUserStatus(Integer.valueOf(userId),false);
        sessions.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Message received from client: " + payload);
        for(WebSocketSession curr : sessions) {
            if(curr!=null && curr!=session){
                curr.sendMessage(new TextMessage(payload));
            }
        }

    }

}
