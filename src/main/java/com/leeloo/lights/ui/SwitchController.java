package com.leeloo.lights.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SwitchController {
	
	@Autowired
	private SimpMessagingTemplate template;
    
    @MessageMapping("/broadcast")
    public void broadcastSwitch(SwitchDto switchDto, SimpMessageHeaderAccessor ha)  {
    	String address = (String) ha.getSessionAttributes().get("port");;
    	String message ="" ;
    	String status = switchDto.getState() ? "ON": "OFF";
    	if (switchDto.getIndex() == -1) {
    		message = String.format("%s set all %s", address, status);
		}else{
			
			message = String.format("%s set %d %s", address, switchDto.getIndex() + 1, status);
		}
		this.template.convertAndSend("/topic/console", new ConsoleMessageDto(message) );
		this.template.convertAndSend("/topic/switches", switchDto );
    }

}
