package com.nathodood.miscmod.main;

import java.util.ArrayList;
import java.util.List;

import com.nathodood.miscmod.blocks.stackedmaterials.MiscStackedBlock;
import com.nathodood.miscmod.blocks.tnt.FiftyTNT;
import com.nathodood.miscmod.blocks.tnt.MiscTNT;
import com.nathodood.miscmod.items.armor.MiscArmor;
import com.nathodood.miscmod.items.tools.MiscAxe;
import com.nathodood.miscmod.items.tools.MiscHoe;
import com.nathodood.miscmod.items.tools.MiscPickaxe;
import com.nathodood.miscmod.items.tools.MiscShovel;
import com.nathodood.miscmod.items.tools.MiscSword;
import com.nathodood.miscmod.proxy.CommonProxy;
import com.nathodood.miscmod.tabs.TabMiscMod;
import com.nathodood.miscmod.util.handlers.RegistryHandler;
import com.nathodood.miscmod.util.registry.Entities;
import com.nathodood.miscmod.util.registry.Recipes;

//import miscmod.worldproviders.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStructure;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.IRecipe;
//import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
@EventBusSubscriber
public class Main {
	@Instance
	public static Main instance;

	// Mod info constants
	public static final String MODID = "miscmod", NAME = "Miscellaneous Mod", VERSION = "3.0";

	// proxy
	@SidedProxy(modId = MODID, clientSide = "com.nathodood.miscmod.proxy.ClientProxy", serverSide = "com.nathodood.miscmod.proxy.CommonProxy")
	public static CommonProxy commonProxy;
	/*
	 * public static final int diamondDimensionId = 8; public static final
	 * BiomeGenBase biomeDiamond = new
	 * BiomeGenDiamond(137).setBiomeName("Diamond").setTemperatureRainfall(1.2F,
	 * 0.9F);
	 */

	// tool material constants
	static float[] speed = { 20.0F, 15.0F, 8.0F, 8.0F }, damage = { 15.0F, 10.0F, 8.5F, 8.5F };

	// Tool Materials
	public static final ToolMaterial obsidianToolMaterial = EnumHelper.addToolMaterial("obsidian_tool_material", 3,
			7500, speed[0], damage[0], 25);
	public static final ToolMaterial emeraldToolMaterial = EnumHelper.addToolMaterial("emerald_tool_material", 3, 2500,
			speed[1], damage[1], 25);
	public static final ToolMaterial redstoneToolMaterial = EnumHelper.addToolMaterial("redstone_tool_material", 3,
			1000, speed[2], damage[2], 10);
	public static final ToolMaterial lapisToolMaterial = EnumHelper.addToolMaterial("lapis_tool_material", 3, 1000,
			speed[3], damage[3], 10);

