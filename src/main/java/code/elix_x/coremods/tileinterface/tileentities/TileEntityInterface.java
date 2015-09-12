package code.elix_x.coremods.tileinterface.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import code.elix_x.coremods.tileinterface.util.BlockPos;
import code.elix_x.coremods.tileinterface.util.WorldUtils;

public class TileEntityInterface extends TileEntity implements ISidedInventory, IFluidHandler/*, IEnergyHandler, IEnergySource, IEnergySink, IPipeTile, IGridHost/*, IGridBlock*/{

	public static Logger logger = LogManager.getLogger("Tile Interface");

	/*
	 * try{

		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
	 */

	public TileEntityInterface() {

	}

	public ForgeDirection getDirection() {
		return ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
	}

	public BlockPos getDirectedBlock(){
		return WorldUtils.getBlockPosWithOffset(worldObj, xCoord, yCoord, zCoord, getDirection());
	}

	public TileEntity getDirectedTileEntity(){
		BlockPos pos = getDirectedBlock();
		return worldObj.getTileEntity(pos.x, pos.y, pos.z);
	}

	public IInventory getDirectedInventory(){
		return (IInventory) getDirectedTileEntity();
	}

	public ISidedInventory getDirectedSidedInventory(){
		return (ISidedInventory) getDirectedTileEntity();
	}

	public IFluidHandler getDirectedFluidHandler(){
		return (IFluidHandler) getDirectedTileEntity();
	}

	/*public IEnergyHandler getDirectedEnergyHandler(){
		return (IEnergyHandler) getDirectedTileEntity();
	}

	public IEnergySource getDirectedEnergySource(){
		return (IEnergySource) getDirectedTileEntity();
	}
	
	public IEnergySink getDirectedEnergySink(){
		return (IEnergySink) getDirectedTileEntity();
	}
	
	public IPipeTile getDirectedPipeTile(){
		return (IPipeTile) getDirectedTileEntity();
	}
	
	public IGridHost getDirectedGridHost(){
		return (IGridHost) getDirectedTileEntity();
	}*/
	
	/*
	 * IInventory
	 */
	
