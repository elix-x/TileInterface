package code.elix_x.coremods.tileinterface.quantum;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import code.elix_x.coremods.tileinterface.TileInterfaceBase;
import code.elix_x.coremods.tileinterface.tileentities.TileEntityQuantumInterface;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class QuantumEtanglementManager {

	public static Logger logger = LogManager.getLogger("Quantum Etanglement");

	//	private static boolean b = false;
	//	private static boolean t = false;

	//	public static int getBlockMetadataAt(World world, int x, int y, int z, int meta){
	//		/*	b = true;
	//		Block block = world.getBlock(x, y, z);
	//		b = false;
	//		if(block == TileInterfaceBase.quantumInterfaceBlock){
	//			logger.info("Block is quantumly etangled");
	//
	//			t = true;
	//			logger.info("Setted t to " + t);
	//
	//			TileEntity tileentity = world.getTileEntity(x, y, z);
	//
	//			t = false;
	//			logger.info("Setted t to " + t);
	//
	//			logger.info("Obtained tileentity: " + tileentity);
	//
	//			TileEntityQuantumInterface qi = (TileEntityQuantumInterface) tileentity;
	//			return qi.getDirectedBlock().getBlockMetadata(world);
	//		}
	//		 */
	//		Block block = getRealBlock(world, x, y, z);
	//		if(block == TileInterfaceBase.quantumInterfaceBlock){
	//
	//			TileEntity tileentity = getRealTileEntity(world, x, y, z);
	//
	//			TileEntityQuantumInterface qi = (TileEntityQuantumInterface) tileentity;
	//			return qi.getDirectedBlock().getBlockMetadata(world);
	//		}
	//		return meta;
	//	}
	//
	//	public static Block getBlockAt(World world, int x, int y, int z, Block block){
	//		/*if(!b && block == TileInterfaceBase.quantumInterfaceBlock){
	//			logger.info("Block is quantumly etangled and not b check");
	//
	//			t = true;
	//			TileEntity tileentity = world.getTileEntity(x, y, z);
	//			t = false;
	//
	//			logger.info("Got quantum tile entity: " + tileentity);
	//
	//			TileEntityQuantumInterface qi = (TileEntityQuantumInterface) tileentity;
	//			return qi.getDirectedBlock().getBlock(world);
	//		}*/
	//		
	//		if(block == TileInterfaceBase.quantumInterfaceBlock){
	//			
	//			TileEntity tileentity = getRealTileEntity(world, x, y, z);
	//
	//			TileEntityQuantumInterface qi = (TileEntityQuantumInterface) tileentity;
	//			return qi.getDirectedBlock().getBlock(world);
	//		}
	//		return block;
	//	}

	public static int getBlockMetadataAt(Chunk chunk, int x, int y, int z, int meta){
		/*TileEntity tileentity = chunk.worldObj.getTileEntity(chunk.xPosition + x, y, chunk.zPosition + z);
		if(tileentity != null){
			return tileentity.getBlockMetadata();
		}*/
		try{
			TileEntity tileentity = getRealTileEntity(chunk.worldObj, chunk.xPosition + x, y, chunk.zPosition + z);
			if(tileentity instanceof TileEntityQuantumInterface){
				TileEntityQuantumInterface qi = (TileEntityQuantumInterface) tileentity;
				return qi.getDirectedTileEntity().getBlockMetadata();
			}
		}catch(RuntimeException e){

		}
		return meta;
	}

	public static Block getBlockAt(Chunk chunk, int x, int y, int z, Block block){
		try{
			if(block == TileInterfaceBase.enderQuantumInterfaceBlock){
//				TileEntity tileentity = getRealTileEntity(chunk.worldObj, chunk.xPosition + x, y, chunk.zPosition + z);
				TileEntity tileentity = getRealTileEntity(chunk, x, y, z);
				if(tileentity instanceof TileEntityQuantumInterface){
					TileEntityQuantumInterface qi = (TileEntityQuantumInterface) tileentity;
					return qi.getDirectedTileEntity().getBlockType();
				}
			}
		}catch(RuntimeException e){

		}
		return block;
	}

	public static TileEntity getTileEntityAt(Chunk chunk, int x, int y, int z, TileEntity tileentity){
		try{
			if(tileentity instanceof TileEntityQuantumInterface){
				TileEntityQuantumInterface qi = (TileEntityQuantumInterface) tileentity;
				return qi.getDirectedTileEntity();
			}
		}catch(RuntimeException e){

		}
		return tileentity;
	}

	//	public static int getBlockMetadataAt(World world, int x, int y, int z, int meta){
	//		return meta;
	//	}
	//
	//	public static Block getBlockAt(World world, int x, int y, int z, Block block){
	//		return block;
	//	}
	//
	//	public static TileEntity getTileEntityAt(World world, int x, int y, int z, TileEntity tileentity){
	//		return tileentity;
	//	}

	/*private static Block getRealBlock(World world, int x, int y, int z){
		if (x >= -30000000 && z >= -30000000 && x < 30000000 && z < 30000000 && y >= 0 && y < 256)
		{
			Chunk chunk = null;

			try
			{
				chunk = world.getChunkFromChunkCoords(x >> 4, z >> 4);
				return chunk.getBlock(x & 15, y, z & 15);
			}
			catch (Throwable throwable)
			{
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Exception getting block type in world");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("Requested block coordinates");
				crashreportcategory.addCrashSection("Found chunk", Boolean.valueOf(chunk == null));
				crashreportcategory.addCrashSection("Location", CrashReportCategory.getLocationInfo(x, y, z));
				throw new ReportedException(crashreport);
			}
		}
		else
		{
			return Blocks.air;
		}
	}*/

	public static TileEntity getRealTileEntity(World world, int x, int y, int z){
		if (y >= 0 && y < 256)
		{
			TileEntity tileentity = null;
			int l;
			TileEntity tileentity1;

			/*if (tileentity == null)
			{
				Chunk chunk = world.getChunkFromChunkCoords(x >> 4, z >> 4);

				if (chunk != null)
				{
					tileentity = chunk.func_150806_e(x & 15, y, z & 15);
				}
			}*/

			if (tileentity == null)
			{
				Chunk chunk = world.getChunkFromBlockCoords(x, z);

				Set<Map.Entry> set = chunk.chunkTileEntityMap.entrySet();

				for(Map.Entry entry : set){
					tileentity1 = (TileEntity) entry.getValue();
					if (!tileentity1.isInvalid() && tileentity1.xCoord == x && tileentity1.yCoord == y && tileentity1.zCoord == z)
					{
						tileentity = tileentity1;
						break;
					}
				}
			}

			if (tileentity == null)
			{
				/* for (l = 0; l < world.addedTileEntityList.size(); ++l)
	                {
	                    tileentity1 = (TileEntity)world.addedTileEntityList.get(l);

	                    if (!tileentity1.isInvalid() && tileentity1.xCoord == x && tileentity1.yCoord == y && tileentity1.zCoord == z)
	                    {
	                        tileentity = tileentity1;
	                        break;
	                    }
	                }*/
				for(Object obj : (List) ObfuscationReflectionHelper.getPrivateValue(World.class, world, "addedTileEntityList", "field_147484_a")){
					tileentity1 = (TileEntity) obj;
					if (!tileentity1.isInvalid() && tileentity1.xCoord == x && tileentity1.yCoord == y && tileentity1.zCoord == z)
					{
						tileentity = tileentity1;
						break;
					}
				}
			}

			return tileentity;
		}
		else
		{
			return null;
		}
	}
	
	public static TileEntity getRealTileEntity(Chunk chunk, int x, int y, int z){
		if (y >= 0 && y < 256)
		{
			TileEntity tileentity = null;
			int l;
			TileEntity tileentity1;

			/*if (tileentity == null)
			{
				Chunk chunk = world.getChunkFromChunkCoords(x >> 4, z >> 4);

				if (chunk != null)
				{
					tileentity = chunk.func_150806_e(x & 15, y, z & 15);
				}
			}*/

			if (tileentity == null)
			{
//				Chunk chunk = world.getChunkFromBlockCoords(x, z);

				Set<Map.Entry> set = chunk.chunkTileEntityMap.entrySet();

				for(Map.Entry entry : set){
					tileentity1 = (TileEntity) entry.getValue();
					if (!tileentity1.isInvalid() && tileentity1.xCoord == x && tileentity1.yCoord == y && tileentity1.zCoord == z)
					{
						tileentity = tileentity1;
						break;
					}
				}
			}

			if (tileentity == null)
			{
				/* for (l = 0; l < world.addedTileEntityList.size(); ++l)
	                {
	                    tileentity1 = (TileEntity)world.addedTileEntityList.get(l);

	                    if (!tileentity1.isInvalid() && tileentity1.xCoord == x && tileentity1.yCoord == y && tileentity1.zCoord == z)
	                    {
	                        tileentity = tileentity1;
	                        break;
	                    }
	                }*/
				for(Object obj : (List) ObfuscationReflectionHelper.getPrivateValue(World.class, chunk.worldObj, "addedTileEntityList", "field_147484_a")){
					tileentity1 = (TileEntity) obj;
					if (!tileentity1.isInvalid() && tileentity1.xCoord == x && tileentity1.yCoord == y && tileentity1.zCoord == z)
					{
						tileentity = tileentity1;
						break;
					}
				}
			}

			return tileentity;
		}
		else
		{
			return null;
		}
	}

	public static int getRealMetadataAt(World world, int x, int y, int z) {
		Chunk chunk = world.getChunkFromBlockCoords(x, z);
		ExtendedBlockStorage[] storage = ObfuscationReflectionHelper.getPrivateValue(Chunk.class, chunk, "storageArrays", "field_76652_q");
		if (y >> 4 >= storage.length)
		{
			return 0;
		}
		else
		{
			ExtendedBlockStorage extendedblockstorage = storage[y >> 4];
			return extendedblockstorage != null ? extendedblockstorage.getExtBlockMetadata(x & 15, y & 15, z & 15) : 0;
		}
	}

}
