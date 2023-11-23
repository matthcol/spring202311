package org.example.movieapi.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @JmsListener(destination = "${amq.queue}")
    public void receiveMessage(String message) {
        logger.info("MESSAGE RECEIVED: " + message);
    }
}
