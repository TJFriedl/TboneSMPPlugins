package me.tbonejdi.tboneplugins.enchants;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import javax.xml.stream.events.Namespace;

public class EnchantmentWrapper extends Enchantment {

    // Minecraft(Unbreaking(1) 1)
    // TelepathyEnchant(Unbreaking(1) 1)

    private final String name;
    private final int maxLvl;
    private final NamespacedKey namespace;

    public EnchantmentWrapper(String namespace, String name, int lvl) {
        this.namespace = NamespacedKey.minecraft(namespace);
        this.name = name;
        this.maxLvl = lvl;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxLevel() {
        return maxLvl;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return false;
    }

    @Override
    public NamespacedKey getKey() {
        return namespace;
    }
}
