package com.leeloo.lights.ui;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * 
 * @author gonzalo.borobio
 * Electric imp rest API emulator
 */
@RestController
@RequestMapping(value="/switch")
public class ElectricImpController {
	
	@PostMapping
    public ResponseEntity<?> setSwitch(@RequestBody String switchDto, HttpServletRequest request) {    	
    	return ResponseEntity.ok("OK");
     }
    
	@RequestMapping(value="/setall", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<?> setAllSwitches(@RequestBody SwitchState state) {    	
    	return ResponseEntity.ok("OK");
     }
	
    @GetMapping() 
    public Boolean[] getSwitch() {    	
    	Boolean[] switchesDto =  {false, true, false, true, false, false, true, false};
    	return switchesDto;
    }
}
