package fr.swynn.repository;

import fr.swynn.model.Reward;
import org.bukkit.Material;

import java.util.List;

public class DefaultRewardRepository extends Repository<Reward> implements RewardRepository {

    public DefaultRewardRepository() {
        super("rewards.json", Reward.class);

        if (data.isEmpty()) {
            createReward(Material.EMERALD, 64, 22, true);
            createReward(Material.GOLD_INGOT, 32, 17, false);
            createReward(Material.DIAMOND, 16, 15, false);
            createReward(Material.OBSIDIAN, 32, 12, false);
            createReward(Material.GOLDEN_CARROT, 64, 10, false);
            createReward(Material.ENDER_CHEST, 2, 6, false);
            createReward(Material.ENCHANTED_BOOK, 1, "UNBREAKING", 3, 4, false);
            createReward(Material.ENCHANTED_BOOK, 1, "MENDING", 1, 4, false);
            createReward(Material.ENCHANTED_BOOK, 1, "SILK_TOUCH", 1, 3, false);
            createReward(Material.ENCHANTED_BOOK, 1, "EFFICIENCY", 5, 2, false);
            createReward(Material.NETHERITE_INGOT, 1, 1, false);
            createReward(Material.TRIDENT, 1, 1, false);
            createReward(Material.ELYTRA, 1, 1, false);
        }
    }

    @Override
    public Reward createReward(final Material material, final int amount, final String enchantment, final int level, final int percentage, final boolean defaultReward) {
        var reward = new Reward(material, amount, percentage, defaultReward, enchantment, level);
        data.add(reward);

        return reward;
    }

    @Override
    public Reward createReward(final Material material, final int amount, final int percentage, final boolean defaultReward) {
        return createReward(material, amount, null, -1, percentage, defaultReward);
    }

    @Override
    public List<Reward> getRewards() {
        return data;
    }

    @Override
    public void saveRewards() {
        serialize();
    }
}
