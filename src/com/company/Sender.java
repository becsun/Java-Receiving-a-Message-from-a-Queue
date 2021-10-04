package com.company;

import javax.jms.*;
import java.util.UUID;

public class Sender implements Runnable {

    private Session session;
    private String destination;

    public Sender(Session session, String destination) {
        this.session = session;
        this.destination = destination;
    }

    public void run() {
        try {
            MessageProducer messageProducer = session.createProducer(session.createQueue(destination));
            long counter = 0;

            while (counter < 10) {
                TextMessage message = session.createTextMessage("Message " + ++counter);
                message.setJMSMessageID(UUID.randomUUID().toString());
                messageProducer.send(message);
                counter++;

            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}