package code.elix_x.coremods.tileinterface.em;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class EMItemStack extends EM {

	private ItemStack itemstack = null;
	
	public EMItemStack() {
		super("itemstack");
	}

	public EMItemStack(ItemStack i){
		this();
		itemstack = i;
	}
	
	public ItemStack getItemStack() {
		return itemstack;
	}
	
	public boolean canStore(ItemStack i) {
		return i != null ? itemstack.getItem() == i.getItem() && ItemStack.areItemStackTagsEqual(itemstack, i) : false;
	}
	
	public boolean store(ItemStack i){
		if(!canStore(i)){
			return false;
		}
		itemstack.stackSize += i.stackSize;
		return true;
	}
	
	@Override
	public NBTTagCompound writeToNbt(NBTTagCompound nbt) {
		nbt = super.writeToNbt(nbt);
		nbt.setTag("itemstack", itemstack.writeToNBT(new NBTTagCompound()));
		return nbt;
	}
	
	@Override
	public void readFromNbt(NBTTagCompound nbt) {
		super.readFromNbt(nbt);
		itemstack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("itemstack"));
	}
}

