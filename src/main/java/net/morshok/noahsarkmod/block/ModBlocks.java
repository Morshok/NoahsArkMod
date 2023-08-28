package net.morshok.noahsarkmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.morshok.noahsarkmod.NoahsArkMod;

public class ModBlocks
{
    public static void registerModBlocks()
    {
        NoahsArkMod.LOGGER.info("Registering Mod Blocks for " +  NoahsArkMod.MOD_ID);
    }

    private static Item registerBlockItem(String name, Block block)
    {
        return Registry.register(
                Registries.ITEM,
                new Identifier(NoahsArkMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings())
        );
    }

    private static Block registerBlock(String name, Block block)
    {
        registerBlockItem(name, block);

        return Registry.register(
                Registries.BLOCK,
                new Identifier(NoahsArkMod.MOD_ID, name),
                block
        );
    }
}