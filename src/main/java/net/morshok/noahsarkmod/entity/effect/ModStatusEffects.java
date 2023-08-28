package net.morshok.noahsarkmod.entity.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.morshok.noahsarkmod.NoahsArkMod;
import net.morshok.noahsarkmod.entity.effect.custom.AdhdStatusEffect;

public class ModStatusEffects
{
    public static StatusEffect ADHD = registerStatusEffect(
            "adhd",
            new AdhdStatusEffect()
    ).addAttributeModifier(
            EntityAttributes.GENERIC_MOVEMENT_SPEED,
            "91AEAA56-376B-4498-935B-2F7F68070635",
            0.2f,
            EntityAttributeModifier.Operation.MULTIPLY_TOTAL
    );

    public static void registerModStatusEffects()
    {
        NoahsArkMod.LOGGER.info("Registering Mod Status Effects for " + NoahsArkMod.MOD_ID);
    }

    public static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect)
    {
        return Registry.register(
                Registries.STATUS_EFFECT,
                new Identifier(NoahsArkMod.MOD_ID, name),
                statusEffect
        );
    }
}