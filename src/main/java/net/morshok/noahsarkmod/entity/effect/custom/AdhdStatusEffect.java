package net.morshok.noahsarkmod.entity.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.morshok.noahsarkmod.sound.ModSounds;

public class AdhdStatusEffect extends StatusEffect
{
    public AdhdStatusEffect()
    {
        super(StatusEffectCategory.NEUTRAL, 0xeb6b34);
    }

    @Override
    public void applyUpdateEffect(LivingEntity livingEntity, int amplifier)
    {
        if(livingEntity instanceof ServerPlayerEntity serverPlayerEntity)
        {
            serverPlayerEntity.damage(serverPlayerEntity.getDamageSources().wither(), 0.1f);
            serverPlayerEntity.addExhaustion(0.01f * (float)(amplifier + 1));
        }
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier)
    {
        super.onApplied(entity, attributes, amplifier);

        if(entity instanceof PlayerEntity playerEntity)
        {
            playerEntity.getWorld().playSoundFromEntity(
                    null,
                    playerEntity,
                    ModSounds.BURP,
                    SoundCategory.AMBIENT,
                    1.0f,
                    1.0f
            );
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier)
    {
        int i = 50 >> amplifier;

        if(i == 0)
        {
            return duration % i == 0;
        }

        return true;
    }
}