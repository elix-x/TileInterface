package code.elix_x.coremods.tileinterface.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import code.elix_x.coremods.tileinterface.TileInterfaceBase;
import code.elix_x.coremods.tileinterface.tileentities.TileEntityEnderQuantumInterface;
import code.elix_x.coremods.tileinterface.util.BlockPos;

public class BlockEnderQuantumInterface extends BlockContainer {

	public BlockEnderQuantumInterface() {
		super(Material.iron);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(Block.soundTypeMetal);
		setBlockName("enderquantumtileinterface");
		setCreativeTab(CreativeTabs.tabRedstone);

		setBlockTextureName(TileInterfaceBase.MODID + ":enderquantumtileinterface");
	}

	/*@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float f, float g, float t) {		
		BlockPos pos = ((TileEntityEnderQuantumInterface) world.getTileEntity(x, y, z)).getDirectedBlock();
		World ww = MinecraftServer.getServer().worldServerForDimension(((TileEntityEnderQuantumInterface) world.getTileEntity(x, y, z)).getLinkToDim());
		return pos.getBlock(ww).onBlockActivated(ww, pos.x, pos.y, pos.z, player, side, f, g, t);
	}*/

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityEnderQuantumInterface();
	}
}
