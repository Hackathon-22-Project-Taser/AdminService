package de.unistuttgart.hackathon.taser.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class AdminService {
    private Logger logger = LoggerFactory.getLogger(AdminService.class);
    private final WebClient webClient;

    public AdminService(){
        logger.info("AdminService constructor");
        webClient =  WebClient.create("http://localhost:8090/");
    }
    public void startQueue(String queueId){
        logger.info("try to start the queue: " + queueId);
        webClient.post()
                .uri("/queue/create/"+queueId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
