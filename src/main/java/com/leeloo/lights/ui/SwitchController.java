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
    
    @MessageMapping("/send")
    public void broadcastSwitch(SwitchDto switchDto, SimpMessageHeaderAccessor ha)  {
    	String ipAddress = (String) ha.getSessionAttributes().get("ip");;
    	String message ="" ;
    	String status = switchDto.getState() ? "ON": "OFF";
    	if (switchDto.getIndex() == -1) {
    		message = String.format("%s set all %s", ipAddress, status);
		}else{
			
			message = String.format("%s set %d %s", ipAddress, switchDto.getIndex() + 1, status);
		}
		this.template.convertAndSend("/topic/console", new ConsoleMessageDto(message) );
		this.template.convertAndSend("/topic/switches", switchDto );
    }

}