	@Override
	public int getSizeInventory() {
		try{
			return getDirectedInventory().getSizeInventory();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		}
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		try{
			//			logger.info("trying to get stack in slot: " + slot);
			return getDirectedInventory().getStackInSlot(slot);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		try{
			return getDirectedInventory().decrStackSize(slot, count);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		try{
			return getDirectedInventory().getStackInSlotOnClosing(slot);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		try{
			getDirectedInventory().setInventorySlotContents(slot, itemstack);;
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
	}

	@Override
	public String getInventoryName() {
		try{
			return getDirectedInventory().getInventoryName();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return "tileinterface";
	}

	@Override
	public boolean hasCustomInventoryName() {
		try{
			return getDirectedInventory().hasCustomInventoryName();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		}
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		try{
			return getDirectedInventory().getInventoryStackLimit();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		try{
			return getDirectedInventory().isUseableByPlayer(player);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}

	@Override
	public void openInventory() {
		try{
			getDirectedInventory().openInventory();;
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
	}

	@Override
	public void closeInventory() {
		try{
			getDirectedInventory().closeInventory();;
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		try{
			return getDirectedInventory().isItemValidForSlot(slot, itemstack);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}
	
	/*
	 * ISidedInventory
	 */

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		try{
			if(getDirectedInventory() instanceof ISidedInventory){
				return getDirectedSidedInventory().getAccessibleSlotsFromSide(side);
			} else {
				int[] aint = new int[getSizeInventory()];
				for(int i = 0; i < getSizeInventory(); i++){
					aint[i] = i;
				}
				return aint;
			}
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return new int[]{};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		try{
			if(getDirectedInventory() instanceof ISidedInventory){
				return getDirectedSidedInventory().canInsertItem(slot, itemstack, side);
			} else {
				return true;
			}
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		try{
			if(getDirectedInventory() instanceof ISidedInventory){
				return getDirectedSidedInventory().canExtractItem(slot, itemstack, side);
			} else {
				return true;
			}
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}
	
	/*
	 * IFluidHandler
	 */

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		try{
			return getDirectedFluidHandler().fill(from, resource, doFill);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		try{
			return getDirectedFluidHandler().drain(from, resource, doDrain);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		try{
			return getDirectedFluidHandler().drain(from, maxDrain, doDrain);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		try{
			return getDirectedFluidHandler().canFill(from, fluid);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		try{
			return getDirectedFluidHandler().canDrain(from, fluid);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		try{
			return getDirectedFluidHandler().getTankInfo(from);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	/*
	 * IEnergyHandler
	 */
	
	/*@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		try{
			return getDirectedEnergyHandler().canConnectEnergy(from);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		try{
			return getDirectedEnergyHandler().receiveEnergy(from, maxReceive, simulate);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!");
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		try{
			return getDirectedEnergyHandler().extractEnergy(from, maxExtract, simulate);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		try{
			return getDirectedEnergyHandler().getEnergyStored(from);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		try{
			return getDirectedEnergyHandler().getMaxEnergyStored(from);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}*/
	
	/*
	 * IEnergySource
	 */

	/*@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		try{
			return getDirectedEnergySource().emitsEnergyTo(receiver, direction);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}

	@Override
	public double getOfferedEnergy() {
		try{
			return getDirectedEnergySource().getOfferedEnergy();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public void drawEnergy(double amount) {
		try{
			getDirectedEnergySource().drawEnergy(amount);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
	}

	@Override
	public int getSourceTier() {
		try{
			return getDirectedEnergySource().getSourceTier();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}*/

	/*
	 * IEnenergySink
	 */
	
	/*@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
		try{
			return getDirectedEnergySink().acceptsEnergyFrom(emitter, direction);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}

	@Override
	public double getDemandedEnergy() {
		try{
			return getDirectedEnergySink().getDemandedEnergy();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public int getSinkTier() {
		try{
			return getDirectedEnergySink().getSinkTier();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
		try{
			return getDirectedEnergySink().injectEnergy(directionFrom, amount, voltage);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}*/

	/*
	 * IPipeTile
	 */
	
	/*@Override
	public boolean canInjectItems(ForgeDirection from) {
		try{
			return getDirectedPipeTile().canInjectItems(from);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}

	@Override
	public PipeType getPipeType() {
		try{
			return getDirectedPipeTile().getPipeType();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public World getWorld() {
		try{
			return getDirectedPipeTile().getWorld();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public int x() {
		try{
			return getDirectedPipeTile().x();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public int y() {
		try{
			return getDirectedPipeTile().y();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public int z() {
		try{
			return getDirectedPipeTile().z();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public boolean isPipeConnected(ForgeDirection with) {
		try{
			return getDirectedPipeTile().isPipeConnected(with);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}

	@Override
	public Block getNeighborBlock(ForgeDirection dir) {
		try{
			return getDirectedPipeTile().getNeighborBlock(dir);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public TileEntity getNeighborTile(ForgeDirection dir) {
		try{
			return getDirectedPipeTile().getNeighborTile(dir);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public IPipe getNeighborPipe(ForgeDirection dir) {
		try{
			return getDirectedPipeTile().getNeighborPipe(dir);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public IPipe getPipe() {
		try{
			return getDirectedPipeTile().getPipe();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public int getPipeColor() {
		try{
			return getDirectedPipeTile().getPipeColor();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public PipePluggable getPipePluggable(ForgeDirection direction) {
		try{
			return getDirectedPipeTile().getPipePluggable(direction);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public boolean hasPipePluggable(ForgeDirection direction) {
		try{
			return getDirectedPipeTile().hasPipePluggable(direction);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}

	@Override
	public boolean hasBlockingPluggable(ForgeDirection direction) {
		try{
			return getDirectedPipeTile().hasBlockingPluggable(direction);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return false;
	}

	@Override
	public void scheduleNeighborChange() {
		try{
			getDirectedPipeTile().scheduleNeighborChange();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
	}

	@Override
	public int injectItem(ItemStack stack, boolean doAdd, ForgeDirection from, EnumColor color) {
		try{
			return getDirectedPipeTile().injectItem(stack, doAdd, from, color);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}

	@Override
	public int injectItem(ItemStack stack, boolean doAdd, ForgeDirection from) {
		try{
			return getDirectedPipeTile().injectItem(stack, doAdd, from);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return 0;
	}*/
	
	/*
	 *  IGridHost
	 */

	/*@Override
	public IGridNode getGridNode(ForgeDirection dir) {
		try{
			return getDirectedGridHost().getGridNode(dir);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public AECableType getCableConnectionType(ForgeDirection dir) {
		try{
			return getDirectedGridHost().getCableConnectionType(dir);
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		return null;
	}

	@Override
	public void securityBreak() {
		try{
			getDirectedGridHost().securityBreak();
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
	}*/

	/*
	 * try{
			return getDirectedEnergySource().;
		} catch (ClassCastException e){

		} catch (NullPointerException e){

		} catch (StackOverflowError e){
			logger.warn("Don't face two interfaces one to another!!!", e);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
	 */


	/*@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
		return true;
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return true;
	}

	@Override
	public double getConductionLoss() {
		return 1;
	}

	@Override
	public double getInsulationEnergyAbsorption() {
		return 2000;
	}

	@Override
	public double getInsulationBreakdownEnergy() {
		return 2000;
	}

	@Override
	public double getConductorBreakdownEnergy() {
		return 3000;
	}

	@Override
	public void removeInsulation() {		
		
	}

	@Override
	public void removeConductor() {
		
	}*/

	/*@Override
	public IGridNode getGridNode(ForgeDirection dir) {
		return AEApi.instance().createGridNode(this);
	}

	@Override
	public AECableType getCableConnectionType(ForgeDirection dir) {
		return AECableType.DENSE;
	}

	@Override
	public void securityBreak() {
		worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
	}

	@Override
	public double getIdlePowerUsage() {
		return 0;
	}

	@Override
	public EnumSet<GridFlags> getFlags() {
		return EnumSet.of(GridFlags.DENSE_CAPACITY);
	}

	@Override
	public boolean isWorldAccessible() {
		return true;
	}

	@Override
	public DimensionalCoord getLocation() {
		return new DimensionalCoord(this);
	}

	@Override
	public AEColor getGridColor() {
		return AEColor.Transparent;
	}

	@Override
	public void onGridNotification(GridNotification notification) {

	}

	@Override
	public void setNetworkStatus(IGrid grid, int channelsInUse) {

	}

	@Override
	public EnumSet<ForgeDirection> getConnectableSides() {
		return EnumSet.allOf(ForgeDirection.class);
	}

	@Override
	public IGridHost getMachine() {
		return this;
	}

	@Override
	public void gridChanged() {

	}

	@Override
	public ItemStack getMachineRepresentation() {
		return new ItemStack(TileInterfaceBase.interfaceBlock);
	}*/

}
