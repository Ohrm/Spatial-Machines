package ohrm.SpatialMachines.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRemove extends Slot {

	public SlotRemove(IInventory par1iInventory, int id, int x, int y) {
		super(par1iInventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		
		return false;
		
	}
	
}
