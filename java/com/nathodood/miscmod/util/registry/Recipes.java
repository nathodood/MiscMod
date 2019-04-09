package com.nathodood.miscmod.util.registry;

import com.nathodood.miscmod.blocks.stackedmaterials.MiscStackedBlock;
import com.nathodood.miscmod.main.Main;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Recipes {
	private static void addShapedRecipe(ItemStack input, ItemStack output, String name) {
		ForgeRegistries.RECIPES.register(new ShapedRecipes("", 3, 3,
				ingredients(input, input, input, input, input, input, input, input, input), output)
						.setRegistryName(new ResourceLocation(Main.MODID + ":stacked_block_" + name + "_shaped")));
	}

	private static void addShapelessRecipe(ItemStack input, ItemStack output, String name) {
		ForgeRegistries.RECIPES.register(new ShapedRecipes("", 1, 1, ingredients(input),
				new ItemStack(output.getItem(), 9, output.getMetadata()).setStackDisplayName(output.getDisplayName()))
						.setRegistryName(new ResourceLocation(Main.MODID + ":stacked_block_" + name + "_shapeless")));
	}

	private static NonNullList<Ingredient> ingredients(ItemStack... stacks) {
		NonNullList<Ingredient> ingr = NonNullList.create();

		for (ItemStack stack : stacks) {
			ingr.add(Ingredient.fromStacks(stack));
		}

		return ingr;
	}

	public static void addRecipes() {
		MiscStackedBlock msb = new MiscStackedBlock();
		ItemStack stack;
		NonNullList<ItemStack> l = MiscStackedBlock.li;
		int meta = 0, i = 0, length = msb.types.length;

		for (Block block : ForgeRegistries.BLOCKS) {
			stack = new ItemStack(block);

			if (Main.flag(block)) {
				addShapedRecipe(stack, l.get(meta), msb.unlocalizedName(block.getUnlocalizedName(), 0));
				addShapelessRecipe(l.get(meta), new ItemStack(block, 9),
						msb.unlocalizedName(block.getUnlocalizedName(), 0));

				for (int type = 1; type < length; type++) {
					addShapedRecipe(l.get(meta + type - 1), l.get(meta + type),
							msb.unlocalizedName(block.getUnlocalizedName(), type));
					addShapelessRecipe(l.get(meta + type), l.get(meta + type - 1),
							msb.unlocalizedName(block.getUnlocalizedName(), type));
				}

				meta += length - 1;

				for (i = 1; stack.getHasSubtypes()
						&& !stack.getUnlocalizedName().equals(new ItemStack(block, 1, i).getUnlocalizedName()); i++) {
					addShapedRecipe(new ItemStack(block, 1, i), l.get(meta + i),
							msb.unlocalizedName(new ItemStack(block, 1, i).getUnlocalizedName(), 0));
					addShapelessRecipe(l.get(meta + i), new ItemStack(block, 9, i),
							msb.unlocalizedName(new ItemStack(block, 1, i).getUnlocalizedName(), 0));

					for (int type = 1; type < length; type++) {
						addShapedRecipe(l.get(meta + i + type - 1), l.get(meta + i + type),
								msb.unlocalizedName(new ItemStack(block, 1, i).getUnlocalizedName(), type));
						addShapelessRecipe(l.get(meta + i + type), l.get(meta + i + type - 1),
								msb.unlocalizedName(new ItemStack(block, 1, i).getUnlocalizedName(), type));
					}
					
					meta += length - 1;
				}

				meta += i;
				i = 0;
			}
		}
	}
}
