package org.acme.websockets;

import io.quarkus.websockets.next.OnClose;
import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.quarkus.websockets.next.WebSocketConnection;
import io.quarkus.runtime.annotations.RegisterForReflection;  // 导入注解

import jakarta.inject.Inject;

@WebSocket(path = "/chat/{username}")  // 定义 WebSocket 路径，并支持路径参数 {username}
public class ChatWebSocket {

    // 定义消息类型
    public enum MessageType {USER_JOINED, USER_LEFT, CHAT_MESSAGE}

    // 使用 @RegisterForReflection 注解
    @RegisterForReflection  // 启用反射支持
    public record ChatMessage(MessageType type, String from, String message) {
    }

    @Inject
    WebSocketConnection connection;  // 注入 WebSocket 连接

    @OnOpen(broadcast = true)  // 当有新客户端连接时调用
    public ChatMessage onOpen() {
        return new ChatMessage(MessageType.USER_JOINED, connection.pathParam("username"), null);
    }

    @OnClose  // 当客户端断开连接时调用
    public void onClose() {
        ChatMessage departure = new ChatMessage(MessageType.USER_LEFT, connection.pathParam("username"), null);
        connection.broadcast().sendTextAndAwait(departure);
    }

    @OnTextMessage(broadcast = true)  // 当客户端发送消息时调用
    public ChatMessage onMessage(ChatMessage message) {
        return message;
    }
}
