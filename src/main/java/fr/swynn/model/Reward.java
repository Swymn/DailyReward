package fr.swynn.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Reward {

    private Material material;
    private int amount;
    private int percentage;
    private boolean defaultReward;

    private String enchantment;
    private int level;

    @JsonCreator
    public Reward(
            @JsonProperty("material") final Material material,
            @JsonProperty("amount") final int amount,
            @JsonProperty("percentage") final int percentage,
            @JsonProperty("defaultReward") final boolean defaultReward,
            @JsonProperty("enchantment") final String enchantment,
            @JsonProperty("level") final int level
    ) {
        this.material = material;
        this.amount = amount;
        this.percentage = percentage;
        this.defaultReward = defaultReward;

        this.enchantment = enchantment;
        this.level = level;
    }

    public Reward(final Material material, final int amount, final int percentage, final boolean defaultReward) {
        this(material, amount, percentage, defaultReward, null, -1);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(String enchantment) {
        this.enchantment = enchantment;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public boolean isDefaultReward() {
        return defaultReward;
    }

    public void setDefaultReward(boolean defaultReward) {
        this.defaultReward = defaultReward;
    }

    public ItemStack toItemStack() {
        var itemStack = new ItemStack(material, amount);
        var itemMeta = itemStack.getItemMeta();
        if (itemMeta != null && level != -1) {
            if (enchantment != null) {
                var enchant = Enchantment.getByName(enchantment);
                if (enchant != null) {
                    itemMeta.addEnchant(enchant, level, true);
                }
            }
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }
}
