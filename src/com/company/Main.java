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
                conn.setClientID(args[0]);
                conn.start();



                new Thread(new Receiver(conn.createSession(true,
                        Session.SESSION_TRANSACTED), "Audit")).start();
                new Thread(new Sender(conn.createSession(false, Session.CLIENT_ACKNOWLEDGE), "Audit")).start();





            }


    }


