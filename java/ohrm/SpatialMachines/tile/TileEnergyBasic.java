package ohrm.SpatialMachines.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;


public abstract class TileEnergyBasic extends TileInventory implements IEnergyHandler {

	/**
	 * The number of ticks since the cycle started
	 */
	public int cycledTicks;
	
	protected EnergyStorage storage;
	public int cycleLength;
	
	public TileEnergyBasic() {
		
		storage = new EnergyStorage(getMaxEnergyStored());
		
		//So the machine doesn't start straight away set it to -1
		cycledTicks -= 1;
		
	}
	
	// Internal energy methods
	/**
	 * The maximum energy that can be stored
	 * @return The amount of energy that can be stored
	 */
	protected abstract int getMaxEnergyStored();
	
	/**
	 * Returns the energy cost to run a cycle. Consumed at the beginning of a cycle.
	 * @return Amount of energy needed to start a cycle.
	 */
	public abstract int getCycleEnergyCost();
		
	/**
	 * @return The length of a cycle, in ticks.
	 */
	public abstract int getCycleLength();
		
	/**
	* Check material/non-energy requirements for starting a cycle.
	 * Do not consume resources here
	 * @return True if a cycle can start/continue, false otherwise.
	 */
	public abstract boolean canBeginCycle();
		
	/**
	 * Perform any necessary operations at the start of a cycle.
	 * Do not consume resources here.
	 * Called every tick to make sure all requirements are still there
	 */
	public abstract void onPoweredCycleBegin();
		
	/**
	 * Perform any necessary operations at the end of a cycle.
	 * Consume and produce resources here.
	 */
	public abstract void onPoweredCycleEnd();

	/**
	 * Gets how many ticks into the cycle we currently are
	 * @return cycleTicks
	 */
	public int getCurrentCycledTicks(){
		
		return cycledTicks;
		
	}
	
	/**
	 * Gets weather the machine is active
	 * @return true if cycled ticks is 0 or greater
	 */
	public boolean isActive(){
		
		return cycledTicks >= 0;
		
	}
	
	/**
	 * Get how close to completion the cycle is
	 * @return float value up to 1 showing how close to completion it is
	 */
	public float getCycleCompletion(){
		
		if(cycledTicks < 0)
			return 0f;
		else
			return (float)cycledTicks/(float)getCycleLength();
		
	}
		
	/* NBT reading
	 * Read the energy levels in the block 
	 * and the current cycled ticks */
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		
		if(nbt.hasKey("storage"))
			this.storage.readFromNBT(nbt.getCompoundTag("storage"));
		
		if(nbt.hasKey("cycledTicks"))
			cycledTicks = nbt.getInteger("cycledTicks");
	
	}

	/* NBT writing
	 * write the energy levels in the block 
	 * and the current cycled ticks */
	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		NBTTagCompound energyNBTTag =  new NBTTagCompound();
		this.storage.writeToNBT(energyNBTTag);
		nbt.setTag("storage", energyNBTTag);
		nbt.setInteger("cycledTicks", cycledTicks);
		
	}
	
	@Override
	public void updateEntity() {
		
		super.updateEntity();
		
		if(!worldObj.isRemote){
			
			if(cycledTicks >= 0){
				
				cycledTicks++;
				
				if(!canBeginCycle()){
					
					cycledTicks = -1;
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					
				}else if(cycledTicks >= getCycleLength()){
					
					onPoweredCycleEnd();
					cycledTicks = -1;
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					
				}
				
			}
			
			if(cycledTicks < 0 && getCycleEnergyCost() <= storage.getEnergyStored() && canBeginCycle()){
				
				this.storage.extractEnergy(getCycleEnergyCost(), false);
				cycledTicks = 0;
				onPoweredCycleBegin();
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				
			}
			
		}
		
	}
	
	

	/* IEnergyConnection */
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {

		return true;
	}

	/* IEnergyReceiver */
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {

		return storage.receiveEnergy(maxReceive, simulate);
	}

	/* IEnergyProvider */
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {

		return storage.extractEnergy(maxExtract, simulate);
	}

	/* IEnergyReceiver and IEnergyProvider */
	@Override
	public int getEnergyStored(ForgeDirection from) {

		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {

		return storage.getMaxEnergyStored();
	}

}