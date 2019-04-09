package com.nathodood.miscmod.items.tools;

import com.nathodood.miscmod.main.Main;
import com.nathodood.miscmod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;

public class MiscSword extends ItemSword implements IHasModel {
	public MiscSword(ToolMaterial material, String name) {
		super(material);
		setUnlocalizedName(name + "Sword");
		setRegistryName(name + "_sword");
		setCreativeTab(Main.tabMiscMod);
		setMaxStackSize(1);
		Main.ITEMS.add(this);
	}

	public void registerModels() {
		Main.commonProxy.registerItemRenderer(this, 0, "inventory");
	}
}
