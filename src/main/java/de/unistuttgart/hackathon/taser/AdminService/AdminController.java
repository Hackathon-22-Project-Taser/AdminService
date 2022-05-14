package de.unistuttgart.hackathon.taser.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@RestController
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);
    private AdminService service = new AdminService();
    @Autowired
    private AdminRepository adminRepository;

     /**
     * Create a Room with the given identifier
     * @param identifier of the queue to create
     */
    @PostMapping("/admin/room/create/{identifier}")
    public void createRoom(@PathVariable final String identifier){
        logger.info("create Room with identifier (roomNumber):" + identifier);
        service.startQueue(identifier);
        adminRepository.save(new Room(identifier));
    }

    /**
     * Start a lecture
     */
    @PostMapping("admin/realtime/start/{identifier}")
    public void startRealtimeEvent(@PathVariable final String identifier) {
        service.startRealTimeEvent(identifier);
    }

    /**
     * Stop a lecture
     */
    @PostMapping("admin/realtime/stop/{identifier}")
    public void stopRealtimeEvent(@PathVariable final String identifier) {
       service.endRealTimeEvent(identifier);
    }
}
