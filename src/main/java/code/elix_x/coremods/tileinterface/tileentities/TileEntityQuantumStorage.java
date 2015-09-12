package code.elix_x.coremods.tileinterface.tileentities;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import code.elix_x.ecore.energy.EnergyStack;

public class TileEntityQuantumStorage extends TileEntity implements IInventory, IFluidHandler{

	private String name;

	private QuantumStorage storage;
	private int bytesMax;

	public TileEntityQuantumStorage() {
		name = "quantumstorage";

		bytesMax = Integer.MAX_VALUE;
	}

	public boolean is(){
		return storage == null || storage instanceof QuantumItemStackStorage;
	}

	public boolean f(){
		return storage == null || storage instanceof QuantumFluidStackStorage;
	}

	public boolean e(){
		return storage == null || storage instanceof QuantumEnergyStorage;
	}

	@Override
	public int getSizeInventory() {
		return is() ? 1 : 0;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		ItemStack i = (ItemStack) (is() ? (storage != null ? storage.getStoring() : null) : null);
		if(i != null){
			i = i.copy();
			if(i.stackSize >= 64){
				i.stackSize = 63;
			}
		}
		System.out.println("Getting stack: " + i + " in " + storage);
		return i;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack i = new ItemStack(((ItemStack) storage.storing).getItem(), amount > storage.getStoringAmount() ? storage.getStoringAmount() : amount);
		storage = storage.decrease(amount);
		return i;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return (ItemStack) storage.getStoring();
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		if(is()){
			System.out.println("Putting " + itemstack + " in " + storage);
			if(storage == null){
				storage = new QuantumItemStackStorage(itemstack);
			} else {
				ItemStack i = (ItemStack) storage.getStoring();
				if(i.stackSize >= 64){
					itemstack.stackSize += i.stackSize - 63;
				} else {
					storage.setStoring(itemstack);
				}
			}
		}
	}

	@Override
	public String getInventoryName() {
		return name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return !name.equals("quantumstorage");
	}

