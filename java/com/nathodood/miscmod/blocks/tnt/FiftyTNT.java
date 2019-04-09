package com.nathodood.miscmod.blocks.tnt;

import com.nathodood.miscmod.entity.tnt.EntityFiftyTNTPrimed;
import com.nathodood.miscmod.entity.tnt.EntityOneHundredTNTPrimed;
import com.nathodood.miscmod.entity.tnt.EntityRandomTNTPrimed;
import com.nathodood.miscmod.entity.tnt.EntityTenTNTPrimed;
import com.nathodood.miscmod.main.Main;
import com.nathodood.miscmod.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class FiftyTNT extends Block implements IHasModel {
	public static final IProperty EXPLODE = PropertyBool.create("explode_t");
	Entity entityTNT;
	protected int fuse, power;
	private String name;

	public FiftyTNT(String name, int fuse, int power) {
		super(Material.TNT);

		this.fuse = fuse;
		this.power = 0;
		this.name = name;

		setRegistryName(name);
		setUnlocalizedName(Main.registryToUnlocalizedName(name));
		setDefaultState(this.blockState.getBaseState().withProperty(EXPLODE, Boolean.valueOf(false)));

		setCreativeTab(Main.tabMiscMod);
		setSoundType(SoundType.PLANT);
		setHardness(0);

		Main.BLOCKS.add(this);
		Main.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	private void spawnTNT(World world, BlockPos pos, int fuse, int power, IBlockState state) {
		if (((Boolean) state.getValue(EXPLODE)).booleanValue()) {
			this.entityTNT = getTNTFromName(world, pos);
			world.spawnEntity(this.entityTNT);
			world.playSound(null, this.entityTNT.getPosition(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1, 1);
			world.setBlockToAir(pos);
		}
	}

	public static void damageItem(World world, EntityPlayer player) {
		ItemStack itemInHand = player.getHeldItemMainhand();
		Item item = itemInHand.getItem();

		if (item == Items.FLINT_AND_STEEL && !player.capabilities.isCreativeMode && !world.isRemote)
			itemInHand.damageItem(1, player);

		else if (item == Items.FIRE_CHARGE && !player.capabilities.isCreativeMode)
			itemInHand.shrink(1);
	}

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItemMainhand();
		Item itemInHand = stack.getItem();

		if (!stack.isEmpty() && (itemInHand == Items.FLINT_AND_STEEL || itemInHand == Items.FIRE_CHARGE)
				&& !world.isRemote) {

			spawnTNT(world, pos, this.fuse, this.power, state.withProperty(EXPLODE, Boolean.valueOf(true)));

			damageItem(world, player);

			return true;
		}

		else {
			return false;
		}
	}

	public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
		this.entityTNT = getTNTFromName(world, pos);
		world.spawnEntity(this.entityTNT);
		world.playSound(null, this.entityTNT.getPosition(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1, 1);
		world.setBlockToAir(pos);
	}

	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (world.isBlockPowered(pos)) {
			spawnTNT(world, pos, this.fuse, this.power, state.withProperty(EXPLODE, Boolean.valueOf(true)));
		}
	}

	public void registerModels() {
		Main.commonProxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		Main.commonProxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "explode_t=false");
		Main.commonProxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "explode_t=true");
	}

	public String getName() {
		return this.name;
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);

		if (worldIn.isBlockPowered(pos)) {
			this.onBlockDestroyedByPlayer(worldIn, pos, state.withProperty(EXPLODE, Boolean.valueOf(true)));
			worldIn.setBlockToAir(pos);
		}
	}

	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}

	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!world.isRemote && entityIn instanceof EntityArrow) {
			EntityArrow entityarrow = (EntityArrow) entityIn;

			if (entityarrow.isBurning()) {
				spawnTNT(world, pos, this.fuse, this.power, state.withProperty(EXPLODE, Boolean.valueOf(true)));
			}
		}
	}

	public int getFuse() {
		return this.fuse;
	}

	public int getPower() {
		return this.power;
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(EXPLODE, Boolean.valueOf((meta & 1) > 0));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Boolean) state.getValue(EXPLODE)).booleanValue() ? 1 : 0;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { EXPLODE });
	}
	
	private Entity getTNTFromName(World world, BlockPos pos) {
		if (!world.isRemote) {
			/*if (this.name.equals("ten_tnt"))
				return new EntityTenTNTPrimed(world, pos, this.fuse, this.power);

			else if (this.name.equals("fifty_tnt"))
				return new EntityFiftyTNTPrimed(world, pos, this.fuse, this.power);

			else if (this.name.equals("one_hundred_tnt"))
				return new EntityOneHundredTNTPrimed(world, pos, this.fuse, this.power);

			else if (this.name.equals("random_tnt"))
				return new EntityRandomTNTPrimed(world, pos);*/
			
			return new EntityFiftyTNTPrimed(world, pos, this.fuse, 0);
		}
		
		return null;
	}
}
