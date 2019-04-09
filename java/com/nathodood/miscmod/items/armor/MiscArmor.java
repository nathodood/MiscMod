package com.nathodood.miscmod.items.armor;

import com.nathodood.miscmod.main.Main;
import com.nathodood.miscmod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class MiscArmor extends ItemArmor implements IHasModel {

	public MiscArmor(ArmorMaterial material, String name, int renderIndex, EntityEquipmentSlot slot) {
		super(material, renderIndex, slot);
		setUnlocalizedName(
				name + Character.toUpperCase(slotToArmorType(slot).charAt(0)) + slotToArmorType(slot).substring(1));
		setRegistryName(name + "_" + slotToArmorType(slot));
		setCreativeTab(Main.tabMiscMod);
		
		Main.ITEMS.add(this);
	}

	public void registerModels() {
		Main.commonProxy.registerItemRenderer(this, 0, "inventory");
	}

	public String slotToArmorType(EntityEquipmentSlot slot) {
		return (slot == EntityEquipmentSlot.HEAD) ? "helmet"
				: (slot == EntityEquipmentSlot.CHEST) ? "chestplate"
						: (slot == EntityEquipmentSlot.LEGS) ? "leggings"
								: (slot == EntityEquipmentSlot.FEET) ? "boots" : "";
	}
}
