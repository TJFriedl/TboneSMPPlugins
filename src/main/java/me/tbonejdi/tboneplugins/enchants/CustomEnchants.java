package me.tbonejdi.tboneplugins.enchants;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomEnchants {

    public static final Enchantment TELEPATHY = new
            EnchantmentWrapper("telepathy", "Telepathy", 1);
    public static final Enchantment BLAZED_TIP = new EnchantmentWrapper("blazedtip", "Blazed Tip", 1);

    public static void register() {
        boolean registered =
                Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.TELEPATHY)
                && Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.BLAZED_TIP);
        if (!registered) {
            registerEnchantment(TELEPATHY);
            registerEnchantment(BLAZED_TIP);
        }
    }


        public static void registerEnchantment(Enchantment enchantment){
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
                Enchantment.registerEnchantment(enchantment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
