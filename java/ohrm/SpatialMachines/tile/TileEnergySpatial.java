package ohrm.SpatialMachines.tile;

import sun.security.util.Debug;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public abstract class TileEnergySpatial extends TileEnergyBasic {
    
    public float tmpMultiplier = 1;
    
    public float multiplier = 1;
    
	Block block;
	
	public TileEnergySpatial() {
		
		
		
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
	@Override
	public int getCycleEnergyCost(){
		
		return getCycleLength() * 4;
		
	}
		
	/**
	 * @return The length of a cycle, in ticks.
	 */
	@Override
	public int getCycleLength(){
		
		return (int)(200 / speedMultiplier(this.xCoord, this.yCoord, this.zCoord, this.worldObj));
		
	}
		
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
	
	public float speedMultiplier(int xco, int yco, int zco, World parWorld){
    	
    	tmpMultiplier = 1.0f;
    	
    	for(int x = -2; x <= 2; x++){
    		for(int y = -2; y <= 2; y++){
    			for(int z = -2; z <= 2; z++){    				
    				block = parWorld.getBlock(xco + x, yco + y, zco + z);
    				
    				if(block != null){
    					
    					if(block == Blocks.diamond_block){
    		    		
    						tmpMultiplier += 0.2f;
    		    	
    					}
    				
    				}
    				
    			}
    			
    		}
    		
    	}
    	
    	if(tmpMultiplier != multiplier)
    		multiplier = tmpMultiplier;
    	
    	return multiplier;
    	
    }

}