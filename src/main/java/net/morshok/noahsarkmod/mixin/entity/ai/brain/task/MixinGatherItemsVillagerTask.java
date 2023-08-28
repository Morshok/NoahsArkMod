package net.morshok.noahsarkmod.mixin.entity.ai.brain.task;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.GatherItemsVillagerTask;
import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.village.VillagerProfession;
import net.morshok.noahsarkmod.NoahsArkMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(value = GatherItemsVillagerTask.class)
public class MixinGatherItemsVillagerTask
{
    @Inject(method = "keepRunning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/VillagerEntity;J)V", at = @At("HEAD"), cancellable = false)
    private void keepRunning(ServerWorld serverWorld, VillagerEntity villagerEntity, long l, CallbackInfo callbackInfo)
    {
        VillagerEntity villagerEntity2 = (VillagerEntity)villagerEntity.getBrain().getOptionalRegisteredMemory(MemoryModuleType.INTERACTION_TARGET).get();

        if (villagerEntity.squaredDistanceTo(villagerEntity2) > 5.0)
        {
            return;
        }

        LookTargetUtil.lookAtAndWalkTowardsEachOther(villagerEntity, villagerEntity2, 0.5f);
        villagerEntity.talkWithVillager(serverWorld, villagerEntity2, l);

        if(villagerEntity.getVillagerData().getProfession() == VillagerProfession.FARMER)
        {
            sacrificeAllWheatForTheGreaterGood(villagerEntity, villagerEntity2);
        }
    }

    private void sacrificeAllWheatForTheGreaterGood(VillagerEntity villager, LivingEntity target) {
        SimpleInventory simpleInventory = villager.getInventory();
        ItemStack itemStack = ItemStack.EMPTY;

        for (int i = 0; i < simpleInventory.size(); ++i)
        {
            int j = 0;
            Item item;
            ItemStack itemStack2 = simpleInventory.getStack(i);
            if (itemStack2.isEmpty() || itemStack2.getItem() != Items.WHEAT) continue;
            if (itemStack2.getCount() > 0)
            {
                j = itemStack2.getCount();
            }
            itemStack2.decrement(j);
            itemStack = new ItemStack(Items.WHEAT, j);
            break;
        }

        if (!itemStack.isEmpty())
        {
            LookTargetUtil.give(villager, itemStack, target.getPos());
        }
    }
}