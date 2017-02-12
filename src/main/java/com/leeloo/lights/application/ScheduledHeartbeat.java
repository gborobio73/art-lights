package com.leeloo.lights.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledHeartbeat {
	@Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedRate = 15000)
    public void reportCurrentTime() {    	
    	this.template.convertAndSend("/topic/heartbeat", "beat");    	
    }
}