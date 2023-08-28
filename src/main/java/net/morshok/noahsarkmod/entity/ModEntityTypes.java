package net.morshok.noahsarkmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.morshok.noahsarkmod.NoahsArkMod;

public class ModEntityTypes
{
    public static void registerModEntityTypes()
    {
        NoahsArkMod.LOGGER.info("Registering Mod Entity Types for " + NoahsArkMod.MOD_ID);
    }

    private static <T extends Entity> EntityType<T> registerEntityType(String name, EntityType.Builder<T> type)
    {
        return Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(NoahsArkMod.MOD_ID, name),
                type.build(name)
        );
    }
}