package code.elix_x.coremods.tileinterface.util;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldLocation extends Location {

	public final World world;

	public WorldLocation(WorldLocation other) {
		super(other);
		world = other.world;
	}

	public WorldLocation(int x, int y, int z, World world) {
		super(x, y, z);
		this.world = world;
	}

	public WorldLocation(ChunkPosition pos, World world) {
		super(pos);
		this.world = world;
	}

	public WorldLocation(TileEntity tile) {
		super(tile);
		world = tile.getWorldObj();
	}

	public WorldLocation(MovingObjectPosition pos) {
		super(pos);
		world = pos.entityHit.worldObj;
	}

	public WorldLocation(Vec3 vector, World world) {
		super(vector);
		this.world = world;
	}

	public WorldLocation(NBTTagCompound tag, World world) {
		super(tag);
		this.world = world;
	}

	public WorldLocation(NBTTagCompound tag, String name, World world) {
		super(tag, name);
		this.world = world;
	}

	@Override
	protected WorldLocation create(int x, int y, int z) {
		return new WorldLocation(x, y, z, world);
	}

	/**
	 * @return A new location with the same coordinates.
	 */
	@Override
	public WorldLocation copy() {
		return new WorldLocation(this);
	}

	public Block getBlock() {
		return super.getBlock(world);
	}

	public int getBlockMeta() {
		return super.getBlockMeta(world);
	}

	@Override
	public WorldLocation rotate(ForgeDirection direction, int degrees) {
		return (WorldLocation) super.rotate(direction, degrees);
	}

	@Override
	public WorldLocation reflect(ForgeDirection direction) {
		return (WorldLocation) super.reflect(direction);
	}

	@Override
	public WorldLocation translate(ChunkPosition other) {
		return (WorldLocation) super.translate(other);
	}

	@Override
	public WorldLocation translate(int direction, int amount) {
		return (WorldLocation) super.translate(direction, amount);
	}

	@Override
	public WorldLocation translate(int x, int y, int z) {
		return (WorldLocation) super.translate(x, y, z);
	}

	@Override
	public WorldLocation translate(ForgeDirection direction, int amount) {
		return (WorldLocation) super.translate(direction, amount);
	}
	
	@Override
	public boolean equals(Object other){
		if(other instanceof WorldLocation)
			return super.equals(other) && this.world.provider.dimensionId == ((WorldLocation) other).world.provider.dimensionId;
		else
			return super.equals(other);
	}
}