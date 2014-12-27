package ohrm.SpatialMachines.slot;

import ohrm.SpatialMachines.Items.ItemSpeedBoostBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSpeedBoost extends Slot {

	public SlotSpeedBoost(IInventory par1iInventory, int id, int x, int y) {
		super(par1iInventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		if(stack.getItem() instanceof ItemSpeedBoostBase){
			
			return true;
			
		}
		return false;
	}

}
