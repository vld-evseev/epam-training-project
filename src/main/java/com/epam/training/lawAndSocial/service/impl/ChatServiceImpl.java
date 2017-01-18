package com.epam.training.lawAndSocial.service.impl;

import com.epam.training.lawAndSocial.config.Config;
import com.epam.training.lawAndSocial.model.Message;
import com.epam.training.lawAndSocial.service.ChatService;
import com.epam.training.lawAndSocial.service.model.MessageHistoryService;
import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;


public class ChatServiceImpl extends WebSocketServer implements ChatService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ChatServiceImpl.class);

    private final Config config;
    private final Gson gson;
    private final MessageHistoryService messageHistoryService;

    private final Map<String, WebSocket> conns;

    @Inject
    public ChatServiceImpl(Config config, Gson gson, MessageHistoryService messageHistoryService) {
        super(new InetSocketAddress(Integer.parseInt(config.getWebsocketPort())));
        this.config = config;
        this.gson = gson;
        this.messageHistoryService = messageHistoryService;
        this.conns = new HashMap<>();
        LOGGER.debug("Chat server initialized on port: {}", Integer.parseInt(config.getWebsocketPort()));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        LOGGER.debug("New connection from {}", conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        LOGGER.debug("Closed connection to {}", conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String msg) {
        final Message message = gson.fromJson(msg, Message.class);
        LOGGER.debug("JSON message from client: {}", message.toString());
        switch (message.getType()) {
            case "enter": {
                final String sessionId = message.getSessionId() + "#" + message.getFromUserId();
                LOGGER.debug("sessionId onEnter: {}", sessionId);
                conns.put(sessionId, conn);
                LOGGER.debug("user {} was added to connection set, current size: {}", message.getFromUserId(), conns.size());
                break;
            }
            case "close": {
                final String sessionId = message.getSessionId() + "#" + message.getFromUserId();
                LOGGER.debug("sessionId onClose: {}", sessionId);
                conns.remove(sessionId);
                LOGGER.debug("user {} was removed from connection set, current size: {}", message.getFromUserId(), conns.size());
                break;
            }
            case "message": {
                if (message.getText() == null || message.getText().isEmpty()) {
                    break;
                }
                messageHistoryService.add(message);

                final String fromUserKey = message.getSessionId() + "#" + message.getFromUserId();
                final String toUserKey = message.getSessionId() + "#" + message.getToUserId();
                LOGGER.debug("key onMessage from: {}", fromUserKey);
                LOGGER.debug("key onMessage to: {}", toUserKey);

                final WebSocket fromWebSocket = conns.get(fromUserKey);
                final WebSocket toWebSocket = conns.get(toUserKey);
                if (fromWebSocket != null && fromWebSocket.isOpen()) {
                    fromWebSocket.send(msg);
                }
                if (toWebSocket != null && toWebSocket.isOpen()) {
                    toWebSocket.send(msg);
                }
                break;
            }
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        LOGGER.error("Websocket caused exception: {}", ex.getMessage());
        if (conn != null) {
            LOGGER.error("ERROR from: {}", conn.getRemoteSocketAddress().getAddress().getHostAddress());
        }
    }

    @Override
    public void startup() {
        this.start();
        LOGGER.debug("chat started");
    }

    @Override
    public void shutdown() {
        try {
            conns.clear();
            this.stop();
            LOGGER.debug("chat stopped");
        } catch (IOException e) {
            LOGGER.error("IOException: {}", e.getMessage());
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException: {}", e.getMessage());
        }
    }
}
