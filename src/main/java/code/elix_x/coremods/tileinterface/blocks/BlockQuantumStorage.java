package code.elix_x.coremods.tileinterface.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import code.elix_x.coremods.tileinterface.TileInterfaceBase;
import code.elix_x.coremods.tileinterface.tileentities.TileEntityQuantumStorage;

public class BlockQuantumStorage extends BlockContainer {

	public BlockQuantumStorage() {
		super(TileInterfaceBase.materialQuantum);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(Block.soundTypeMetal);
		setBlockName("quantumstorage");
		setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityQuantumStorage();
	}

}
