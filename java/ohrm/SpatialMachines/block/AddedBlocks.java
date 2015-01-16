package ohrm.SpatialMachines.block;

import ohrm.SpatialMachines.Items.AddedItems;
import ohrm.SpatialMachines.main.SpatialMain;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AddedBlocks {	
	
	//Powered Machines
    public static Block SpatialPoweredFurnace;
    public static Block SpatialPoweredSmasher;
    
    public static Block BoostBlock;
    
    public static void addBlocks() {
    	
    	//Powered Machines
    	SpatialPoweredFurnace =  new SpatialPoweredFurnace();
    	SpatialPoweredSmasher = new SpatialPoweredSmasher();
    	
    	BoostBlock = new BoostBlock();
    
    	registerBlocks();
    	
    	blockRecipes();
    	
    }
    
    public static void registerBlocks(){
    	
    	//Game Reg    
    	//Powered Machines
    	GameRegistry.registerBlock(SpatialPoweredFurnace, "spatialPoweredFurnace");
    	GameRegistry.registerBlock(SpatialPoweredSmasher, "spatialPoweredSmasher");
    	
    	GameRegistry.registerBlock(BoostBlock, "speedBooster");
    	
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
