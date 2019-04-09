package com.nathodood.miscmod.tabs;

import com.nathodood.miscmod.main.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabMiscMod extends CreativeTabs {
	public TabMiscMod() {
		super("miscmod");
		setBackgroundImageName("items.png");
	}

	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(Main.randomTNT));
	}
}
