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
	public static Item copperIngot;
	
	public static void addItems(){
		
		itemSpeedBoost = new ItemSpeedBoostBase("itemSpeedBoost");
		itemEnergyBoost = new ItemEnergyBoostBase("itemEnergyBoost");
		
		copperDust = new ItemBase("copperDust");
		copperIngot = new ItemBase("copperIngot");
		
		registerItems();
		
		itemRecipes();
		
	}
	
	public static void registerItems(){
		
		GameRegistry.registerItem(itemSpeedBoost, "itemSpeedBoost");
		GameRegistry.registerItem(itemEnergyBoost, "itemEnergyBoost");
		GameRegistry.registerItem(copperDust, "copperDust");
		GameRegistry.registerItem(copperIngot, "copperIngot");
		
		OreDictionary.registerOre("dustCopper", copperDust);
		OreDictionary.registerOre("ingotCopper", copperIngot);
		
	}
	
	public static void itemRecipes(){
       
		
	    GameRegistry.addSmelting(copperDust, new ItemStack(copperIngot), 0.2f);
		
	}
	
}
