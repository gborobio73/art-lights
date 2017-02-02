//package com.leeloo.lights.ui;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class ConsoleController {
//
//    //@MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public ConsoleMessageDto sendMessage() throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new ConsoleMessageDto("Hello!");
//    }
//
//}