package com.nathodood.miscmod.util.handlers;

import com.nathodood.miscmod.blocks.tnt.MiscTNT;
import com.nathodood.miscmod.main.Main;
import com.nathodood.miscmod.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(Main.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(Main.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (Item item : Main.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}

		for (Block block : Main.BLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel) block).registerModels();
			}
		}
	}
	
	// register the state mappers
	/*public static void registerBlockStateMappers() {
		BlockModelShapes model = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes();
		
		model.registerBlockWithStateMapper(Main.tenTNT, (new StateMap.Builder()).ignore(MiscTNT.EXPLODE).build());
		model.registerBlockWithStateMapper(Main.fiftyTNT, (new StateMap.Builder()).ignore(MiscTNT.EXPLODE).build());
		model.registerBlockWithStateMapper(Main.oneHundredTNT, (new StateMap.Builder()).ignore(MiscTNT.EXPLODE).build());
		model.registerBlockWithStateMapper(Main.randomTNT, (new StateMap.Builder()).ignore(MiscTNT.EXPLODE).build());
	}*/
}
