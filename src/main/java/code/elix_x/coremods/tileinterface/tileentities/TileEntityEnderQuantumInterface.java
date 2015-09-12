package code.elix_x.coremods.tileinterface.tileentities;

import code.elix_x.coremods.tileinterface.util.BlockPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEnderQuantumInterface extends TileEntityQuantumInterface{

	private int linkToDim;
	private int linkToX;
	private int linkToY;
	private int linkToZ;
	
	public TileEntityEnderQuantumInterface() {
		super();
	}
	
	public TileEntityEnderQuantumInterface(int d, int x, int y, int z){
		this();
		linkToDim = d;
		linkToX = x;
		linkToY = y;
		linkToZ = z;
	}
	
	@Override
	public BlockPos getDirectedBlock() {
		return new BlockPos(linkToX, linkToY, linkToZ);
	}
	
	@Override
	public TileEntity getDirectedTileEntity() {
		return MinecraftServer.getServer().worldServerForDimension(linkToDim).getTileEntity(linkToX, linkToY, linkToZ);
	}

	public void setLinkedTo(int dim, BlockPos pos) {
		linkToDim = dim;
		linkToX = pos.x;
		linkToY = pos.y;
		linkToZ = pos.z;
	}
	
	public int getLinkToDim() {
		return linkToDim;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		linkToDim = nbt.getInteger("linkToDim");
		linkToX = nbt.getInteger("linkToX");
		linkToY = nbt.getInteger("linkToY");
		linkToZ = nbt.getInteger("linkToZ");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("linkToDim", linkToDim);
		nbt.setInteger("linkToX", linkToX);
		nbt.setInteger("linkToY", linkToY);
		nbt.setInteger("linkToZ", linkToZ);
	}
}
