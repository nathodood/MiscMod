package com.nathodood.miscmod.items.tools;

import com.nathodood.miscmod.main.Main;
import com.nathodood.miscmod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;

public class MiscAxe extends ItemAxe implements IHasModel {
	public MiscAxe(ToolMaterial material, String name, float attackDamage, float speed) {
		super(material, attackDamage, speed);
		setUnlocalizedName(name + "Axe");
		setRegistryName(name + "_axe");
		setCreativeTab(Main.tabMiscMod);
		Main.ITEMS.add(this);
	}

	public void registerModels() {
		Main.commonProxy.registerItemRenderer(this, 0, "inventory");
	}
}
