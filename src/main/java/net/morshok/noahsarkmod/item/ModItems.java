package net.morshok.noahsarkmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.morshok.noahsarkmod.NoahsArkMod;
import net.morshok.noahsarkmod.item.custom.*;
import net.morshok.noahsarkmod.sound.ModSounds;

public class ModItems
{
    public static final Item TOTEM_OF_DYING = registerItem(
            "totem_of_dying",
            new Item(
                    new FabricItemSettings()
                    .maxCount(1)
            )
    );
    public static final Item RAW_MEATBALL = registerItem(
            "raw_meatball",
            new MeatballItem(
                    new FabricItemSettings()
                    .food(ModFoodComponents.RAW_MEATBALL)
            )
    );
    public static final Item COOKED_MEATBALL = registerItem(
            "cooked_meatball",
            new MeatballItem(
                    new FabricItemSettings()
                    .food(ModFoodComponents.COOKED_MEATBALL)
            )
    );
    public static final Item GLASS_MUG = registerItem(
            "glass_mug",
            new GlassMugItem(
                    new FabricItemSettings()
                    .maxCount(64)
            )
    );
    public static final Item MUG_OF_MILK = registerItem(
            "mug_of_milk",
            new MugOfMilkItem(
                    new FabricItemSettings()
                            .maxCount(64)
                            .recipeRemainder(ModItems.GLASS_MUG)
            )
    );
    public static final Item MUG_OF_WATER = registerItem(
            "mug_of_water",
            new MugOfWaterItem(
                    new FabricItemSettings()
                    .maxCount(1)
                    .food(ModFoodComponents.MUG_OF_WATER)
            )
    );
    public static final Item MUG_OF_FANTA = registerItem(
            "mug_of_fanta",
            new MugOfFantaItem(
                    new FabricItemSettings()
                    .maxCount(1)
                    .food(ModFoodComponents.MUG_OF_FANTA)
            )
    );
    public static final Item MUG_OF_GRIMACE_SHAKE = registerItem(
            "mug_of_grimace_shake",
            new MugOfGrimaceShakeItem(
                    new FabricItemSettings()
                    .maxCount(1)
                    .food(ModFoodComponents.MUG_OF_GRIMACE_SHAKE)
            )
    );

    public static final Item SOKA_BLATT_PILL = registerItem(
            "soka_blatt_pill",
            new Item(
                    new FabricItemSettings()
                            .food(ModFoodComponents.SOKA_BLATT_PILL)
            )
    );
    public static final ToolItem HOLY_BREAD_OF_NOAH = (ToolItem) registerItem(
            "holy_bread_of_noah",
            new HolyBreadOfNoahItem(
                    ModToolMaterials.HOLY_BREAD_OF_NOAH,
                    3,
                    -2.4f,
                    new Item.Settings()
            )
    );
    public static final Item ANTHONS_CAR_KEYS = registerItem(
            "anthons_car_keys",
            new AnthonsCarKeysItem(
                    new FabricItemSettings()
                            .maxCount(1)
            )
    );
    public static final Item SOME_RANDOM_GUY_IN_A_GARAGE_MUSIC_DISC = registerItem(
            "some_random_guy_in_a_garage_music_disc",
            new MusicDiscItem(
                    14,
                    ModSounds.SOME_RANDOM_GUY_IN_A_GARAGE,
                    new FabricItemSettings()
                            .maxCount(1)
                            .rarity(Rarity.RARE),
                    14
            )
    );

    public static void registerModItems()
    {
        NoahsArkMod.LOGGER.info("Registering Mod Items for " + NoahsArkMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodAndDrinkItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsAndUtilitiesItemGroup);
    }

    public static Item registerItem(String name, Item item)
    {
        return Registry.register(
                Registries.ITEM,
                new Identifier(NoahsArkMod.MOD_ID, name),
                item
        );
    }

    private static void addItemsToToolsAndUtilitiesItemGroup(FabricItemGroupEntries fabricItemGroupEntries)
    {
        fabricItemGroupEntries.addAfter(Items.MUSIC_DISC_RELIC, SOME_RANDOM_GUY_IN_A_GARAGE_MUSIC_DISC);
    }

    private static void addItemsToCombatItemGroup(FabricItemGroupEntries fabricItemGroupEntries)
    {
        fabricItemGroupEntries.addAfter(Items.TOTEM_OF_UNDYING, TOTEM_OF_DYING);

        fabricItemGroupEntries.addAfter(Items.WOODEN_SWORD, HOLY_BREAD_OF_NOAH);

        fabricItemGroupEntries.addAfter(Items.TRIDENT, ANTHONS_CAR_KEYS);
    }

    private static void addItemsToFoodAndDrinkItemGroup(FabricItemGroupEntries fabricItemGroupEntries)
    {
        fabricItemGroupEntries.addAfter(Items.COOKED_BEEF, RAW_MEATBALL);
        fabricItemGroupEntries.addAfter(RAW_MEATBALL, COOKED_MEATBALL);

        fabricItemGroupEntries.addAfter(Items.CAKE, SOKA_BLATT_PILL);

        fabricItemGroupEntries.addBefore(Items.MILK_BUCKET, MUG_OF_MILK);
        fabricItemGroupEntries.addBefore(MUG_OF_MILK, GLASS_MUG);
        fabricItemGroupEntries.addAfter(GLASS_MUG, MUG_OF_WATER);
        fabricItemGroupEntries.addAfter(MUG_OF_WATER, MUG_OF_FANTA);
        fabricItemGroupEntries.addAfter(MUG_OF_FANTA, MUG_OF_GRIMACE_SHAKE);

    }
}