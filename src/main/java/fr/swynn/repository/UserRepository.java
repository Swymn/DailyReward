package fr.swynn.repository;

import fr.swynn.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User createUser(UUID playerUUID);

    Optional<User> getUser(UUID playerUUID);

    void saveUsers();
}
