package net.morshok.noahsarkmod.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.morshok.noahsarkmod.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class MugOfMilkItem extends Item
{
    private static final int MAX_USE_TIME = 32;
    private final Item recipeRemainder = ModItems.MUG_OF_MILK;

    public MugOfMilkItem(Item.Settings settings)
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
        if (user instanceof ServerPlayerEntity serverPlayerEntity)
        {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode)
        {
            stack.decrement(1);
        }
        if (!world.isClient)
        {
            user.clearStatusEffects();
        }
        if (stack.isEmpty())
        {
            return new ItemStack(ModItems.GLASS_MUG);
        }

        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack)
    {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack)
    {
        return MAX_USE_TIME;
    }
}