package com.nathodood.miscmod.items.tools;

import com.nathodood.miscmod.main.Main;
import com.nathodood.miscmod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;

public class MiscShovel extends ItemSpade implements IHasModel {	
	public MiscShovel(ToolMaterial material, String name) {
		super(material);
		setUnlocalizedName(name + "Shovel");
		setRegistryName(name + "_shovel");
		setCreativeTab(Main.tabMiscMod);
		Main.ITEMS.add(this);
	}
	
	public void registerModels() {
		Main.commonProxy.registerItemRenderer(this, 0, "inventory");
	}
}
