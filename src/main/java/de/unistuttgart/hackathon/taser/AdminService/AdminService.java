package de.unistuttgart.hackathon.taser.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class AdminService {
    private Logger logger = LoggerFactory.getLogger(AdminService.class);
    private WebClient webClient;

    public AdminService(){
        logger.info("AdminService constructor");
    }

    public void startQueue(String queueId){
        logger.info("try to start the queue: " + queueId);
        webClient =  WebClient.create("http://localhost:8090/");
        webClient.post()
                .uri("/queue/create/"+queueId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public void startRealTimeEvent(String queueId){
        webClient =  WebClient.create("http://localhost:8090/");
        logger.info("try to start the real time event: " + queueId);
    }

    public void endRealTimeEvent(String queueId){
        webClient =  WebClient.create("http://localhost:8090/");
        logger.info("try to end the real time event: " + queueId);
    }
}