	// Armor Materials
	public static final ArmorMaterial obsidianArmorMaterial = EnumHelper.addArmorMaterial("obsidian_armor_material",
			MODID + ":obsidian", 500, new int[] { 10, 12, 15, 8 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 10.0F);
	public static final ArmorMaterial emeraldArmorMaterial = EnumHelper.addArmorMaterial("emerald_armor_material",
			MODID + ":emerald", 475, new int[] { 4, 5, 6, 3 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F);
	public static final ArmorMaterial redstoneArmorMaterial = EnumHelper.addArmorMaterial("redstone_armor_material",
			MODID + ":redstone", 400, new int[] { 5, 4, 7, 2 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.0F);
	public static final ArmorMaterial lapisArmorMaterial = EnumHelper.addArmorMaterial("lapis_armor_material",
			MODID + ":lapis", 450, new int[] { 4, 3, 7, 2 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 3.0F);

	// Tabs
	public static final CreativeTabs tabMiscMod = new TabMiscMod();

	// Items
	public static final List<Item> ITEMS = new ArrayList<Item>();

	public static final ItemPickaxe obsidianPickaxe = new MiscPickaxe(obsidianToolMaterial, "obsidian");
	public static final ItemSword obsidianSword = new MiscSword(obsidianToolMaterial, "obsidian");
	public static final ItemSpade obsidianShovel = new MiscShovel(obsidianToolMaterial, "obsidian");
	public static final ItemHoe obsidianHoe = new MiscHoe(obsidianToolMaterial, "obsidian");
	public static final ItemAxe obsidianAxe = new MiscAxe(obsidianToolMaterial, "obsidian", damage[0], speed[0]);

	public static final ItemPickaxe emeraldPickaxe = new MiscPickaxe(emeraldToolMaterial, "emerald");
	public static final ItemSword emeraldSword = new MiscSword(emeraldToolMaterial, "emerald");
	public static final ItemSpade emeraldShovel = new MiscShovel(emeraldToolMaterial, "emerald");
	public static final ItemHoe emeraldHoe = new MiscHoe(emeraldToolMaterial, "emerald");
	public static final ItemAxe emeraldAxe = new MiscAxe(emeraldToolMaterial, "emerald", damage[1], speed[1]);

	public static final ItemPickaxe redstonePickaxe = new MiscPickaxe(redstoneToolMaterial, "redstone");
	public static final ItemSword redstoneSword = new MiscSword(redstoneToolMaterial, "redstone");
	public static final ItemSpade redstoneShovel = new MiscShovel(redstoneToolMaterial, "redstone");
	public static final ItemHoe redstoneHoe = new MiscHoe(redstoneToolMaterial, "redstone");
	public static final ItemAxe redstoneAxe = new MiscAxe(redstoneToolMaterial, "redstone", damage[2], speed[2]);

	public static final ItemPickaxe lapisPickaxe = new MiscPickaxe(lapisToolMaterial, "lapis");
	public static final ItemSword lapisSword = new MiscSword(lapisToolMaterial, "lapis");
	public static final ItemSpade lapisShovel = new MiscShovel(lapisToolMaterial, "lapis");
	public static final ItemHoe lapisHoe = new MiscHoe(lapisToolMaterial, "lapis");
	public static final ItemAxe lapisAxe = new MiscAxe(lapisToolMaterial, "lapis", damage[3], speed[3]);

	public static final ItemArmor obsidianHelmet = new MiscArmor(obsidianArmorMaterial, "obsidian", 1,
			EntityEquipmentSlot.HEAD);
	public static final ItemArmor obsidianChestplate = new MiscArmor(obsidianArmorMaterial, "obsidian", 1,
			EntityEquipmentSlot.CHEST);
	public static final ItemArmor obsidianLeggings = new MiscArmor(obsidianArmorMaterial, "obsidian", 2,
			EntityEquipmentSlot.LEGS);
	public static final ItemArmor obsidianBoots = new MiscArmor(obsidianArmorMaterial, "obsidian", 1,
			EntityEquipmentSlot.FEET);

	public static final ItemArmor emeraldHelmet = new MiscArmor(emeraldArmorMaterial, "emerald", 1,
			EntityEquipmentSlot.HEAD);
	public static final ItemArmor emeraldChestplate = new MiscArmor(emeraldArmorMaterial, "emerald", 1,
			EntityEquipmentSlot.CHEST);
	public static final ItemArmor emeraldLeggings = new MiscArmor(emeraldArmorMaterial, "emerald", 2,
			EntityEquipmentSlot.LEGS);
	public static final ItemArmor emeraldBoots = new MiscArmor(emeraldArmorMaterial, "emerald", 1,
			EntityEquipmentSlot.FEET);

	public static final ItemArmor redstoneHelmet = new MiscArmor(redstoneArmorMaterial, "redstone", 1,
			EntityEquipmentSlot.HEAD);
	public static final ItemArmor redstoneChestplate = new MiscArmor(redstoneArmorMaterial, "redstone", 1,
			EntityEquipmentSlot.CHEST);
	public static final ItemArmor redstoneLeggings = new MiscArmor(redstoneArmorMaterial, "redstone", 2,
			EntityEquipmentSlot.LEGS);
	public static final ItemArmor redstoneBoots = new MiscArmor(redstoneArmorMaterial, "redstone", 1,
			EntityEquipmentSlot.FEET);

	public static final ItemArmor lapisHelmet = new MiscArmor(lapisArmorMaterial, "lapis", 1, EntityEquipmentSlot.HEAD);
	public static final ItemArmor lapisChestplate = new MiscArmor(lapisArmorMaterial, "lapis", 1,
			EntityEquipmentSlot.CHEST);
	public static final ItemArmor lapisLeggings = new MiscArmor(lapisArmorMaterial, "lapis", 2,
			EntityEquipmentSlot.LEGS);
	public static final ItemArmor lapisBoots = new MiscArmor(lapisArmorMaterial, "lapis", 1, EntityEquipmentSlot.FEET);

	// Blocks
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	// I tried seeing if creating different classes for each different TNT would
	// solve my problem but lolnope, so that's why you see three of the TNTs as
	// instances of one class and one TNT as an instance of a different class
	// instead of them all being instances of the same class, which is what I had
	// before
	public static final Block tenTNT = new MiscTNT("ten_tnt", 125, 40);
	public static final Block fiftyTNT = new FiftyTNT("fifty_tnt", 200, 200);
	public static final Block oneHundredTNT = new MiscTNT("one_hundred_tnt", 400, 400);
	public static final Block randomTNT = new MiscTNT("random_tnt", 0, 0);

	public static final Item stackedBlock = new MiscStackedBlock();

	// init events
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ForgeRegistries.ITEMS.register(stackedBlock);
		ForgeRegistries.ENTITIES.registerAll(Entities.ENTITIES.toArray(new EntityEntry[0]));
		Entities.init();

		new MiscStackedBlock().registerModels();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		//RegistryHandler.registerBlockStateMappers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	// random registry functions
	@SubscribeEvent
	public static void addRecipes(RegistryEvent.Register<IRecipe> e) {
		Recipes.addRecipes();
	}

	public static boolean flag(Block block) {
		return (block instanceof BlockStone || block instanceof BlockDirt || block instanceof BlockGravel
				|| block instanceof BlockSand || block instanceof BlockSandStone
				|| (block.getMaterial(null) == Material.IRON && block.getRegistryName().toString().contains("block")
						&& !(block instanceof BlockCommandBlock || block instanceof BlockStructure)))
				&& block.isFullBlock(null) && !(new ItemStack(block).getDisplayName().equalsIgnoreCase("air"));
	}

	/**
	 * coverts a registry name into an unlocalized name
	 * 
	 * @param name the registry name of the object
	 * @return the converted unlocalized name
	 */
	public static String registryToUnlocalizedName(String name) {
		while (name.indexOf("_") != -1) {
			name = name.substring(0, name.indexOf("_")) + (name.indexOf("_") + 1 > name.length() ? ""
					: name.substring(name.indexOf("_") + 1, name.indexOf("_") + 2).toUpperCase()
							+ name.substring(name.indexOf("_") + 2));
		}

		return name;
	}
}
