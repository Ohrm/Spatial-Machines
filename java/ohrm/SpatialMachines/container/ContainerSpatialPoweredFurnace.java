package ohrm.SpatialMachines.container;

import ohrm.SpatialMachines.slot.SlotTestMachine;
import ohrm.SpatialMachines.tile.TileEntitySpatialPoweredFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerSpatialPoweredFurnace extends Container
{
    private TileEntitySpatialPoweredFurnace tileFurnace;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;
    private static final String __OBFID = "CL_00001748";

    public ContainerSpatialPoweredFurnace(InventoryPlayer invPlayer, TileEntitySpatialPoweredFurnace tile)
    {
        this.tileFurnace = tile;
        this.addSlotToContainer(new Slot(tile, 0, 56, 17));
        this.addSlotToContainer(new SlotTestMachine(tile, 1, 116, 35));
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

    public void addCraftingToCrafters(ICrafting craft)
    {
        super.addCraftingToCrafters(craft);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        //for (int i = 0; i < this.crafters.size(); ++i)
        //{
            //ICrafting icrafting = (ICrafting)this.crafters.get(i);

            //if (this.lastCookTime != this.tileFurnace.furnaceCookTime)
            //{
                //icrafting.sendProgressBarUpdate(this, 0, this.tileFurnace.furnaceCookTime);
            //}

            //if (this.lastBurnTime != this.tileFurnace.furnaceBurnTime)
            //{
                //icrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
            //}

            //if (this.lastItemBurnTime != this.tileFurnace.currentItemBurnTime)
            //{
                //icrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.currentItemBurnTime);
            //}
        //}

        //this.lastCookTime = this.tileFurnace.furnaceCookTime;
        //this.lastBurnTime = this.tileFurnace.furnaceBurnTime;
        //this.lastItemBurnTime = this.tileFurnace.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int p_75137_1_, int p_75137_2_)
    {
        //if (p_75137_1_ == 0)
        //{
            //this.tileFurnace.furnaceCookTime = p_75137_2_;
        //}

        //if (p_75137_1_ == 1)
        //{
            //this.tileFurnace.furnaceBurnTime = p_75137_2_;
        //}

        //if (p_75137_1_ == 2)
        //{
            //this.tileFurnace.currentItemBurnTime = p_75137_2_;
        //}
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileFurnace.isUseableByPlayer(player);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNum)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNum);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotNum == 1)
            {
                if (!this.mergeItemStack(itemstack1, 3, 38, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotNum != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (slotNum >= 2 && slotNum < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 38, false))
                    {
                        return null;
                    }
                }
                else if (slotNum >= 30 && slotNum < 38 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 38, false))
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