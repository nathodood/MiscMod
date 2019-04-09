package com.nathodood.miscmod.blocks.stackedmaterials;

import com.nathodood.miscmod.main.Main;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockStructure;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MiscStackedBlock extends Item /* implements IHasModel */ {
	public String[] types = { "Double Stacked ", "Triple Stacked ", "Quadruple Stacked ", "Quintuple Stacked " },
			typesUnlocalized = { "double", "triple", "quadruple", "quintuple" };

	public static NonNullList<ItemStack> li = NonNullList.create();

	public MiscStackedBlock() {
		super();

		setRegistryName("stacked_block");
		setUnlocalizedName("stackedBlock");
		setHasSubtypes(true);
		setCreativeTab(Main.tabMiscMod);
	}

	public void registerModels() {
		int i = 0;
		ItemStack stack;

		for (Block block : ForgeRegistries.BLOCKS) {
			stack = new ItemStack(block);

			if (Main.flag(block)) {
				for (String type : types) {
					Main.commonProxy.registerStackedBlockModelByMeta(stack, i);

					i++;
				}

				for (int k = 1; stack.getHasSubtypes()
						&& !stack.getUnlocalizedName().equals(new ItemStack(block, 1, k).getUnlocalizedName()); k++) {
					for (String type : types) {
						Main.commonProxy.registerStackedBlockModelByMeta(new ItemStack(block, 1, k), i);

						i++;
					}
				}
			}
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		int i = 0;
		ItemStack stack;
		String stackDisplayName;

		for (Block block : ForgeRegistries.BLOCKS) {
			stack = new ItemStack(block);
			stackDisplayName = stack.getDisplayName();

			if (Main.flag(block)) {
				for (int j = 0; j < types.length; j++) {
					ItemStack s = new ItemStack(this, 1, i).setStackDisplayName(types[j] + stackDisplayName);
					list.add(s);
					li.add(s);

					i++;
				}

				int k = 1;
				while (stack.getHasSubtypes()
						&& !stack.getUnlocalizedName().equals(new ItemStack(block, 1, k).getUnlocalizedName())) {
					ItemStack s1 = new ItemStack(block, 1, k);

					for (int l = 0; l < types.length; l++) {
						ItemStack s2 = new ItemStack(this, 1, i).setStackDisplayName(types[l] + s1.getDisplayName());
						list.add(s2);
						li.add(s2);

						i++;
					}

					k++;
				}
			}
		}
	}

	public String unlocalizedName(String str, int type) {
		return typesUnlocalized[type] + str.substring(5, 6).toUpperCase() + str.substring(6);
	}
}
