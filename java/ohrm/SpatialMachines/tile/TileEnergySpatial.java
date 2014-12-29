package ohrm.SpatialMachines.tile;

import ohrm.SpatialMachines.Items.ItemEnergyBoostBase;
import ohrm.SpatialMachines.Items.ItemSpeedBoostBase;
import ohrm.SpatialMachines.block.AddedBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public abstract class TileEnergySpatial extends TileEnergyBasic {
    
    public float tmpMultiplier = 1;
    
    public float multiplier = 1;
    
    public float tmpEnergyMultiplier = 1;
    
    public float tmpEnergySpeedMultiplier = 0;
    
    public float energyMultiplier = 1;
    
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
		
		return (int)(800 / energyMultiplier(this.xCoord, this.yCoord, this.zCoord, this.worldObj));
		
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
    	tmpEnergySpeedMultiplier = 0.0f;
    	
    	for(int x = -2; x <= 2; x++){
    		for(int y = -2; y <= 2; y++){
    			for(int z = -2; z <= 2; z++){    				
    				block = parWorld.getBlock(xco + x, yco + y, zco + z);
    				
    				if(block != null){
    					
    					if(block == AddedBlocks.BoostBlock){
    		    		
    						TileEntity te = parWorld.getTileEntity(xco + x, yco + y, zco + z);
    						
    						if(te instanceof TileEntityBoostBlock){
    							
    							ItemStack currentItem = ((TileEntityBoostBlock) te).getStackInSlot(0);
    							
    							if(currentItem != null && currentItem.getItem() instanceof ItemSpeedBoostBase){
    								if(currentItem.getItemDamage() == 0){
    								
    									tmpMultiplier += 0.1f;
    									tmpEnergySpeedMultiplier += 0.05f;
    								
    								}else if(currentItem.getItemDamage() == 1){
    								
    									tmpMultiplier += 0.3f;
    									tmpEnergySpeedMultiplier += 0.15f;
    								
    								}else if(currentItem.getItemDamage() == 2){
    								
    									tmpMultiplier += 0.5f;
    									tmpEnergySpeedMultiplier += 0.25f;
    								
    								}
    							}
    							
    						}
    		    	
    					}
    				
    				}
    				
    			}
    			
    		}
    		
    	}
    	
    	if(tmpMultiplier != multiplier)
    		multiplier = tmpMultiplier;
    	
    	return multiplier;
    	
    }
	
	public float energyMultiplier(int xco, int yco, int zco, World parWorld){
    	
    	tmpEnergyMultiplier = 1.0f;
    	
    	for(int x = -2; x <= 2; x++){
    		for(int y = -2; y <= 2; y++){
    			for(int z = -2; z <= 2; z++){    				
    				block = parWorld.getBlock(xco + x, yco + y, zco + z);
    				
    				if(block != null){
    					
    					if(block == AddedBlocks.BoostBlock){
    		    		
    						TileEntity te = parWorld.getTileEntity(xco + x, yco + y, zco + z);
    						
    						if(te instanceof TileEntityBoostBlock){
    							
    							ItemStack currentItem = ((TileEntityBoostBlock) te).getStackInSlot(0);
    							
    							if(currentItem != null && currentItem.getItem() instanceof ItemEnergyBoostBase){
    								if(currentItem.getItemDamage() == 0){
    								
    									tmpEnergyMultiplier += 0.1f;    									
    								
    								}else if(currentItem.getItemDamage() == 1){
    								
    									tmpEnergyMultiplier += 0.3f;
    								
    								}else if(currentItem.getItemDamage() == 2){
    								
    									tmpEnergyMultiplier += 0.5f;
    								
    								}
    							}
    							
    						}
    		    	
    					}
    				
    				}
    				
    			}
    			
    		}
    		
    	}
    	
    	tmpEnergyMultiplier += tmpEnergySpeedMultiplier;
    	
    	if(tmpEnergyMultiplier != energyMultiplier)
    		energyMultiplier = tmpEnergyMultiplier;
    	
    	return energyMultiplier;
    	
    }

}