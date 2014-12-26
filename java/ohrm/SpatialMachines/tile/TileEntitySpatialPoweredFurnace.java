package ohrm.SpatialMachines.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import ohrm.SpatialMachines.Util.Utils;
import ohrm.SpatialMachines.block.SpatialPoweredFurnace;
import sun.security.util.Debug;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
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
		return "Cyanite Reprocessor";
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
    	
    	
    	
    }
    
    @Override
    public void onPoweredCycleEnd(){
    	
    	if(_inventories[1] != null){
    		
    		if(consumeInputs()){
    			
    			_inventories[1].stackSize += 1;
    			
    		}
    		
    	}else{
    		
    		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this._inventories[0]);
    		if(consumeInputs()){
    		
    			_inventories[1] = itemstack.copy();
    			_inventories[1].stackSize = 1;
    		
    		}
    		
    	}
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

    /* A bit broken right now */
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int p_145953_1_)
    {
        return this.cycledTicks * getCycleLength() / p_145953_1_;
    }

    /* A bit broken right now */
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int time)
    {
    	if(this.isActive())
    		return this.cycledTicks * time / getCycleLength();
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