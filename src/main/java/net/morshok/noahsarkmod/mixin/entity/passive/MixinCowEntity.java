package net.morshok.noahsarkmod.mixin.entity.passive;

import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.morshok.noahsarkmod.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CowEntity.class)
public abstract class MixinCowEntity
{
    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable callbackInfoReturnable)
    {
        CowEntity cowEntity = ((CowEntity) (Object) this);

        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(ModItems.GLASS_MUG) && !cowEntity.isBaby())
        {
            ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, ModItems.MUG_OF_MILK.getDefaultStack());

            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0f, 1.0f);
            player.setStackInHand(hand, itemStack2);

            callbackInfoReturnable.setReturnValue(ActionResult.success(cowEntity.getWorld().isClient));
        }
    }
}