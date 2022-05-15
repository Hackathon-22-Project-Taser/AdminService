package de.unistuttgart.hackathon.taser.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);
    private AdminService service = new AdminService();
    @Autowired
    private AdminRepository adminRepository;

    /**
     * Create a Room with the given identifier
     *
     * @param identifier of the queue to create
     */
    @PostMapping("/admin/room/create/{identifier}")
    public Room createRoom(@PathVariable final String identifier) {
        logger.info("create Room with identifier (roomNumber):" + identifier);
        service.createQueue(identifier);
        Room room = new Room(identifier);
        adminRepository.save(room);
        return room;
    }

    /**
     * The getter returns all rooms currently saved in the database
     *
     * @return
     */
    @GetMapping("/admin/rooms")
    public List<Room> getRooms() {
        logger.info("try to get all rooms");
        return adminRepository.findAll();
    }

    /**
     * This Method removes a room with a specific identifier
     * @param identifier room that gets deleted
     * @return deleted room
     */
    @DeleteMapping("/admin/room/delete/{identifier}")
    public Room deleteRoom(@PathVariable final String identifier) {
        logger.info("try to delete room: " + identifier);
        if (adminRepository.findById(identifier).isEmpty()) {
            Room deletedRoom = adminRepository.findById(identifier).get();
            adminRepository.deleteById(identifier);
            return deletedRoom;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Room with ID %s not found!", identifier));
    }

    /**
     * Flushes the Queues
     */
    @PostMapping("admin/realtime/start/")
    public void flushQueues() {
        service.flushQueues();
    }
}
