package fr.swynn.repository;

import fr.swynn.model.User;

import java.util.Optional;
import java.util.UUID;

public class DefaultUserRepository extends Repository<User> implements UserRepository {

    public DefaultUserRepository() {
        super("users.json", User.class);
    }

    @Override
    public Optional<User> getUser(final UUID playerUUID) {
        return data.stream()
                .filter(user -> user.getPlayerUUUID().equals(playerUUID))
                .findFirst();
    }

    @Override
    public User createUser(final UUID playerUUID) {
        var user = new User(playerUUID, null);
        data.add(user);

        return user;
    }

    @Override
    public void saveUsers() {
        serialize();
    }
}
