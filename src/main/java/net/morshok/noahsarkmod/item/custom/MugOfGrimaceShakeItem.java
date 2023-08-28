package net.morshok.noahsarkmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.morshok.noahsarkmod.sound.ModSounds;

public class MugOfGrimaceShakeItem extends GlassMugItem
{

    public MugOfGrimaceShakeItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user)
    {
        world.playSoundFromEntity(
                null,
                user,
                ModSounds.GRIMACE_SHAKE_BURP,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f
        );

        return super.finishUsing(stack, world, user);
    }
}