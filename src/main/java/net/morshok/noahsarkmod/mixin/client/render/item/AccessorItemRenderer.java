package net.morshok.noahsarkmod.mixin.client.render.item;

import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = ItemRenderer.class)
public interface AccessorItemRenderer
{
    @Accessor("models")
    ItemModels noahsarkmod$getModels();
}