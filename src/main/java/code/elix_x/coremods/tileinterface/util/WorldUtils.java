package code.elix_x.coremods.tileinterface.util;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldUtils {

	public static BlockPos getBlockPosWithOffset(World world, int x, int y, int z, ForgeDirection direction) {
		x += direction.offsetX;
		y += direction.offsetY;
		z += direction.offsetZ;
		return new BlockPos(x, y, z);
	}
	
	public static Block getBlockWithOffset(World world, int x, int y, int z, ForgeDirection direction) {
		BlockPos pos = getBlockPosWithOffset(world, x, y, z, direction);
		return world.getBlock(pos.x, pos.y, pos.z);
	}

}