	@Override
	public int getInventoryStackLimit() {
		return bytesMax;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		boolean b = is() ? (storage != null ? storage.canCollide(itemstack) : true) : false;
		System.out.println("Item (" + itemstack + ") valid for " + storage + " (size: " + bytesMax + ", current taken: " + storage.getStoringAmount() + ") : " + b);
		return b;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(f()){
			if(storage == null){
				if(doFill){
					storage = new QuantumFluidStackStorage(resource.copy());
				}
				return resource.amount;
			}
			if(storage.canCollide(resource)){
				FluidStack s = (FluidStack) storage.collide(resource);
				int i = 0;
				if(s == null){
					i = resource.amount;
				} else {
					i = resource.amount - s.amount;
				}
				if(!doFill){
					storage.decrease(resource.amount);
				}
				return i;
			}
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if(f()){
			if(storage == null){
				return null;
			}
			if(storage.canCollide(resource)){
				FluidStack ret = new FluidStack(resource.fluid, resource.amount > storage.getStoringAmount() ? storage.getStoringAmount() : resource.amount);
				if(doDrain){
					storage.decrease(resource.amount);
				}
				return ret;
			}
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(f()){
			if(storage == null){
				return null;
			} else {
				FluidStack ret = new FluidStack(((FluidStack) storage.getStoring()).getFluid(), maxDrain > storage.getStoringAmount() ? storage.getStoringAmount() : maxDrain);
				if(doDrain){
					storage.decrease(maxDrain);
				}
				return ret;
			}
		}
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return f();
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return f() ? (storage != null ? ((FluidStack) storage.getStoring()).getFluid() == fluid : true) : false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return f() ? (storage != null ? new FluidTankInfo[]{new FluidTankInfo((FluidStack) storage.getStoring(), bytesMax)} :  new FluidTankInfo[]{new FluidTankInfo(null, bytesMax)}) : new FluidTankInfo[]{new FluidTankInfo(null, 0)};
	}

	public abstract class QuantumStorage<S> {

		protected S storing;

		public QuantumStorage(S s){
			storing = s;
		}

		public abstract boolean canCollide(S s);
		public abstract S collide(S s);
		public abstract S getStoring();
		public abstract void setStoring(S s);
		public abstract int getStoringAmount();
		public abstract void setStoringAmount(int i);
		public abstract QuantumStorage<S> decrease(int decr);
		public abstract QuantumStorage<S> increase(int incr);
	}

	public class QuantumItemStackStorage extends QuantumStorage<ItemStack>{

		public QuantumItemStackStorage(ItemStack s) {
			super(s);
		}

		@Override
		public boolean canCollide(ItemStack s) {
			return storing != null && s != null ? storing.getItem() == s.getItem() && ItemStack.areItemStackTagsEqual(storing, s) : false;
		}

		@Override
		public ItemStack collide(ItemStack s) {
			if(canCollide(s)){
				storing.stackSize += s.stackSize;
				if(storing.stackSize > bytesMax){
					s.stackSize = bytesMax - storing.stackSize;
					return s;
				}
				return null;
			}
			return s;
		}

		@Override
		public ItemStack getStoring() {
			return storing;
		}

		@Override
		public void setStoring(ItemStack s) {
			storing = s;
		}

		@Override
		public QuantumStorage<ItemStack> decrease(int decr) {
			storing.stackSize -= decr;
			if(storing.stackSize <= 0){
				return null;
			}
			return this;
		}

		@Override
		public QuantumStorage<ItemStack> increase(int incr) {
			storing.stackSize += incr;
			if(storing.stackSize > bytesMax){
				storing.stackSize = bytesMax;
			}
			return this;
		}

		@Override
		public int getStoringAmount() {
			return storing.stackSize;
		}

		@Override
		public void setStoringAmount(int i) {
			storing.stackSize = i;
		}

	}

	public class QuantumFluidStackStorage extends QuantumStorage<FluidStack>{

		public QuantumFluidStackStorage(FluidStack s) {
			super(s);
		}

		@Override
		public boolean canCollide(FluidStack s) {
			return storing != null && s != null ? storing.isFluidEqual(s) : false;
		}

		@Override
		public FluidStack collide(FluidStack s) {
			if(canCollide(s)){
				storing.amount += s.amount;
				if(storing.amount > bytesMax){
					s.amount = bytesMax - storing.amount;
					return s;
				}
				return null;
			}
			return s;
		}

		@Override
		public FluidStack getStoring() {
			return storing;
		}

		@Override
		public void setStoring(FluidStack s) {
			storing = s;
		}

		@Override
		public QuantumStorage<FluidStack> decrease(int decr) {
			storing.amount -= decr;
			if(storing.amount <= 0){
				return null;
			}
			return this;
		}

		@Override
		public QuantumStorage<FluidStack> increase(int incr) {
			storing.amount += incr;
			if(storing.amount > bytesMax){
				storing.amount = bytesMax;
			}
			return this;
		}

		@Override
		public int getStoringAmount() {
			return storing.amount;
		}

		@Override
		public void setStoringAmount(int i) {
			storing.amount = i;
		}

	}

	public class QuantumEnergyStorage extends QuantumStorage<EnergyStack>{

		public QuantumEnergyStorage(EnergyStack s) {
			super(s);
		}

		@Override
		public boolean canCollide(EnergyStack s) {
			return true;
		}

		@Override
		public EnergyStack collide(EnergyStack s) {
			storing.increase(s.getQU());
			if(storing.getQU() > bytesMax){
				s.setQU(bytesMax - storing.getQU());
				return s;
			}
			return null;
		}

		@Override
		public EnergyStack getStoring() {
			return storing;
		}

		@Override
		public void setStoring(EnergyStack s) {
			storing = s;
		}

		@Override
		public int getStoringAmount() {
			return (int) storing.getQU();
		}

		@Override
		public void setStoringAmount(int i) {
			storing.setQU(i);
		}

		@Override
		public QuantumStorage<EnergyStack> decrease(int decr) {
			storing.decrease(decr);
			if(storing.getQU() <= 0){
				return null;
			}
			return this;
		}

		@Override
		public QuantumStorage<EnergyStack> increase(int incr) {
			storing.increase(incr);
			if(storing.getQU() > bytesMax){
				storing.setQU(bytesMax);
			}
			return this;
		}
	}

	/*private String name;

	private int bytesMax;
	private int bytesUsed;
	private int typesUsed;

	private EM[] stored;

	public TileEntityQuantumStorage() {
		name = "quantumstorage";

		bytesMax = 0;
		bytesUsed = 0;	
		typesUsed = 0;

		stored = new EM[64];
	}

	public void insertItemStack(ItemStack itemstack) {
		for(EM em : stored){
			if(em instanceof EMItemStack){
				if(((EMItemStack) em).store(itemstack)){
					return;
				}
			}
		}
		EM em = new EMItemStack(itemstack);
		stored = ArrayUtils.add(stored, em);
	}

	@Override
	public int getSizeInventory() {
		return 64;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		EM em = stored[slot];
		if(em instanceof EMItemStack){
			return ((EMItemStack) em).getItemStack();
		}
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		insertItemStack(itemstack);
	}

	@Override
	public String getInventoryName() {
		return name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return !name.equals("quantumstorage");
	}

	@Override
	public int getInventoryStackLimit() {
		return -1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		EM em = stored[slot];
		return em != null ? (em instanceof EMItemStack ? ((EMItemStack) em).canStore(itemstack) : false) : false;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList list = new NBTTagList();
		for(EM em : stored){
			list.appendTag(em.writeToNbt(new NBTTagCompound()));
		}
		nbt.setTag("stored", list);
		nbt.setInteger("bytesMax", bytesMax);
		nbt.setInteger("bytesUsed", bytesUsed);
		nbt.setInteger("typesUsed", typesUsed);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = (NBTTagList) nbt.getTag("stored");
		for(int i = 0; i < list.tagCount(); i++){
			try {
				stored[i] = EM.createFromNBT(list.getCompoundTagAt(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		bytesMax = nbt.getInteger("bytesMax");
		bytesUsed = nbt.getInteger("bytesUsed");
		typesUsed = nbt.getInteger("typesUsed");
	}*/

	//storage == null || storage instanceof QuantumItemStackStorage ? 1 : 0
}
