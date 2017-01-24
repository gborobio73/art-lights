package com.leeloo.lights.ui;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/switch")
public class SwitchController {
	
	@RequestMapping(value="/set", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<?> setSwitch(@RequestBody SwitchDto switchDto) {    	
		//send it to Iot
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put("https://agent.electricimp.com/SmkBeMW_hTdc", new IotSwitch(switchDto.getId(), switchDto.getState()));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
    	return ResponseEntity.ok("OK");
    	
		//throw new LightsException("Oh shit!");
    }
    
    @GetMapping() 
    public SwitchDto getSwitch() {    	
    	return new SwitchDto(1, true);
        
    }
}
