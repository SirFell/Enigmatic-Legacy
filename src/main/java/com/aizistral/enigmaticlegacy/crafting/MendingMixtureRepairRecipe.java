package com.aizistral.enigmaticlegacy.crafting;

import java.util.ArrayList;
import java.util.List;

import com.aizistral.enigmaticlegacy.registries.EnigmaticItems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;

/**
 * Special recipe type for repairing of damageable items with Mending Mixture.
 * @author Integral
 */

public class MendingMixtureRepairRecipe extends CustomRecipe {
	public static final SimpleRecipeSerializer<MendingMixtureRepairRecipe> SERIALIZER = new SimpleRecipeSerializer<>(MendingMixtureRepairRecipe::new);

	public MendingMixtureRepairRecipe(ResourceLocation id) {
		super(id);
	}

	@Override
	public ItemStack assemble(CraftingContainer inv) {
		List<ItemStack> stackList = new ArrayList<ItemStack>();

		for (int i = 0; i < inv.getContainerSize(); i++) {
			ItemStack slotStack = inv.getItem(i);

			if (!slotStack.isEmpty()) {
				stackList.add(slotStack);
			}
		}

		if (stackList.size() == 2)
			if (stackList.get(0).isDamageableItem() || stackList.get(1).isDamageableItem())
				if (stackList.get(0).getItem() == EnigmaticItems.MENDING_MIXTURE || stackList.get(1).getItem() == EnigmaticItems.MENDING_MIXTURE) {
					ItemStack tool = stackList.get(0).isDamageableItem() ? stackList.get(0).copy() : stackList.get(1).copy();

					tool.setDamageValue(0);
					return tool;
				}

		return ItemStack.EMPTY;
	}

	@Override
	public boolean matches(CraftingContainer inv, Level world) {
		List<ItemStack> stackList = new ArrayList<ItemStack>();

		for (int i = 0; i < inv.getContainerSize(); i++) {
			ItemStack slotStack = inv.getItem(i);

			if (!slotStack.isEmpty()) {
				stackList.add(slotStack);
			}
		}

		if (stackList.size() == 2)
			if (stackList.get(0).isDamageableItem() || stackList.get(1).isDamageableItem())
				if (stackList.get(0).getItem() == EnigmaticItems.MENDING_MIXTURE || stackList.get(1).getItem() == EnigmaticItems.MENDING_MIXTURE)
					return true;

		return false;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}
}
