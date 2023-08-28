package net.morshok.noahsarkmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import net.morshok.noahsarkmod.sound.ModSounds;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MeatballItem extends Item
{

    public MeatballItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user)
    {
        List<SoundEvent> soundEventList = Arrays.asList(
                ModSounds.FUCKING_MEATBALL_1,
                ModSounds.FUCKING_MEATBALL_2
        );

        Random random = new Random();
        SoundEvent selectedSoundEvent = soundEventList.get(random.nextInt(soundEventList.size()));

        world.playSoundFromEntity(
                null,
                (Entity) user,
                selectedSoundEvent,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f
        );

        return super.finishUsing(stack, world, user);
    }
}