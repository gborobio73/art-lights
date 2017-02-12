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
    	//String ipAddress = request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr(): request.getHeader("X-FORWARDED-FOR");
    	String ipAddress = (String) ha.getSessionAttributes().get("ip");;
    	String message ="" ;
    	if (switchDto.getIndex() == -1) {
    		message = String.format("%s has set all switches to %s", ipAddress, Boolean.toString(switchDto.getState()));
		}else{
			message = String.format("%s has set switch %d to %s", ipAddress, switchDto.getIndex() + 1, Boolean.toString(switchDto.getState()));
		}
		this.template.convertAndSend("/topic/console", new ConsoleMessageDto(message) );
		this.template.convertAndSend("/topic/switches", switchDto );
    }

}
