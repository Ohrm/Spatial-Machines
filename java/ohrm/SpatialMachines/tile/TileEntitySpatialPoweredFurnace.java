package ohrm.SpatialMachines.tile;

import cofh.api.energy.EnergyStorage;
import ohrm.SpatialMachines.Util.Utils;
import ohrm.SpatialMachines.block.SpatialPoweredFurnace;
import ohrm.SpatialMachines.recipes.SmasherRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySpatialPoweredFurnace extends TileEnergySpatial{
   
	private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {0};
    private static final int[] slotsSides = new int[] {1};
    
    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    public int active = 0;
    
    public static final int NUM_SLOTS = 2;
    
    private String name;
	
    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return NUM_SLOTS;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
	public String getInventoryName() {
		return "Spatial Powered Furnace";
	}

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack items)
    {
        return slot == 1 ? false : (slot == 0);
    }

    @Override
    public int getMaxEnergyStored(){
    	
    	return 4000;
    	
    }
    
    @Override
    public boolean canBeginCycle(){
    	
    	if(_inventories[0] != null && _inventories[0].stackSize >= 1){
    		
    		if(_inventories[1] != null && _inventories[1].stackSize >= getInventoryStackLimit()){
    			
    		return false;	
    			
    		}
    		if(FurnaceRecipes.smelting().getSmeltingResult(this._inventories[0]) != null)
    			return true;
    		
    	}
    	
    	return false;
    }
    
    @Override
    public void onPoweredCycleBegin(){
    	
    	active = 1;
    	
    }
    
    @Override
    public void onPoweredCycleEnd(){
    	
    	ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this._inventories[0]);
    	
    	if(destroyInputs(this.xCoord, this.yCoord, this.zCoord, this.worldObj)){
    		
    		consumeInputs();
    		
    	}else if(Utils.isChestAvailable(this.xCoord, this.yCoord, this.zCoord, this.worldObj, itemstack)){
    		
    		consumeInputs();

    		
    	}else if(_inventories[1] != null){
    		
    		if(consumeInputs()){
    			
    			_inventories[1].stackSize += itemstack.stackSize;
    			
    		}
    		
    	}else{
    		
    		if(consumeInputs()){
    		
    			_inventories[1] = itemstack.copy();
    			_inventories[1].stackSize = itemstack.stackSize;
    		
    		}
    		
    	}
    	active = 0;
    	markDirty();
    	
    	
    }
    
    /**
     * Consume specified amount of items from slot
     * @return True when items have been removed
     */
    public boolean consumeInputs(){
    	
    	_inventories[0] = Utils.consumeItem(_inventories[0], 1);
    	return true;
    	
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int p_145953_1_)
    {
        return this.cycledTicks * p_145953_1_ / getCycleLength();
    }

    @SideOnly(Side.CLIENT)
    public int getIsActive(int scale)
    {
    	if(this.active == 1)
    		return scale;
    	else
    		return 0;
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int slot)
    {
        return slot == 1 ? slotsSides : (slot == 0 ? slotsTop : slotsBottom);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int slot, ItemStack item, int side)
    {
        return this.isItemValidForSlot(slot, item);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int slot, ItemStack item, int side)
    {
        return side != 0 || slot != 1 || item.getItem() == Items.bucket;
    }
    
}