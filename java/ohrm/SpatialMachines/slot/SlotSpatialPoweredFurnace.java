package ohrm.SpatialMachines.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotSpatialPoweredFurnace extends Slot {

	public SlotSpatialPoweredFurnace(IInventory par1iInventory, int id, int x, int y) {
		super(par1iInventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		if(stack.getItem() instanceof Item){
			
			return true;
			
		}
		return false;
	}
	
}
