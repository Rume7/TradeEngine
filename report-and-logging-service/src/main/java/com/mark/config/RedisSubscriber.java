package com.mark.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber implements MessageListener {

   // public static List<String> messageList = new ArrayList<String>();
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Message received: " + new String(message.getBody()));
    }
}
