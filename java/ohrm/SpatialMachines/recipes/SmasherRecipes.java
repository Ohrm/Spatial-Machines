package ohrm.SpatialMachines.recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import ohrm.SpatialMachines.Items.AddedItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class SmasherRecipes
{
    private static final SmasherRecipes smashingBase = new SmasherRecipes();
    /** The list of smelting results. */
    private Map smashingList = new HashMap();
    private Map experienceList = new HashMap();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static SmasherRecipes smelting() 
    {
        return smashingBase;
    }

    private SmasherRecipes()
    {
    	for(ItemStack ore : OreDictionary.getOres("oreCopper")){
    		this.addBlockRecipe(Block.getBlockFromItem(ore.getItem()), new ItemStack(AddedItems.copperDust, 2), 0.7F);
    	}
    }

    public void addBlockRecipe(Block input, ItemStack itemStack, float xp)
    {
        this.addItemRecipe(Item.getItemFromBlock(input), itemStack, xp);
    }

    public void addItemRecipe(Item input, ItemStack result, float xp)
    {
        this.func_151394_a(new ItemStack(input, 1, 32767), result, xp);
    }

    public void func_151394_a(ItemStack input, ItemStack result, float xp)
    {
        this.smashingList.put(input, result);
        this.experienceList.put(result, Float.valueOf(xp));
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmashingResult(ItemStack stack)
    {
        Iterator iterator = this.smashingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(stack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    public Map getSmashingList()
    {
        return this.smashingList;
    }

    public float func_151398_b(ItemStack p_151398_1_)
    {
        float ret = p_151398_1_.getItem().getSmeltingExperience(p_151398_1_);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(p_151398_1_, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}