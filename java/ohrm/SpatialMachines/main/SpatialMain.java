package ohrm.SpatialMachines.main;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import ohrm.SpatialMachines.Items.AddedItems;
import ohrm.SpatialMachines.Reference.Reference;
import ohrm.SpatialMachines.Tabs.SpatialTab;
import ohrm.SpatialMachines.block.AddedBlocks;
import ohrm.SpatialMachines.gui.GUIHandler;
import ohrm.SpatialMachines.proxies.ClientProxy;
import ohrm.SpatialMachines.proxies.CommonProxy;
import ohrm.SpatialMachines.tile.TileEntitySpatialPoweredFurnace;

@Mod(name = "Spatial Machines", modid = Reference.MODID, version = Reference.MODVERSION)
public class SpatialMain {
	
	@SidedProxy(clientSide = "ohrm.SpatialMachines.proxies.ClientProxy", serverSide = "ohrm.SpatialMachines.proxies.CommonProxy")
	public static CommonProxy serverProxy;
	public static ClientProxy clientProxy;
	
	public static CreativeTabs spatialTab = new SpatialTab(CreativeTabs.getNextID(), "spatialTab");	
	
	@Mod.Instance(Reference.MODID)
	public static SpatialMain instance;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event){
		
		AddedItems.addItems();
		AddedBlocks.preinit();
		
	}

	@EventHandler
	public void init(FMLInitializationEvent event){
		
		serverProxy.registerRenders();
		
		GameRegistry.registerTileEntity(TileEntitySpatialPoweredFurnace.class, "Spatial Powered Furnace");
	
		new GUIHandler();
	
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event){
		
		
		
	
	}

}
