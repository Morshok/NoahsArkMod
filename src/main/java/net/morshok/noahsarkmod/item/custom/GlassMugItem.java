package net.morshok.noahsarkmod.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.morshok.noahsarkmod.item.ModItems;
import net.morshok.noahsarkmod.sound.ModSounds;

public class GlassMugItem extends Item
{
    protected static final int MAX_USE_TIME = 40;

    public GlassMugItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = GlassMugItem.raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);

        if (blockHitResult.getType() == HitResult.Type.MISS)
        {
            return ItemUsage.consumeHeldItem(world, user, hand);
        }
        if (blockHitResult.getType() == HitResult.Type.BLOCK)
        {
            BlockPos blockPos = blockHitResult.getBlockPos();

            if (!world.canPlayerModifyAt(user, blockPos))
            {
                return ItemUsage.consumeHeldItem(world, user, hand);
            }
            if (world.getFluidState(blockPos).isIn(FluidTags.WATER))
            {
                world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                world.emitGameEvent((Entity)user, GameEvent.FLUID_PICKUP, blockPos);

                return TypedActionResult.success(this.fill(itemStack, user, new ItemStack(ModItems.MUG_OF_WATER)), world.isClient());
            }
        }

        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    protected ItemStack fill(ItemStack stack, PlayerEntity player, ItemStack outputStack)
    {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return ItemUsage.exchangeStack(stack, player, outputStack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user)
    {
        super.finishUsing(stack, world, user);

        if (user instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if (stack.isEmpty())
        {
            return new ItemStack(ModItems.GLASS_MUG);
        }
        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode)
        {
            ItemStack itemStack = new ItemStack(ModItems.GLASS_MUG);
            PlayerEntity playerEntity = (PlayerEntity)user;
            if (!playerEntity.getInventory().insertStack(itemStack))
            {
                playerEntity.dropItem(itemStack, false);
            }
        }

        if(!world.isClient)
        {
            if (user instanceof ServerPlayerEntity)
            {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;

                world.playSoundFromEntity(
                        null,
                        serverPlayerEntity,
                        ModSounds.SIP_DONE,
                        SoundCategory.AMBIENT,
                        1.0f,
                        1.0f
                );
            }
        }

        user.emitGameEvent(GameEvent.DRINK);
        return stack;
    }

    public static int getMaxUseTime()
    {
        return MAX_USE_TIME;
    }

    @Override
    public UseAction getUseAction(ItemStack stack)
    {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound()
    {
        return ModSounds.SIP;
    }

    @Override
    public SoundEvent getEatSound()
    {
        return ModSounds.SIP;
    }
}