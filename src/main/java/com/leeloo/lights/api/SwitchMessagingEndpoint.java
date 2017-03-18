package com.leeloo.lights.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.leeloo.lights.application.SwithesRepository;

@Controller
public class SwitchMessagingEndpoint {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private SwithesRepository repository;
    
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
		
		this.repository.store(switchDto, address);
    }

    public class ConsoleMessageDto {

    	private String message;
    	public ConsoleMessageDto() {}	
    	
    	public ConsoleMessageDto(String string) {
    		this.message = string;
    	}
    	public String getString() {
    		return message;
    	}

    }
}
