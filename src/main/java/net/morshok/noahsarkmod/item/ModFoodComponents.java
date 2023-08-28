package net.morshok.noahsarkmod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.morshok.noahsarkmod.entity.effect.ModStatusEffects;

public class ModFoodComponents
{
    public static final FoodComponent RAW_MEATBALL = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(0.4f)
            .meat()
            .build();
    public static final FoodComponent COOKED_MEATBALL = new FoodComponent.Builder()
            .hunger(8)
            .saturationModifier(0.8f)
            .meat()
            .build();
    public static final FoodComponent MUG_OF_WATER = new FoodComponent.Builder()
            .hunger(0)
            .saturationModifier(0.2f)
            .build();
    public static final FoodComponent MUG_OF_FANTA = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(ModStatusEffects.ADHD, 300, 1), 1.0f)
            .build();
    public static final FoodComponent MUG_OF_GRIMACE_SHAKE = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1), 1.0f)
            .build();

    public static final FoodComponent SOKA_BLATT_PILL = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.1f)
            .statusEffect(new StatusEffectInstance(ModStatusEffects.ADHD, 300, 1), 1.0f)
            .build();
}