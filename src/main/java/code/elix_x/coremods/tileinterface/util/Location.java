package code.elix_x.coremods.tileinterface.util;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class Location extends ChunkPosition {

	public Location(int x, int y, int z) {
		super(x, y, z);
	}

	public Location(ChunkPosition pos) {
		this(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ);
	}

	public Location(TileEntity tile) {
		this(tile.xCoord, tile.yCoord, tile.zCoord);
	}

	public Location(MovingObjectPosition pos) {
		this(pos.blockX, pos.blockY, pos.blockZ);
	}

	public Location(Vec3 vector) {
		super(vector);
	}

	public int getX() {
		return chunkPosX;
	}

	public int getY() {
		return chunkPosY;
	}

	public int getZ() {
		return chunkPosZ;
	}
	
	protected Location create(int x, int y, int z){
		return new Location(x, y, z);
	}

	/**
	 * @return A new location with the same coordinates.
	 */
	public Location copy() {
		return new Location(this);
	}

	public Block getBlock(IBlockAccess world) {
		return world.getBlock(chunkPosX, chunkPosY, chunkPosZ);
	}

	public int getBlockMeta(IBlockAccess world) {
		return world.getBlockMetadata(chunkPosX, chunkPosY, chunkPosZ);
	}

	public Location rotate(ForgeDirection direction, int degrees) {
		degrees = Math.abs(degrees) % 4;// should ensure that degrees must be 0, 1, 2, or 3 and nothing else

		if (degrees == 0)
			return this.copy();

		switch (direction) {
		case DOWN:
			return rotateY(this, (degrees + 2) % 4);
		case UP:
			return rotateY(this, degrees);
		case NORTH:
			return rotateZ(this, (degrees + 2) % 4);
		case SOUTH:
			return rotateZ(this, degrees);
		case WEST:
			return rotateX(this, (degrees + 2) % 4);
		case EAST:
			return rotateX(this, degrees);
		case UNKNOWN:
		default:
			return this.copy();
		}
	}

	public Location reflect(ForgeDirection direction) {
		switch (direction) {
		case DOWN:
		case UP:
			return reflectY();
		case NORTH:
		case SOUTH:
			return reflectZ();
		case WEST:
		case EAST:
			return reflectX();
		case UNKNOWN:
		default:
			return this.copy();
		}
	}

	private Location rotateX(Location loc, int degrees) {
		if (degrees > 1)
			loc = rotateX(loc, --degrees);

		return create(loc.chunkPosX, loc.chunkPosZ, -loc.chunkPosY);
	}

	private Location rotateY(Location loc, int degrees) {
		if (degrees > 1)
			loc = rotateY(loc, --degrees);

		return create(-loc.chunkPosZ, loc.chunkPosY, loc.chunkPosX);
	}

	private Location rotateZ(Location loc, int degrees) {
		if (degrees > 1)
			loc = rotateZ(loc, --degrees);

		return create(loc.chunkPosY, -loc.chunkPosX, loc.chunkPosZ);
	}

	private Location reflectX() {
		return create(-chunkPosX, chunkPosY, chunkPosZ);
	}

	private Location reflectY() {
		return create(chunkPosX, -chunkPosY, chunkPosZ);
	}

	private Location reflectZ() {
		return create(chunkPosX, chunkPosY, -chunkPosZ);
	}

	public Location translate(ChunkPosition other) {
		return create(chunkPosX + other.chunkPosX, chunkPosY + other.chunkPosY, chunkPosZ + other.chunkPosZ);
	}

	public Location translate(int direction, int amount) {
		return translate(ForgeDirection.getOrientation(direction), amount);
	}

	public Location translate(int x, int y, int z) {
		return translate(create(x, y, z));
	}

	public Location translate(ForgeDirection direction, int amount) {
		return translate(direction.offsetX * amount, direction.offsetY * amount, direction.offsetZ * amount);
	}

	@Override
	public String toString() {
		return "Location [x=" + chunkPosX + ", y=" + chunkPosY + ", z=" + chunkPosZ + "]";
	}

	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger("x", chunkPosX);
		tag.setInteger("y", chunkPosY);
		tag.setInteger("z", chunkPosZ);
	}

	public void writeToNBT(NBTTagCompound tag, String name) {
		NBTTagCompound subTag = new NBTTagCompound();
		writeToNBT(subTag);
		tag.setTag(name, subTag);
	}

	public Location(NBTTagCompound tag) {
		this(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));
	}

	public Location(NBTTagCompound tag, String name) {
		this(tag.getCompoundTag(name));
	}
}