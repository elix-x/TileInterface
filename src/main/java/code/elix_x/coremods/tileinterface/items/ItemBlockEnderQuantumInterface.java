package code.elix_x.coremods.tileinterface.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import code.elix_x.coremods.tileinterface.quantum.QuantumEtanglementManager;
import code.elix_x.coremods.tileinterface.tileentities.TileEntityEnderQuantumInterface;
import code.elix_x.coremods.tileinterface.util.BlockPos;

public class ItemBlockEnderQuantumInterface extends ItemBlock{

	public ItemBlockEnderQuantumInterface(Block block) {
		super(block);
		setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(!hasSyncData(itemstack) || player.isSneaking()){
			setSyncData(itemstack, player.worldObj.provider.dimensionId, x, y, z);
			player.addChatComponentMessage(new ChatComponentText("Succesfully linked ender quantum interface"));
			return true;
		} else if(hasSyncData(itemstack)){
			return super.onItemUse(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);
		}
		return false;
	}

	@Override
	public boolean placeBlockAt(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		if(super.placeBlockAt(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata)){
			
//			TileEntityEnderQuantumInterface tileentity = (TileEntityEnderQuantumInterface) world.getTileEntity(x, y, z);
			TileEntityEnderQuantumInterface tileentity = (TileEntityEnderQuantumInterface) QuantumEtanglementManager.getRealTileEntity(world, x, y, z);
			
			tileentity.setLinkedTo(getLinkToDim(itemstack), getLinkToPos(itemstack));
			
			return true;
		}
		return false;
	}

	public static void setupTags(ItemStack itemstack){
		NBTTagCompound nbt = itemstack.stackTagCompound;
		if(nbt == null){
			nbt = new NBTTagCompound();
		}
		
		NBTTagCompound tag = nbt.getCompoundTag("linkTo");
		if(tag == null){
			tag = new NBTTagCompound();
		}
		
		if(!tag.hasKey("linked")){
			tag.setBoolean("linked", false);
		}
		
		if(!tag.hasKey("linkToDim")){
			tag.setInteger("linkToDim", 0);
		}
		
		if(!tag.hasKey("linkToX")){
			tag.setInteger("linkToX", 0);
		}
		
		if(!tag.hasKey("linkToY")){
			tag.setInteger("linkToY", 0);
		}
		
		if(!tag.hasKey("linkToZ")){
			tag.setInteger("linkToZ", 0);
		}
		
		nbt.setTag("linkTo", tag);
		itemstack.stackTagCompound = nbt;
	}
	
	public static boolean hasSyncData(ItemStack itemstack) {
		setupTags(itemstack);
		return itemstack.stackTagCompound.getCompoundTag("linkTo").getBoolean("linked");
	}
	
	public static int getLinkToDim(ItemStack itemstack) {
		setupTags(itemstack);
		return itemstack.stackTagCompound.getCompoundTag("linkTo").getInteger("patnerDim");
	}
	
	public static BlockPos getLinkToPos(ItemStack itemstack){
		setupTags(itemstack);
		return new BlockPos(itemstack.stackTagCompound.getCompoundTag("linkTo").getInteger("linkToX"),  itemstack.stackTagCompound.getCompoundTag("linkTo").getInteger("linkToY"),  itemstack.stackTagCompound.getCompoundTag("linkTo").getInteger("linkToZ"));
	}
	
	public static void setSyncData(ItemStack itemstack, int dim, int x, int y, int z){
		setupTags(itemstack);
		NBTTagCompound tag =  itemstack.stackTagCompound.getCompoundTag("linkTo");
		tag.setBoolean("linked", true);
		tag.setInteger("linkToDim", dim);
		tag.setInteger("linkToX", x);
		tag.setInteger("linkToY", y);
		tag.setInteger("linkToZ", z);
		itemstack.stackTagCompound.setTag("linkTo", tag);
	}
}
