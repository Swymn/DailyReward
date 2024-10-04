package fr.swynn.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class User {

    private UUID playerUUUID;
    private Date lastClaimedDate;

    @JsonCreator
    public User(
            @JsonProperty("playerUUID") final UUID playerUUUID,
            @JsonProperty("lastClaimedData") final Date lastClaimedDate
    ) {
        this.playerUUUID = playerUUUID;
        this.lastClaimedDate = lastClaimedDate;
    }

    public UUID getPlayerUUUID() {
        return playerUUUID;
    }

    public Date getLastClaimedDate() {
        return lastClaimedDate;
    }

    public void setPlayerUUUID(UUID playerUUUID) {
        this.playerUUUID = playerUUUID;
    }

    public void setLastClaimedDate(Date lastClaimedDate) {
        this.lastClaimedDate = lastClaimedDate;
    }

    public void claimedDailyReward() {
        lastClaimedDate = new Date();
    }

    public boolean hasClaimedDailyReward() {
        return false;
        /*if (lastClaimedDate == null) {
            return false;
        }
        var now = java.time.LocalDate.now();
        var lastClaimed = lastClaimedDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        return lastClaimed.getYear() == now.getYear() &&
                lastClaimed.getMonthValue() == now.getMonthValue() &&
                lastClaimed.getDayOfMonth() == now.getDayOfMonth();*/
    }
}
