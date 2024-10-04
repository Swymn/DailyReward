package fr.swynn.api;

import fr.swynn.model.Reward;

import java.util.Optional;
import java.util.UUID;

public interface RewardService {

    /**
     * Get the daily reward for a player
     * @param playerUUID The UUID of the player
     * @return The ItemStack representing the daily reward
     */
    Optional<Reward> getDailyRewardForPlayer(UUID playerUUID);
}
