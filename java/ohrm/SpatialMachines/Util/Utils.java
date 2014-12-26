package ohrm.SpatialMachines.Util;

import net.minecraft.item.ItemStack;

public class Utils {

	public static ItemStack consumeItem(ItemStack stack, int amount)
	{
		if(stack == null) { return null; }

		if(stack.stackSize <= amount)
		{
			if(stack.getItem().hasContainerItem(stack))
			{
				return stack.getItem().getContainerItem(stack);
			}
			else
			{
				return null;
			}
		}
		else
		{
			stack.splitStack(amount);
			return stack;
		}	
	}
	
}
