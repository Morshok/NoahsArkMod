package net.morshok.noahsarkmod;

import net.fabricmc.api.ModInitializer;
import net.morshok.noahsarkmod.enchantment.ModEnchantments;
import net.morshok.noahsarkmod.entity.ModEntityTypes;
import net.morshok.noahsarkmod.entity.effect.ModStatusEffects;
import net.morshok.noahsarkmod.item.ModItems;
import net.morshok.noahsarkmod.painting.ModPaintings;
import net.morshok.noahsarkmod.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoahsArkMod implements ModInitializer
{
	public static final String MOD_ID = "noahsarkmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		ModItems.registerModItems();

		ModSounds.registerModSounds();

		ModStatusEffects.registerModStatusEffects();

		ModEntityTypes.registerModEntityTypes();

		ModPaintings.registerModPaintings();

		ModEnchantments.registerModEnchantments();
	}
}