package ohrm.SpatialMachines.Items;

import ohrm.SpatialMachines.Reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class AddedItems {
	
	public static ItemSpeedBoostBase itemSpeedBoost;
	public static ItemEnergyBoostBase itemEnergyBoost;
	
	public static Item spatialDetector;
	
	public static Item copperDust;
	public static Item copperIngot;
	
	public static Item tinDust;
	public static Item tinIngot;
	
	public static Item leadDust;
	public static Item leadIngot;
	
	public static Item silverDust;
	public static Item silverIngot;
	
	public static void addItems(){
		
		itemSpeedBoost = new ItemSpeedBoostBase("itemSpeedBoost");
		itemEnergyBoost = new ItemEnergyBoostBase("itemEnergyBoost");
		
		spatialDetector = new ItemBase("spatialDetector");
		
		copperDust = new ItemBase("copperDust");
		copperIngot = new ItemBase("copperIngot");
		
		tinDust = new ItemBase("tinDust");
		tinIngot = new ItemBase("tinIngot");
		
		leadDust = new ItemBase("leadDust");
		leadIngot = new ItemBase("leadIngot");
		
		silverDust = new ItemBase("silverDust");
		silverIngot = new ItemBase("silverIngot");
		
		registerItems();
		
		itemRecipes();
		
	}
	
	public static void registerItems(){
		
		GameRegistry.registerItem(itemSpeedBoost, "itemSpeedBoost");
		GameRegistry.registerItem(itemEnergyBoost, "itemEnergyBoost");
		GameRegistry.registerItem(spatialDetector, "spatialDetector");
		GameRegistry.registerItem(copperDust, "copperDust");
		GameRegistry.registerItem(copperIngot, "copperIngot");
		GameRegistry.registerItem(tinDust, "tinDust");
		GameRegistry.registerItem(tinIngot, "tinIngot");
		GameRegistry.registerItem(leadDust, "leadDust");
		GameRegistry.registerItem(leadIngot, "leadIngot");
		GameRegistry.registerItem(silverDust, "silverDust");
		GameRegistry.registerItem(silverIngot, "silverIngot");
		
		OreDictionary.registerOre("dustCopper", copperDust);
		OreDictionary.registerOre("ingotCopper", copperIngot);
		
		OreDictionary.registerOre("dustTin", tinDust);
		OreDictionary.registerOre("ingotTin", tinIngot);
		
		OreDictionary.registerOre("dustLead", leadDust);
		OreDictionary.registerOre("ingotLead", leadIngot);
		
		OreDictionary.registerOre("dustSilver", silverDust);
		OreDictionary.registerOre("ingotSilver", silverIngot);
		
	}
	
	public static void itemRecipes(){
       
		//Crafting
		GameRegistry.addRecipe(new ItemStack(spatialDetector, 1), new Object[]{
			
			"DBI",
			"BBB",
			"IBD",
			'D', Items.diamond,
			'B', Blocks.iron_bars,
			'I', Items.iron_ingot
			
			
		});
		
		GameRegistry.addRecipe(new ItemStack(itemSpeedBoost, 1, 0), new Object[]{
			
			"I I",
			"IRI",
			"III",
			'I', Items.iron_ingot,
			'R', Items.redstone			
			
		});
		
		GameRegistry.addRecipe(new ItemStack(itemSpeedBoost, 1, 1), new Object[]{
			
			"D D",
			"IRI",
			"IDI",
			'I', Items.iron_ingot,
			'R', Items.redstone, 
			'D', Items.diamond
			
		});

		GameRegistry.addRecipe(new ItemStack(itemSpeedBoost, 1, 2), new Object[]{
			
			"D D",
			"DRD",
			"DDD",
			'D', Items.diamond,
			'R', Items.redstone			
			
		});
		
		//itemEnergyBoost
		GameRegistry.addRecipe(new ItemStack(itemEnergyBoost, 1, 0), new Object[]{
			
			"I I",
			"IGI",
			"III",
			'I', Items.iron_ingot,
			'G', Items.glowstone_dust			
			
		});
		
		GameRegistry.addRecipe(new ItemStack(itemEnergyBoost, 1, 1), new Object[]{
			
			"D D",
			"IGI",
			"IDI",
			'I', Items.iron_ingot,
			'G', Items.glowstone_dust,
			'D', Items.diamond
			
		});

		GameRegistry.addRecipe(new ItemStack(itemEnergyBoost, 1, 2), new Object[]{
			
			"D D",
			"DGD",
			"DDD",
			'D', Items.diamond,
			'G', Items.glowstone_dust			
			
		});
		
		
		//Smelting
	    GameRegistry.addSmelting(copperDust, new ItemStack(copperIngot), 0.2f);
	    GameRegistry.addSmelting(tinDust, new ItemStack(tinIngot), 0.2f);
	    GameRegistry.addSmelting(leadDust, new ItemStack(leadIngot), 0.2f);
	    GameRegistry.addSmelting(silverDust, new ItemStack(silverIngot), 0.2f);
	    
	}
	
}
