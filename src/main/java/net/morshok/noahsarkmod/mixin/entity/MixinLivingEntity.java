package net.morshok.noahsarkmod.mixin.entity;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.morshok.noahsarkmod.item.ModItems;
import net.morshok.noahsarkmod.sound.ModSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LivingEntity.class)
public class MixinLivingEntity
{
    @Inject(method = "damage", at = @At("TAIL"), cancellable = false)
    private void onHurt(DamageSource damageSource, float damageAmount, CallbackInfoReturnable<Boolean> callbackInfoReturnable)
    {
        LivingEntity livingEntity = ((LivingEntity) (Object) this);

        if(livingEntity instanceof ServerPlayerEntity serverPlayerEntity)
        {
            Item mainHandItem = serverPlayerEntity.getMainHandStack().getItem();
            Item offHandItem = serverPlayerEntity.getOffHandStack().getItem();

            if(mainHandItem.equals(ModItems.TOTEM_OF_DYING) || offHandItem.equals(ModItems.TOTEM_OF_DYING))
            {
                if(!serverPlayerEntity.isDead())
                {
                    ItemStack itemStack = null;

                    for(ItemStack handItemStack : serverPlayerEntity.getHandItems())
                    {
                        if(!handItemStack.isOf(ModItems.TOTEM_OF_DYING))
                        {
                            continue;
                        }

                        itemStack = handItemStack.copy();
                        handItemStack.decrement(1);
                        break;
                    }

                    if(itemStack != null)
                    {
                        serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(ModItems.TOTEM_OF_DYING));
                        Criteria.USED_TOTEM.trigger(serverPlayerEntity, itemStack);
                    }

                    serverPlayerEntity.kill();
                    serverPlayerEntity.onDeath(damageSource);

                    serverPlayerEntity.getWorld().playSoundFromEntity(
                            null,
                            serverPlayerEntity,
                            ModSounds.LAUGHTER,
                            SoundCategory.AMBIENT,
                            1.0f,
                            1.0f
                    );
                }
            }
        }
    }
}