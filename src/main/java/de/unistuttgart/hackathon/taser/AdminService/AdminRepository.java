package de.unistuttgart.hackathon.taser.AdminService;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Room, String> {
}
