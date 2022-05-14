package de.unistuttgart.hackathon.taser.AdminService;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Room {
    @Id
    private String roomId;

    private String queueId;

    public Room(final String roomId) {
        this.roomId = roomId;
        this.queueId = roomId;
    }
}
