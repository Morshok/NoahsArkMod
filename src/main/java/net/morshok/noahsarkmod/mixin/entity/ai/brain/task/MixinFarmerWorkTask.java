package net.morshok.noahsarkmod.mixin.entity.ai.brain.task;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.FarmerWorkTask;
import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.morshok.noahsarkmod.NoahsArkMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.NoSuchElementException;
import java.util.Optional;

@Mixin(value = FarmerWorkTask.class)
public class MixinFarmerWorkTask
{
    @Inject(method = "craftAndDropBread", at = @At("HEAD"), cancellable = true)
    private void dropWheat(VillagerEntity villagerEntity, CallbackInfo callbackInfo)
    {
        callbackInfo.cancel();
    }
}