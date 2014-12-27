package ohrm.SpatialMachines.Items;

import ohrm.SpatialMachines.Reference.Reference;
import ohrm.SpatialMachines.block.AddedBlocks;
import ohrm.SpatialMachines.main.SpatialMain;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class AddedItems {
	
	public static ItemSpeedBoostBase itemSpeedBoost;
;
	
	public static void addItems(){
		
		itemSpeedBoost = new ItemSpeedBoostBase("itemSpeedBoost");
		
		registerItems();
		
		itemRecipes();
		
	}
	
	public static void registerItems(){
		
		GameRegistry.registerItem(itemSpeedBoost, "itemSpeedBoost");

	}
	
	public static void itemRecipes(){
        
	   
		
	}
	
}
