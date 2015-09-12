package code.elix_x.coremods.tileinterface.tileentities;

import code.elix_x.coremods.tileinterface.quantum.QuantumEtanglementManager;
import code.elix_x.coremods.tileinterface.util.BlockPos;
import code.elix_x.coremods.tileinterface.util.WorldUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityQuantumInterface extends TileEntity {
	
	public TileEntityQuantumInterface() {
		
	}
	
	public ForgeDirection getDirection() {
		return ForgeDirection.getOrientation(/*worldObj.getBlockMetadata(xCoord, yCoord, zCoord)*/ QuantumEtanglementManager.getRealMetadataAt(worldObj, xCoord, yCoord, zCoord));
	}

	public BlockPos getDirectedBlock(){
		return WorldUtils.getBlockPosWithOffset(worldObj, xCoord, yCoord, zCoord, getDirection());
	}

	public TileEntity getDirectedTileEntity(){
		BlockPos pos = getDirectedBlock();
		return worldObj.getTileEntity(pos.x, pos.y, pos.z);
	}
	
}
