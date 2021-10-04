package com.company;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSslConnectionFactory;

import javax.jms.Connection;
import javax.jms.Session;

public class Main {


            public static void main(String... args) throws Exception {
                ActiveMQConnectionFactory connFact = new ActiveMQConnectionFactory("tcp://localhost:61616");
                connFact.setConnectResponseTimeout(10000);
                Connection conn = connFact.createConnection("user", "password");
                conn.setClientID("PointToPointOneWayTraditional");
                conn.start();

                new Thread(new Receiver(conn.createSession(false, Session.CLIENT_ACKNOWLEDGE), "Queue.PointToPoint.OneWay.Traditional")).start();
                new Thread(new Sender(conn.createSession(false, Session.CLIENT_ACKNOWLEDGE), "Queue.PointToPoint.OneWay.Traditional")).start();
            }


    }


