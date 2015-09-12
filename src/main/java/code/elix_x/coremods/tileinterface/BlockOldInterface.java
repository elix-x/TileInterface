package code.elix_x.coremods.tileinterface;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import buildcraft.api.tools.IToolWrench;
import code.elix_x.coremods.tileinterface.util.WorldLocation;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOldInterface extends BlockContainer {

	private static IIcon[] icons = new IIcon[6];
	private final int[][] rotationMatrix = { //side : meta = icon
			{0, 1, 4, 4, 4, 4 }, //down
			{1, 0, 2, 2, 2, 2 }, //up 
			{2, 2, 0, 1, 5, 3 }, //north
			{4, 4, 1, 0, 3, 5 }, //south
			{5, 5, 3, 5, 0, 1 }, //west
			{3, 3, 5, 3, 1, 0 } //east
	};

	protected BlockOldInterface() {
		super(Material.iron);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(Block.soundTypeMetal);
		setBlockName("tileinterface");
		setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new OldTileEntityInterface();
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float par6, float par7, float par8, int meta) {
		return ForgeDirection.OPPOSITES[side];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iReg) {
		icons[0] = iReg.registerIcon(TileInterfaceBase.MODID + ":front");
		icons[1] = iReg.registerIcon(TileInterfaceBase.MODID + ":back");
		icons[2] = iReg.registerIcon(TileInterfaceBase.MODID + ":up");
		icons[3] = iReg.registerIcon(TileInterfaceBase.MODID + ":right");
		icons[4] = iReg.registerIcon(TileInterfaceBase.MODID + ":down");
		icons[5] = iReg.registerIcon(TileInterfaceBase.MODID + ":left");
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public IIcon getIcon(int side, int meta) {
		return icons[rotationMatrix[meta][side]];
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float f, float g, float t) {
		if(Loader.isModLoaded("Buildcraft"))
			if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IToolWrench)
				return false;

		WorldLocation loc = new WorldLocation(x, y, z, world);

		ForgeDirection direct = ForgeDirection.getOrientation(loc.getBlockMeta());
		loc = loc.translate(direct, 1);

		Block block = loc.getBlock();

		if (!(block instanceof BlockOldInterface) || direct != ForgeDirection.getOrientation(loc.getBlockMeta()).getOpposite())
			return block.onBlockActivated(world, loc.getX(), loc.getY(), z + loc.getZ(), player, side, f, g, t);
		else
			return false;

	}

	/**
	 * Rotate the block. For vanilla blocks this rotates around the axis passed
	 * in (generally, it should be the "face" that was hit).
	 * Note: for mod blocks, this is up to the block and modder to decide. It is
	 * not mandated that it be a rotation around the
	 * face, but could be a rotation to orient *to* that face, or a visiting of
	 * possible rotations.
	 * The method should return true if the rotation was successful though.
	 * 
	 * @param worldObj The world
	 * @param x X position
	 * @param y Y position
	 * @param z Z position
	 * @param axis The axis to rotate around
	 * @return True if the rotation was successful, False if the rotation
	 * failed, or is not possible
	 */
	@Override
	public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection side) {
		ForgeDirection meta = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));

		world.setBlock(x, y, z, this, meta.getRotation(side).ordinal(), 2);

		return true;
	}

	/**
	 * Get the rotations that can apply to the block at the specified
	 * coordinates. Null means no rotations are possible.
	 * Note, this is up to the block to decide. It may not be accurate or
	 * representative.
	 * 
	 * @param worldObj The world
	 * @param x X position
	 * @param y Y position
	 * @param z Z position
	 * @return An array of valid axes to rotate around, or null for none or
	 * unknown
	 */
	@Override
	public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z) {
		return ForgeDirection.VALID_DIRECTIONS;
	}
}
