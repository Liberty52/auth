package com.liberty52.auth.global.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;

public class Events {

    private static ApplicationEventPublisher publisher;

    public static void setPublisher(ApplicationEventPublisher publisher){
        Events.publisher = publisher;
    }

    public static void raise(Event event) {
        if(publisher == null)
            return;
        publisher.publishEvent(event);
    }

}
