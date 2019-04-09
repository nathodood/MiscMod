package com.nathodood.miscmod.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CommonProxy {

	/**
	 * registers renderers for items
	 * 
	 * @param item the item
	 * @param meta the meta of <b>item</b>
	 * @param id   the id of the model registry
	 */
	public void registerItemRenderer(Item item, int meta, String id) {

	}

	/**
	 * registers models for the stacked blocks
	 * 
	 * @param stack the item stack
	 * @param meta  the meta of the stacked block
	 */
	public void registerStackedBlockModelByMeta(ItemStack stack, int meta) {

	}
}
