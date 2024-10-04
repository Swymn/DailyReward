package fr.swynn.api;

import fr.swynn.model.Reward;
import fr.swynn.repository.RewardRepository;
import fr.swynn.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class DefaultRewardService implements RewardService {

    private final UserRepository userRepository;
    private final RewardRepository rewardRepository;

    public DefaultRewardService(final UserRepository userRepository, final RewardRepository rewardRepository) {
        this.userRepository = userRepository;
        this.rewardRepository = rewardRepository;
    }

    @Override
    public Optional<Reward> getDailyRewardForPlayer(final UUID playerUUID) {
        var user = userRepository.getUser(playerUUID)
                .orElseGet(() -> userRepository.createUser(playerUUID));

        if (user.hasClaimedDailyReward()) {
            return Optional.empty();
        }

        user.claimedDailyReward();
        return Optional.of(generateRandomItem());
    }

    private Reward generateRandomItem() {
        var random = Math.random() * 100;
        var currentPercentage = 0;
        var rewards = rewardRepository.getRewards();

        for (var reward : rewards) {
            currentPercentage += reward.getPercentage();
            if (random <= currentPercentage) {
                return reward;
            }
        }

        return rewards.stream().filter(Reward::isDefaultReward).findFirst().orElseThrow();
    }
}
