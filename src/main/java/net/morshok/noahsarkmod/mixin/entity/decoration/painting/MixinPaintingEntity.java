package net.morshok.noahsarkmod.mixin.entity.decoration.painting;

import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.decoration.painting.PaintingVariants;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.morshok.noahsarkmod.NoahsArkMod;
import net.morshok.noahsarkmod.painting.ModPaintings;
import net.morshok.noahsarkmod.sound.ModSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Mixin(value = PaintingEntity.class)
public class MixinPaintingEntity
{
    @Inject(method = "onPlace", at = @At("TAIL"), cancellable = false)
    public void onPlace(CallbackInfo callbackInfo)
    {
        RegistryEntry<PaintingVariant> paintingVariantRegistryEntry = ((PaintingEntity) (Object) this).getVariant();
        String variantIdentifier = ((RegistryKey)paintingVariantRegistryEntry.getKey().orElse(PaintingVariants.KEBAB)).getValue().toString();

        if(variantIdentifier.equals("noahsarkmod:i_am_a_pigeon"))
        {
            List<SoundEvent> soundEventList = Arrays.asList(
                    ModSounds.FUCKING_BIRD_1,
                    ModSounds.FUCKING_BIRD_2,
                    ModSounds.FUCKING_BIRD_3
            );

            ((PaintingEntity) (Object) this).getWorld().playSound(
                    null,
                    ((PaintingEntity) (Object) this).getBlockPos(),
                    ModSounds.getRandomSoundEvent(soundEventList),
                    SoundCategory.AMBIENT,
                    1.0f,
                    1.0f
            );
        }
        if(variantIdentifier.equals("noahsarkmod:giga_han"))
        {
            ((PaintingEntity) (Object) this).getWorld().playSound(
                    null,
                    ((PaintingEntity) (Object) this).getBlockPos(),
                    ModSounds.GIGA_CHAD_THEME,
                    SoundCategory.AMBIENT,
                    1.0f,
                    1.0f
            );
        }
    }
}