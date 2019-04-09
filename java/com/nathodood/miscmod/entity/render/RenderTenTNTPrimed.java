package com.nathodood.miscmod.entity.render;

import com.nathodood.miscmod.entity.tnt.EntityTenTNTPrimed;
import com.nathodood.miscmod.main.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderTenTNTPrimed extends Render<EntityTenTNTPrimed> {
	public RenderTenTNTPrimed(RenderManager manager) {
		super(manager);
	}

	public void doRender(EntityTenTNTPrimed entity, double x, double y, double z, float entityYaw, float partialTicks) {
		BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.5F, (float) z);

		if ((float) entity.getFuse() - partialTicks + 1.0F < 10.0F) {
			float f = 1.0F - ((float) entity.getFuse() - partialTicks + 1.0F) / 10.0F;
			f = MathHelper.clamp(f, 0.0F, 1.0F);
			f *= f;
			f *= f;
			float f1 = 1.0F + f * 0.3F;
			GlStateManager.scale(f1, f1, f1);
		}

		float f2 = (1.0F - ((float) entity.getFuse() - partialTicks + 1.0F) / 100.0F) * 0.8F;
		GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(-0.5F, -0.5F, 0.5F);
		dispatcher.renderBlockBrightness(Main.tenTNT.getDefaultState(), entity.getBrightness());
		GlStateManager.translate(0.0F, 0.0F, 1.0F);

		if (this.renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
			dispatcher.renderBlockBrightness(Main.tenTNT.getDefaultState(), 1.0F);
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}

		else if (entity.getFuse() / 5 % 2 == 0) {
			GlStateManager.disableTexture2D();
			GlStateManager.disableLighting();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.DST_ALPHA);
			GlStateManager.color(1.0F, 1.0F, 1.0F, f2);
			GlStateManager.doPolygonOffset(-3.0F, -3.0F);
			GlStateManager.enablePolygonOffset();
			dispatcher.renderBlockBrightness(Main.tenTNT.getDefaultState(), 1.0F);
			GlStateManager.doPolygonOffset(0.0F, 0.0F);
			GlStateManager.disablePolygonOffset();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture2D();
		}

		GlStateManager.popMatrix();
		//super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	protected ResourceLocation getEntityTexture(EntityTenTNTPrimed entity) {
		return null;
	}
}
