package ohrm.SpatialMachines.container;

import ohrm.SpatialMachines.slot.SlotSpeedBoost;
import ohrm.SpatialMachines.slot.SlotTestMachine;
import ohrm.SpatialMachines.tile.TileEntitySpatialPoweredFurnace;
import ohrm.SpatialMachines.tile.TileEntitySpeedBoost;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerSpeedBoost extends Container {

	 private TileEntitySpeedBoost speedboost;
	 
	 public ContainerSpeedBoost(InventoryPlayer invPlayer, TileEntitySpeedBoost tile)
	    {
	        this.speedboost = tile;
	        this.addSlotToContainer(new SlotSpeedBoost(tile, 0, 83, 43));
	        int i;

	        for (i = 0; i < 3; ++i)
	        {
	            for (int j = 0; j < 9; ++j)
	            {
	                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
	            }
	        }

	        for (i = 0; i < 9; ++i)
	        {
	            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
	        }
	    }
	
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.speedboost.isUseableByPlayer(player);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNum) {
    	
    	ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNum);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotNum == 0)
            {
                if (!this.mergeItemStack(itemstack1, 3, 37, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotNum != 0)
            {
                if (slotNum >= 1 && slotNum < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 37, false))
                    {
                        return null;
                    }
                }
                else if (slotNum >= 30 && slotNum < 37 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 37, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    	
    }
    
}
