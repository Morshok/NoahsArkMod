package net.morshok.noahsarkmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.morshok.noahsarkmod.sound.ModSounds;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AnthonsCarKeysItem extends Item
{

    public AnthonsCarKeysItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        List<SoundEvent> soundEventList = Arrays.asList(
                ModSounds.I_HAVE_ANTHONS_CAR_KEYS_1,
                ModSounds.I_HAVE_ANTHONS_CAR_KEYS_2,
                ModSounds.I_HAVE_ANTHONS_CAR_KEYS_3
        );

        Random random = new Random();
        SoundEvent selectedSoundEvent = soundEventList.get(random.nextInt(soundEventList.size()));

        world.playSoundFromEntity(
                null,
                user,
                selectedSoundEvent,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f
        );

        return super.use(world, user, hand);
    }
}