package ohrm.SpatialMachines.block;

import ohrm.SpatialMachines.Items.AddedItems;
import ohrm.SpatialMachines.main.SpatialMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class AddedBlocks {	
	
	//Powered Machines
    public static Block SpatialPoweredFurnace;
    
    public static void preinit() {
    	
    	//Powered Machines
    	SpatialPoweredFurnace =  new SpatialPoweredFurnace(false).setCreativeTab(SpatialMain.spatialTab);
    
    	registerBlocks();
    	
    	blockRecipes();
    	
    }
    
    public static void registerBlocks(){
    	
    	//Game Reg    
    	//Powered Machines
    	GameRegistry.registerBlock(SpatialPoweredFurnace, "spatialPoweredFurnace");
    	
    }
    public static void blockRecipes() {
       
    	GameRegistry.addRecipe(new ItemStack(SpatialPoweredFurnace, 1), new Object[]{
    		
    		"CCC",
    		"CIC",
    		"CCC",
    		'C', Blocks.cobblestone,
    		'I', Blocks.iron_block
    		
    	});
    		
    }
    
}    
