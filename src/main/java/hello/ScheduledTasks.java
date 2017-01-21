package hello;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    private SimpMessagingTemplate template;

    @Autowired
    public ScheduledTasks(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
    	String text = String.format("The time is now %s", dateFormat.format(new Date()));
    	System.out.println(text);
    	this.template.convertAndSend("/topic/greetings", new Greeting(text));
    }
}