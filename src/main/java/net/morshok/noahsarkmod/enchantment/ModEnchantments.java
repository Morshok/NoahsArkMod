package net.morshok.noahsarkmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.morshok.noahsarkmod.NoahsArkMod;
import net.morshok.noahsarkmod.enchantment.custom.FriendOfTheEndEnchantment;

public class ModEnchantments
{
    public static Enchantment FRIEND_OF_THE_END = registerEnchantment(
            "friend_of_the_end",
            new FriendOfTheEndEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.HEAD)
    );

    public static void registerModEnchantments()
    {
        NoahsArkMod.LOGGER.info("Registering Mod Enchantments for " + NoahsArkMod.MOD_ID);
    }

    private static Enchantment registerEnchantment(String name, Enchantment enchantment)
    {
        return Registry.register(
                Registries.ENCHANTMENT,
                new Identifier(NoahsArkMod.MOD_ID, name),
                enchantment
        );
    }
}