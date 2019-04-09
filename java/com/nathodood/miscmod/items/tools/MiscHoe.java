package com.nathodood.miscmod.items.tools;

import com.nathodood.miscmod.main.Main;
import com.nathodood.miscmod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;

public class MiscHoe extends ItemHoe implements IHasModel {
	public MiscHoe(ToolMaterial material, String name) {
		super(material);
		setUnlocalizedName(name + "Hoe");
		setRegistryName(name + "_hoe");
		setCreativeTab(Main.tabMiscMod);
		Main.ITEMS.add(this);
	}
	
	public void registerModels() {
		Main.commonProxy.registerItemRenderer(this, 0, "inventory");
	}
}
