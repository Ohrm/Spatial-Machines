package ohrm.SpatialMachines.Items;

import ohrm.SpatialMachines.Reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AddedItems {
	
	public static ItemSpeedBoostBase itemSpeedBoost;
	public static ItemEnergyBoostBase itemEnergyBoost;
	
	public static void addItems(){
		
		itemSpeedBoost = new ItemSpeedBoostBase("itemSpeedBoost");
		itemEnergyBoost = new ItemEnergyBoostBase("itemEnergyBoost");
		
		registerItems();
		
		itemRecipes();
		
	}
	
	public static void registerItems(){
		
		GameRegistry.registerItem(itemSpeedBoost, "itemSpeedBoost");
		GameRegistry.registerItem(itemEnergyBoost, "itemEnergyBoost");
		
	}
	
	public static void itemRecipes(){
        
	   
		
	}
	
}
