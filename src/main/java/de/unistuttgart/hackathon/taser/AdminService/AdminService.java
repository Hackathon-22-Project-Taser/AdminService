package de.unistuttgart.hackathon.taser.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;


@Service
public class AdminService {
    private Logger logger = LoggerFactory.getLogger(AdminService.class);
    private final WebClient webClient;

    public AdminService(){
        logger.info("AdminService constructor");
        webClient =  WebClient.create("http://localhost:8084/");
    }

    /**
     * This Method calls the QueueREST Controller and tries to start a queue for a specific Room.
     * @param queueId Id for a specific Room
     */
    public void createQueue(String queueId){
        logger.info("try to connect to QueueService");
        try {
            webClient.post()
                    .uri("/queue/create/" + queueId)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientRequestException e) {
            logger.error("cant connect to queueService to create a queue: " + e);
        }
    }

    /**
     * This Method calls the QueueRest Controller and tries to flush all queues.
     */
    public void flushQueues(){
        logger.info("try to flushes the queues");
        try {
            webClient.post()
                    .uri("/queues/flush")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientRequestException e) {
            logger.error("cant connect to queueService to flush the queues: " + e);
        }
    }

    /**
     * This Method calls the QueueRest Controller and tries to delete a queue.
     * @param identifier the queue that gets deleted
     */
    public void deleteQueue(final String identifier) {
        logger.info("try to flushes the queues");
        try{
            webClient.delete()
                    .uri("queue/delete/" + identifier)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }catch (WebClientRequestException e){
            logger.error("cant connect to queueService to delete a queue: " + e);
        }

    }
}
