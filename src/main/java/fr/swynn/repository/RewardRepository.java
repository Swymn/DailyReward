package fr.swynn.repository;

import fr.swynn.model.Reward;
import org.bukkit.Material;

import java.util.List;

public interface RewardRepository {
    Reward createReward(Material material, int amount, String enchantment, int level, int percentage, boolean defaultReward);

    Reward createReward(Material material, int amount, int percentage, boolean defaultReward);

    List<Reward> getRewards();

    void saveRewards();
}
