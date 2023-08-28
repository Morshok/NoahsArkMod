package net.morshok.noahsarkmod.mixin.entity.mob;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.morshok.noahsarkmod.enchantment.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EndermanEntity.class)
public class MixinEndermanEntity
{
    @Inject(method = "isPlayerStaring", at = @At("HEAD"), cancellable = true)
    public void isPlayerStaring(PlayerEntity playerEntity, CallbackInfoReturnable callbackInfoReturnable)
    {
        ItemStack itemStack = playerEntity.getInventory().armor.get(3);
        if (EnchantmentHelper.getLevel(ModEnchantments.FRIEND_OF_THE_END, itemStack) != 0)
        {
            callbackInfoReturnable.setReturnValue(false);
        }
    }
}