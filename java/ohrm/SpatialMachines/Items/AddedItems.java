package ohrm.SpatialMachines.Items;

import ohrm.SpatialMachines.Reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class AddedItems {
	
	public static ItemSpeedBoostBase itemSpeedBoost;
	public static ItemEnergyBoostBase itemEnergyBoost;
	public static Item copperDust;
	
	public static void addItems(){
		
		itemSpeedBoost = new ItemSpeedBoostBase("itemSpeedBoost");
		itemEnergyBoost = new ItemEnergyBoostBase("itemEnergyBoost");
		
		copperDust = new ItemBase("copperDust");
		
		registerItems();
		
		itemRecipes();
		
	}
	
	public static void registerItems(){
		
		GameRegistry.registerItem(itemSpeedBoost, "itemSpeedBoost");
		GameRegistry.registerItem(itemEnergyBoost, "itemEnergyBoost");
		GameRegistry.registerItem(copperDust, "copperDust");
		
		OreDictionary.registerOre("dustCopper", copperDust);
		
	}
	
	public static void itemRecipes(){
        
	   
		
	}
	
}
