package com.mangoket.camunda.notification;

import com.mangoket.core.dto.common.MailTO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Service
public class RabbitMQService implements NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQService.class);

    private static final String HOST_NAME = "localhost";
    private static final String EXCHANGE_NAME = "message";
    private static final String ROUTING_KEY = "message";
    private static final String USER_NAME = "dev";
    private static final String PASSWORD = "dev0823";
    private static final String VIRTUAL_HOST = "dev_mango5";
    private static final int PORT_NUMBER = 5672;
    private static final String CONNECTION = "mango5";

    @Override
    public void sendNotification(Notification notification) {
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
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }
}