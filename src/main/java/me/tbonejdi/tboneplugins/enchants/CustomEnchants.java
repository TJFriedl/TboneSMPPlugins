package me.tbonejdi.tboneplugins.enchants;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomEnchants {

    public static final Enchantment TELEPATHY = new
            EnchantmentWrapper("telepathy", "Telepathy", 1);

    public static void register() {
        boolean registered =
                Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.TELEPATHY);
        if (!registered) {
            registerEnchantment(TELEPATHY);
        }
    }


        public static void registerEnchantment(Enchantment enchantment){
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
                registerEnchantment(enchantment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
