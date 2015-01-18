package ohrm.SpatialMachines.Util;

import ohrm.SpatialMachines.tile.TileEntitySpatialPoweredFurnace;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class Utils {

	static Block block;
	
	/**
	 * Removes the given amount from the ItemStack 
	 * @param stack: ItemStack to be removed from
	 * @param amount: amount to be removed
	 * @return new ItemStack woth items removed
	 */
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
	
	/**
	 * Checks to see if a nearby chest can accept the item just made from the machine
	 * @param xCo: x coordinate of the block
	 * @param yCo: y coordinate of the block
	 * @param zCo: z coordinate of the block
	 * @param world: the world that the block is in
	 * @param stack: ItemStack that should be put into chest if possible
	 * @return true if ItemStack was successfully added and false if it was not successfully added
	 */
	public static boolean isChestAvailable(int xCo, int yCo, int zCo, World world, ItemStack stack){
		
		for(int x = -2; x <= 2; x++){
			for(int y = -2; y <= 2; y++){
				for(int z = -2; z <= 2; z++){
					
					block = world.getBlock(xCo + x, yCo + y, zCo + z);
    				
					if(block != null){
						
						TileEntity te = world.getTileEntity(xCo + x, yCo + y, zCo + z);
						
						if(te instanceof TileEntityChest){
							
							for(int slot = 0; slot < ((TileEntityChest)te).getSizeInventory(); slot++){
								
								if(((TileEntityChest)te).getStackInSlot(slot) == null){
									
									((TileEntityChest)te).setInventorySlotContents(slot, stack.copy());
									((TileEntityChest)te).getStackInSlot(slot).stackSize = stack.stackSize;
									return true;
									
								}else if(((TileEntityChest)te).getStackInSlot(slot).getItem() == stack.getItem() && ((TileEntityChest)te).getStackInSlot(slot).stackSize < ((TileEntityChest)te).getStackInSlot(slot).getItem().getItemStackLimit(((TileEntityChest)te).getStackInSlot(slot))){
								
									((TileEntityChest)te).getStackInSlot(slot).stackSize += stack.stackSize;
									return true;
									
								}
								
							}
							
						}
						
					}
					
				}
			}
		}
		return false;
	}
	
	public static boolean isFurnaceAvailable(int xCo, int yCo, int zCo, World world, ItemStack stack){
		
		for(int x = -2; x <= 2; x++){
			for(int y = -2; y <= 2; y++){
				for(int z = -2; z <= 2; z++){
					
					block = world.getBlock(xCo + x, yCo + y, zCo + z);
    				
					if(block != null){
						
						TileEntity te = world.getTileEntity(xCo + x, yCo + y, zCo + z);
						
						if(te instanceof TileEntitySpatialPoweredFurnace){
								
								if(((TileEntitySpatialPoweredFurnace)te).getStackInSlot(0) == null){
									
									((TileEntitySpatialPoweredFurnace)te).setInventorySlotContents(0, stack.copy());
									((TileEntitySpatialPoweredFurnace)te).getStackInSlot(0).stackSize = stack.stackSize;
									return true;
									
								}else if(((TileEntitySpatialPoweredFurnace)te).getStackInSlot(0).getItem() == stack.getItem() && ((TileEntitySpatialPoweredFurnace)te).getStackInSlot(0).stackSize < ((TileEntitySpatialPoweredFurnace)te).getStackInSlot(0).getItem().getItemStackLimit(((TileEntitySpatialPoweredFurnace)te).getStackInSlot(0))){
								
									((TileEntitySpatialPoweredFurnace)te).getStackInSlot(0).stackSize += stack.stackSize;
									return true;
									
								}
							
						}else if(te instanceof TileEntityFurnace){
								
								if(((TileEntityFurnace)te).getStackInSlot(0) == null){
									
									((TileEntityFurnace)te).setInventorySlotContents(0, stack.copy());
									((TileEntityFurnace)te).getStackInSlot(0).stackSize = stack.stackSize;
									return true;
									
								}else if(((TileEntityFurnace)te).getStackInSlot(0).getItem() == stack.getItem() && ((TileEntityFurnace)te).getStackInSlot(0).stackSize < ((TileEntityFurnace)te).getStackInSlot(0).getItem().getItemStackLimit(((TileEntityFurnace)te).getStackInSlot(0))){
								
									((TileEntityFurnace)te).getStackInSlot(0).stackSize += stack.stackSize;
									return true;
									
								}
								
							
						}
						
					}
					
				}
			}
		}
		return false;
		
	}
	
}
