package com.nathodood.miscmod.util.registry;

import java.util.ArrayList;
import java.util.List;

import com.nathodood.miscmod.entity.render.RenderFiftyTNTPrimed;
import com.nathodood.miscmod.entity.render.RenderOneHundredTNTPrimed;
import com.nathodood.miscmod.entity.render.RenderRandomTNTPrimed;
import com.nathodood.miscmod.entity.render.RenderTenTNTPrimed;
import com.nathodood.miscmod.entity.tnt.EntityFiftyTNTPrimed;
import com.nathodood.miscmod.entity.tnt.EntityOneHundredTNTPrimed;
import com.nathodood.miscmod.entity.tnt.EntityRandomTNTPrimed;
import com.nathodood.miscmod.entity.tnt.EntityTenTNTPrimed;
import com.nathodood.miscmod.main.Main;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class Entities {

	// the entites
	public static final List<EntityEntry> ENTITIES = new ArrayList<EntityEntry>();

	public static EntityEntry entityTenTNTPrimed = newEntity("ten", EntityTenTNTPrimed.class);
	public static EntityEntry entityFiftyTNTPrimed = newEntity("fifty", EntityFiftyTNTPrimed.class);
	public static EntityEntry entityOneHundredTNTPrimed = newEntity("one_hundred", EntityOneHundredTNTPrimed.class);
	public static EntityEntry entityRandomTNTPrimed = newEntity("random", EntityRandomTNTPrimed.class);

	private static final Entities instance = new Entities();

	/*
	 * this was commented out from me calling the registerAll from preInit in Main
	 * to see if I needed to register them earlier but that didn't work
	 * 
	 * @SubscribeEvent public void
	 * registerEntity(RegistryEvent.Register<EntityEntry> e) {
	 * e.getRegistry().registerAll(ENTITIES.toArray(new EntityEntry[0])); }
	 */

	public static void init() {
		MinecraftForge.EVENT_BUS.register(instance);
		registerRenderers();
	}

	/**
	 * creates a new instance of EntityEntry and adds it to the list ENTITIES
	 * 
	 * @param name  the entity's registry name
	 * @param clazz the entity's class
	 * @return a new entity
	 */
	private static EntityEntry newEntity(String name, Class<? extends Entity> clazz) {
		EntityEntry entity = EntityEntryBuilder.create().entity(clazz).id(Main.MODID + ":" + name + "_tnt_primed", 120)
				.name(name + "_tnt_primed").tracker(100, 3, true).build();
		ENTITIES.add(entity);

		return entity;
	}

	// registers entity renderers; it only needs one render statement to register
	// for all the entities which is not at all what I want to happen (i.e. it
	// registers the renderer for all registered entities as the renderer declared
	// in the first statement only as if it were repeated for each entity in the
	// registry)
	private static void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTenTNTPrimed.class,
				renderer -> new RenderTenTNTPrimed(renderer));
		RenderingRegistry.registerEntityRenderingHandler(EntityFiftyTNTPrimed.class,
				renderer -> new RenderFiftyTNTPrimed(renderer));
		RenderingRegistry.registerEntityRenderingHandler(EntityOneHundredTNTPrimed.class,
				renderer -> new RenderOneHundredTNTPrimed(renderer));
		RenderingRegistry.registerEntityRenderingHandler(EntityRandomTNTPrimed.class,
				renderer -> new RenderRandomTNTPrimed(renderer));
	}
}
