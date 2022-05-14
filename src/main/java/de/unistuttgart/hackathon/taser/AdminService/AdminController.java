package de.unistuttgart.hackathon.taser.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Queue;

@RestController
public class AdminController {
    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminRepository adminRepository;

    /**
     * Request the queue of the room with the give identifier
     * @param identifier of a specific room
     * @return queue which is linked to the room
     */
    @GetMapping("/admin/queue/{identifier}")
    public Queue<String> getQueueByRoom(@PathVariable final String identifier){
        return new LinkedList<>();
    }

    /**
     * Create a Room with the given identifier
     * @param identifier of the queue to create
     */
    @PostMapping("/admin/room/create/{identifier}")
    public void createRoom(@PathVariable final String identifier){
        logger.info("create Room with identifier (roomNumber):" + identifier);
        adminRepository.save(new Room(identifier, "Please put the Queue-Id here"));
    }

    /**
     * initialize a Room and a Queue paired to the room
     * @param identifier for the room to create
     */
    @PostMapping("/admin/room/initialize/{identifier}")
    public void initializeRoom(@PathVariable final String identifier){

    }

    /**
     * Start a lecture
     */
    @PostMapping("admin/realtime/start")
    public void startRealtimeEvent() {

    }

    /**
     * Stop a lecture
     */
    @PostMapping("admin/realtime/stop")
    public void stopRealtimeEvent() {

    }
}
