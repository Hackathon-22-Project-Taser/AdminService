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

    /**
     * This Method calls the QueueREST Controller and tries to start a queue for a specific Room.
     * @param queueId Id for a specific Room
     */
    public void createQueue(String queueId){
        logger.info("try to start the queue: " + queueId);
        webClient =  WebClient.create("http://queue:8080/");
        webClient.post()
                .uri("/queue/create/"+queueId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /**
     * This Method calls the QueueRest Controller and tries to flush all queues.
     */
    public void flushQueues(){
        logger.info("try to flushes the queues");
        webClient =  WebClient.create("http://queue:8080/");
        webClient.post()
                .uri("/queues/flush")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
