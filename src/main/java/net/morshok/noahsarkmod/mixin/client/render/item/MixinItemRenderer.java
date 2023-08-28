package net.morshok.noahsarkmod.mixin.client.render.item;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.morshok.noahsarkmod.NoahsArkMod;
import net.morshok.noahsarkmod.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = ItemRenderer.class)
public abstract class MixinItemRenderer
{
    @ModifyVariable(method = "renderItem", at = @At("HEAD"), argsOnly = true)
    public BakedModel useAnthonsCarKeysModel(BakedModel bakedModel, ItemStack itemStack, ModelTransformationMode modelTransformationMode, boolean isLeftHanded, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, int overlay)
    {
        if(itemStack.isOf(ModItems.ANTHONS_CAR_KEYS) && modelTransformationMode != ModelTransformationMode.GUI)
        {
            return ((AccessorItemRenderer) this).noahsarkmod$getModels().getModelManager().getModel(new ModelIdentifier(NoahsArkMod.MOD_ID, "anthons_car_keys_3d", "inventory"));
        }

        return bakedModel;
    }
}