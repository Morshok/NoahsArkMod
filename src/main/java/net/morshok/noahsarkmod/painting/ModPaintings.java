package net.morshok.noahsarkmod.painting;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.morshok.noahsarkmod.NoahsArkMod;

public class ModPaintings
{
    public static PaintingVariant HOLY_BREAD_OF_NOAH = registerPainting(
            "holy_bread_of_noah",
            new PaintingVariant(16, 32)
    );
    public static PaintingVariant GIGA_HAN = registerPainting(
            "giga_han",
            new PaintingVariant(64, 64)
    );
    public static PaintingVariant SOKA_BLATT = registerPainting(
            "soka_blatt",
            new PaintingVariant(64, 64)
    );
    public static PaintingVariant I_AM_A_PIGEON = registerPainting(
            "i_am_a_pigeon",
            new PaintingVariant(32, 64)
    );

    public static void registerModPaintings()
    {
        NoahsArkMod.LOGGER.info("Registering Mod Paintings for " + NoahsArkMod.MOD_ID);
    }

    private static PaintingVariant registerPainting(String name, PaintingVariant paintingVariant)
    {
        return Registry.register(
                Registries.PAINTING_VARIANT,
                new Identifier(NoahsArkMod.MOD_ID, name),
                paintingVariant
        );
    }
}