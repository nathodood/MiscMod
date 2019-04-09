package com.nathodood.miscmod.proxy;

import com.nathodood.miscmod.main.Main;
import com.nathodood.miscmod.util.handlers.RegistryHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
	public static ClientProxy instance = new ClientProxy();

	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	public void registerStackedBlockModelByMeta(ItemStack stack, int meta) {
		ModelLoader.setCustomModelResourceLocation(Main.stackedBlock, meta,
				new ModelResourceLocation(stack.getItem().getRegistryName(), "inventory"));
	}
}
