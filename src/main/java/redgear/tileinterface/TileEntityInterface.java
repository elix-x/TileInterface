package redgear.tileinterface;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;

public class TileEntityInterface extends TileEntity implements ISidedInventory, IFluidHandler, IPowerReceptor {

	protected ForgeDirection getDirection() {
		return ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
	}

	protected TileEntity getTile() {
		ForgeDirection temp = getDirection();

		TileEntity tile = worldObj.getBlockTileEntity(xCoord + temp.offsetX, yCoord + temp.offsetY, zCoord
				+ temp.offsetZ);

		if (tile instanceof TileEntityInterface) {
			TileEntityInterface other = (TileEntityInterface) tile;

			if (other.getDirection().getOpposite() == temp)
				return null;

			tile = other.getTile();
		}

		return tile;
	}

	private IInventory getInv() {
		TileEntity tile = getTile();

		if (tile instanceof IInventory)
			if (tile instanceof TileEntityChest) {
				ForgeDirection temp = getDirection();
				int l = worldObj.getBlockId(xCoord + temp.offsetX, yCoord + temp.offsetY, zCoord + temp.offsetZ);
				Block block = Block.blocksList[l];

				if (block instanceof BlockChest)
					return ((BlockChest) block).getInventory(worldObj, xCoord + temp.offsetX, yCoord + temp.offsetY,
							zCoord + temp.offsetZ);
			} else
				return (IInventory) tile;
		return null;
	}

	@Override
	public int getSizeInventory() {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			return tile.getSizeInventory();
		else
			return 0;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			return tile.getStackInSlot(i);
		else
			return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			return tile.decrStackSize(i, j);
		else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			return tile.getStackInSlotOnClosing(i);
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			tile.setInventorySlotContents(i, itemstack);
	}

	@Override
	public String getInvName() {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			return tile.getInvName();
		else
			return "";
	}

	@Override
	public boolean isInvNameLocalized() {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			return tile.isInvNameLocalized();
		else
			return true;
	}

	@Override
	public int getInventoryStackLimit() {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			return tile.getInventoryStackLimit();
		else
			return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			return tile.isUseableByPlayer(entityplayer);
		else
			return false;
	}

	@Override
	public void openChest() {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			tile.openChest();
	}

	@Override
	public void closeChest() {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			tile.closeChest();
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		IInventory tile = getInv();

		if (tile instanceof IInventory)
			return tile.isItemValidForSlot(i, itemstack);
		else
			return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		IInventory tile = getInv();

		if (tile instanceof ISidedInventory)
			return ((ISidedInventory) tile).getAccessibleSlotsFromSide(var1);
		else
			return new int[] {};
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		IInventory tile = getInv();

		if (tile instanceof ISidedInventory)
			return ((ISidedInventory) tile).canInsertItem(i, itemstack, j);
		else
			return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		IInventory tile = getInv();

		if (tile instanceof ISidedInventory)
			return ((ISidedInventory) tile).canExtractItem(i, itemstack, j);
		else
			return false;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		TileEntity tile = getTile();

		if (tile instanceof IFluidHandler)
			return ((IFluidHandler) tile).fill(from, resource, doFill);
		else
			return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		TileEntity tile = getTile();

		if (tile instanceof IFluidHandler)
			return ((IFluidHandler) tile).drain(from, resource, doDrain);
		else
			return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		TileEntity tile = getTile();

		if (tile instanceof IFluidHandler)
			return ((IFluidHandler) tile).drain(from, maxDrain, doDrain);
		else
			return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		TileEntity tile = getTile();

		if (tile instanceof IFluidHandler)
			return ((IFluidHandler) tile).canFill(from, fluid);
		else
			return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		TileEntity tile = getTile();

		if (tile instanceof IFluidHandler)
			return ((IFluidHandler) tile).canDrain(from, fluid);
		else
			return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		TileEntity tile = getTile();

		if (tile instanceof IFluidHandler)
			return ((IFluidHandler) tile).getTankInfo(from);
		else
			return null;
	}

	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		TileEntity tile = getTile();

		if (tile instanceof IPowerReceptor)
			return ((IPowerReceptor) tile).getPowerReceiver(side);
		else
			return null;
	}

	@Override
	public void doWork(PowerHandler workProvider) {
		TileEntity tile = getTile();

		if (tile instanceof IPowerReceptor)
			((IPowerReceptor) tile).doWork(workProvider);

	}

	@Override
	public World getWorld() {
		TileEntity tile = getTile();

		if (tile instanceof IPowerReceptor)
			return ((IPowerReceptor) tile).getWorld();
		else
			return null;
	}

}
