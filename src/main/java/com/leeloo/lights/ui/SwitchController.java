package com.leeloo.lights.ui;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
//@RequestMapping(value="/switch")
@Controller
public class SwitchController {
	
	@Autowired
	private SimpMessagingTemplate template;
//	@Autowired
//	private HttpServletRequest request;

//	
//	private static String server ="https://agent.electricimp.com/SmkBeMW_hTdc";
//	//private static String server ="https://agent.electricimp.com/SmkBeMW_c";
//	@RequestMapping(value="/set", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
//    public ResponseEntity<?> setSwitch(@RequestBody SwitchDto switchDto, HttpServletRequest request) {    	
//		RestTemplate restTemplate = new RestTemplate();
//		try {
//			restTemplate.postForLocation(server, new IotSwitch(switchDto.getIndex(), switchDto.getState()));
//			
//			String ipAddress = request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr(): request.getHeader("X-FORWARDED-FOR");
//			
//			String message = String.format("%s has set switch %d to %s", ipAddress, switchDto.getIndex() + 1, Boolean.toString(switchDto.getState()));
//			
//			this.template.convertAndSend("/topic/console", new ConsoleMessageDto(message) );
//			this.template.convertAndSend("/topic/switches", switchDto );
//			
//		} catch (Exception e) {			
//			System.out.println(e.getMessage());
//			throw new LightsException("Exception when calling agent. " + e.getMessage());
//		}
//		
//    	return ResponseEntity.ok("OK");
//     }
//    
//	@RequestMapping(value="/setall", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE) 
//    public ResponseEntity<?> setAllSwitches(@RequestBody SwitchState state) {    	
//		RestTemplate restTemplate = new RestTemplate();
//		try {
//			restTemplate.put(server, new IotSwitch(-1, state.getState()));
//		} catch (Exception e) {			
//			System.out.println(e.getMessage());
//			throw new LightsException("Exception when calling agent. " + e.getMessage());
//		}
//		
//    	return ResponseEntity.ok("OK");
//     }
	
//    @GetMapping() 
//    public Boolean[] getSwitch() {    	
//    	RestTemplate restTemplate = new RestTemplate();
//		try {
//			String switches;
//			switches= restTemplate.getForObject(server, String.class);
//			String[] arraySwitches = switches.replace("[", "").replace("]", "").trim().split(",");
//			Boolean[] switchesDto = new Boolean[arraySwitches.length];
//			for(int i =0; i< arraySwitches.length; i++){
//				switchesDto[i] = Boolean.parseBoolean(arraySwitches[i].trim());
//			}
//			return switchesDto; 
//		} catch (Exception e) {			
//			System.out.println(e.getMessage());
//			throw new LightsException("Exception when calling agent. " + e.getMessage());
//		}        
//    }
    
    @MessageMapping("/send")
    public void broadcastSwitch(SwitchDto switchDto, SimpMessageHeaderAccessor ha)  {
    	//String ipAddress = request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr(): request.getHeader("X-FORWARDED-FOR");
    	String ipAddress = (String) ha.getSessionAttributes().get("ip");;
		
		String message = String.format("%s has set switch %d to %s", ipAddress, switchDto.getIndex() + 1, Boolean.toString(switchDto.getState()));
		
		this.template.convertAndSend("/topic/console", new ConsoleMessageDto(message) );
		this.template.convertAndSend("/topic/switches", switchDto );
    }
    
    //heartbeat
//    @MessageMapping("/ping")
//    @SendTo("/topic/pong")
//    public String heartbeat(String ping) throws Exception {
//        //Thread.sleep(1000); // simulated delay
//        return "pong";
//    }
}
