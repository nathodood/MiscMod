package com.nathodood.miscmod.entity.tnt;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityOneHundredTNTPrimed extends Entity {
	private int fuse, power;

	private static final DataParameter<Integer> FUSE = EntityDataManager
			.<Integer>createKey(EntityOneHundredTNTPrimed.class, DataSerializers.VARINT);

	public EntityOneHundredTNTPrimed(World world) {
		super(world);
	}

	public EntityOneHundredTNTPrimed(World world, BlockPos pos, int fuse, int power) {
		super(world);

		this.preventEntitySpawning = true;
		this.isImmuneToFire = true;
		this.setSize(0.98F, 0.98F);

		this.setPosition(pos.getX(), pos.getY(), pos.getZ());
		float f = (float) (Math.random() * (Math.PI * 2D));
		this.motionX = (double) (-((float) Math.sin((double) f)) * 0.02F);
		this.motionY = 0.20000000298023224D;
		this.motionZ = (double) (-((float) Math.cos((double) f)) * 0.02F);
		this.setFuse(fuse);
		this.power = power;
		this.prevPosX = pos.getX();
		this.prevPosY = pos.getY();
		this.prevPosZ = pos.getZ();
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (!this.hasNoGravity()) {
			this.motionY -= 0.03999999910593033D;
		}

		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}

		this.fuse--;

		if (this.fuse <= 0) {
			this.setDead();

			if (!world.isRemote) {
				explode();
			}
		}

		else {
			this.handleWaterMovement();
			this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D,
					0.0D);
		}
	}

	// entity properties and stuff
	@Override
	protected boolean canTriggerWalking() {
		return super.canTriggerWalking();
	}

	@Override
	public boolean canBeCollidedWith() {
		return super.canBeCollidedWith();
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(FUSE, Integer.valueOf(this.fuse));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setShort("Fuse", (short) this.fuse);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.setFuse(compound.getShort("Fuse"));
	}

	public void explode() {
		world.createExplosion(this, this.prevPosX, this.prevPosY, this.prevPosZ, this.power, true);
	}

	public void setFuse(int fuse) {
		this.dataManager.set(FUSE, Integer.valueOf(fuse));
		this.fuse = fuse;
	}

	public int getFuse() {
		return this.fuse;
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		if (FUSE.equals(key)) {
			this.fuse = ((Integer) this.dataManager.get(FUSE)).intValue();
		}
	}

	@Override
	public String getName() {
		return "one_hundred_tnt";
	}
}
