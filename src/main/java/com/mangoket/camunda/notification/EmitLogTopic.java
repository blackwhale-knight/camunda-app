package com.mangoket.camunda.notification;

import com.mangoket.core.dto.common.MailTO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.SerializationUtils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class EmitLogTopic {

    private static final String HOST_NAME = "localhost";
    private static final String EXCHANGE_NAME = "message";
    private static final String ROUTING_KEY = "message";
    private static final String USER_NAME = "dev";
    private static final String PASSWORD = "dev0823";
    private static final String VIRTUAL_HOST = "dev_mango5";
    private static final int PORT_NUMBER = 5672;
    private static final String CONNECTION = "mango5";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);
        factory.setUsername(USER_NAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUAL_HOST);
        factory.setPort(PORT_NUMBER);
        try (Connection connection = factory.newConnection(CONNECTION); Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);

            MailTO mailTO = new MailTO();
            List<String> to = new ArrayList<>();
            to.add("cody.huang@mangoket.com");
            mailTO.setSubject("test message from camunda");
            mailTO.setBodyContent("test message from camunda");
            mailTO.setTo(to);
            
            ByteBuffer requestBuffer = ByteBuffer.wrap(SerializationUtils.serialize(mailTO));

            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, requestBuffer.array());
            System.out.println(" [x] Sent '" + ROUTING_KEY + "':'" + mailTO + "'");
        }
    }
}