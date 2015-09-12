package code.elix_x.coremods.tileinterface.core;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;
import code.elix_x.coremods.tileinterface.quantum.QuantumEtanglementManager;

public class AsmHooks {

	/*public static Block getBlockAt(World world, int x, int y, int z, Block block){
		return QuantumEtanglementManager.getBlockAt(world, x, y, z, block);
	}

	public static int getBlockMetadataAt(World world, int x, int y, int z, int meta){
		return QuantumEtanglementManager.getBlockMetadataAt(world, x, y, z, meta);
	}

	public static TileEntity getTileEntityAt(World world, int x, int y, int z, TileEntity tileentity){
		return QuantumEtanglementManager.getTileEntityAt(world, x, y, z, tileentity);
	}*/

	public static Block getBlockAt(Chunk chunk, int x, int y, int z, Block block){
		return QuantumEtanglementManager.getBlockAt(chunk, x, y, z, block);
	}

	public static int getBlockMetadataAt(Chunk chunk, int x, int y, int z, int meta){
			return QuantumEtanglementManager.getBlockMetadataAt(chunk, x, y, z, meta);
	}

	public static TileEntity getTileEntityAt(Chunk chunk, int x, int y, int z, TileEntity tileentity){
		return QuantumEtanglementManager.getTileEntityAt(chunk, x, y, z, tileentity);
	}
}
