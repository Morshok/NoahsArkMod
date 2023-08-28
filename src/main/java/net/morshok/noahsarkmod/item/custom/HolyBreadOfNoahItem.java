package net.morshok.noahsarkmod.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ClickType;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import net.morshok.noahsarkmod.sound.ModSounds;

public class HolyBreadOfNoahItem extends SwordItem
{
    private static final int ATTACK_DAMAGE = 4;
    private static final float ATTACK_SPEED = 1.6f;
    private static final String ENCHANTMENTS_KEY = "Enchantments";
    private static final int ENCHANTMENT_LEVEL = 5;

    public HolyBreadOfNoahItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings)
    {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public HolyBreadOfNoahItem(ToolMaterial toolMaterial, Settings settings)
    {
        this(toolMaterial, ATTACK_DAMAGE, ATTACK_SPEED, settings);
    }

    @Override
    public Rarity getRarity(ItemStack stack)
    {
        return Rarity.EPIC;
    }

    @Override
    public boolean hasGlint(ItemStack stack)
    {
        return false;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        attacker.getWorld().playSound(
                null,
                attacker.getBlockPos(),
                ModSounds.KNOCKBACK2_BREAD,
                SoundCategory.AMBIENT,
                1.0f,
                1.0f
        );

        return super.postHit(stack, target, attacker);
    }

    @Override
    public void onCraft(ItemStack itemStack, World world, PlayerEntity playerEntity)
    {
        super.onCraft(itemStack, world, playerEntity);

        if(!world.isClient)
        {
            addNbtDataToHolyBreadOfNoah(itemStack);
        }
    }

    private void addNbtDataToHolyBreadOfNoah(ItemStack itemStack)
    {
        NbtCompound nbtCompound = itemStack.getOrCreateNbt();
        if (!nbtCompound.contains(ENCHANTMENTS_KEY, NbtElement.LIST_TYPE))
        {
            nbtCompound.put(ENCHANTMENTS_KEY, new NbtList());
        }
        NbtList nbtList = nbtCompound.getList(ENCHANTMENTS_KEY, NbtElement.COMPOUND_TYPE);
        nbtList.add(EnchantmentHelper.createNbt(EnchantmentHelper.getEnchantmentId(Enchantments.KNOCKBACK), (byte)ENCHANTMENT_LEVEL));
    }
}