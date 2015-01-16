package ohrm.SpatialMachines.tile;

import ohrm.SpatialMachines.Items.ItemEnergyBoostBase;
import ohrm.SpatialMachines.Items.ItemSpeedBoostBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoostBlock extends TileInventory{

    public static final int NUM_SLOTS = 1;
	
	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {

		return null;
		
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {

		return false;
		
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {

		return false;
		
	}

	@Override
	public int getSizeInventory() {

		return NUM_SLOTS;
		
	}

	@Override
	public String getInventoryName() {
		return "Speed Booster";
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		
		if(itemstack.getItem() instanceof ItemSpeedBoostBase || itemstack.getItem() instanceof ItemEnergyBoostBase){
			
			return true;
			
		}
		return false;
		
	}



}
