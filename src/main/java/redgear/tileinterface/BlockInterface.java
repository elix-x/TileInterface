package redgear.tileinterface;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.tools.IToolWrench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInterface extends BlockContainer {
	
	private static Icon[] icons;
	private final int[][] rotationMatrix = { //side : meta = icon
			{0, 1, 4, 4, 4, 4}, //down
			{1, 0, 2, 2, 2, 2}, //up 
			{2, 2, 0, 1, 5, 3}, //north
			{4, 4, 1, 0, 3, 5}, //south
			{5, 5, 3, 5, 0, 1}, //west
			{3, 3, 5, 3, 1, 0} //east
	};

	protected BlockInterface(int id) {
		super(id, Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityInterface();
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
    public void registerIcons(IconRegister iReg){
        icons = new Icon[6];
        
        icons[0] = iReg.registerIcon("redgear_tileinterface:front");
        icons[1] = iReg.registerIcon("redgear_tileinterface:back");
        icons[2] = iReg.registerIcon("redgear_tileinterface:up");
        icons[3] = iReg.registerIcon("redgear_tileinterface:right");
        icons[4] = iReg.registerIcon("redgear_tileinterface:down");
        icons[5] = iReg.registerIcon("redgear_tileinterface:left");
    }
    
    @Override
    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int meta){
        return icons[rotationMatrix[meta][side]];
    }

	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float f, float g, float t){
		if(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IToolWrench)
			return false;
		
        ForgeDirection direct = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
        int ID = world.getBlockId(x + direct.offsetX,  y + direct.offsetY, z + direct.offsetZ);
        
        if(ID > 0 && ID < Block.blocksList.length && Block.blocksList[ID] != null && (!(Block.blocksList[ID] instanceof BlockInterface) || direct != ForgeDirection.getOrientation(world.getBlockMetadata(x + direct.offsetX,  y + direct.offsetY, z + direct.offsetZ)).getOpposite()))
        	return Block.blocksList[ID].onBlockActivated(world, x + direct.offsetX,  y + direct.offsetY, z + direct.offsetZ, player, side, f, g, t);
        else
        	return false;
        
    }
	
	/**
     * Rotate the block. For vanilla blocks this rotates around the axis passed in (generally, it should be the "face" that was hit).
     * Note: for mod blocks, this is up to the block and modder to decide. It is not mandated that it be a rotation around the
     * face, but could be a rotation to orient *to* that face, or a visiting of possible rotations.
     * The method should return true if the rotation was successful though.
     *
     * @param worldObj The world
     * @param x X position
     * @param y Y position
     * @param z Z position
     * @param axis The axis to rotate around
     * @return True if the rotation was successful, False if the rotation failed, or is not possible
     */
	@Override
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection side){
		ForgeDirection meta = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
		
		world.setBlock(x, y, z, this.blockID, meta.getRotation(side).ordinal(), 2);
		
    	return true;
    }
	
	/**
     * Get the rotations that can apply to the block at the specified coordinates. Null means no rotations are possible.
     * Note, this is up to the block to decide. It may not be accurate or representative.
     * @param worldObj The world
     * @param x X position
     * @param y Y position
     * @param z Z position
     * @return An array of valid axes to rotate around, or null for none or unknown
     */
    public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z)
    {
        return ForgeDirection.VALID_DIRECTIONS;
    }
}
