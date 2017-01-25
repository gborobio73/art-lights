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
	
	@RequestMapping(value="/set", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<?> setSwitch(@RequestBody SwitchDto switchDto) {    	
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put("https://agent.electricimp.com/SmkBeMW_hTdc", new IotSwitch(switchDto.getId(), switchDto.getState()));
		} catch (Exception e) {			
			System.out.println(e.getMessage());
			throw new LightsException("Exception when calling agent. " + e.getMessage());
		}
		
    	return ResponseEntity.ok("OK");
     }
    
	@RequestMapping(value="/setall", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<?> setAllSwitches(@RequestBody SwitchState state) {    	
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put("https://agent.electricimp.com/SmkBeMW_hTdc", new IotSwitch(-1, state.getState()));
		} catch (Exception e) {			
			System.out.println(e.getMessage());
			throw new LightsException("Exception when calling agent. " + e.getMessage());
		}
		
    	return ResponseEntity.ok("OK");
     }
	
    @GetMapping() 
    public SwitchDto getSwitch() {    	
    	return new SwitchDto(1, true);
        
    }
}
