package ohrm.SpatialMachines.gui;

import ohrm.SpatialMachines.container.ContainerSpatialPoweredFurnace;
import ohrm.SpatialMachines.container.ContainerBoostBlock;
import ohrm.SpatialMachines.container.ContainerSpatialPoweredSmasher;
import ohrm.SpatialMachines.main.SpatialMain;
import ohrm.SpatialMachines.tile.TileEntitySpatialPoweredFurnace;
import ohrm.SpatialMachines.tile.TileEntityBoostBlock;
import ohrm.SpatialMachines.tile.TileEntitySpatialPoweredSmasher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GUIHandler implements IGuiHandler {
		
	public GUIHandler(){
		NetworkRegistry.INSTANCE.registerGuiHandler(SpatialMain.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te;
		switch (ID){
		case 0:
			te = world.getTileEntity(x, y, z);
			if(te != null && te instanceof TileEntitySpatialPoweredFurnace){
				
				return new ContainerSpatialPoweredFurnace(player.inventory, (TileEntitySpatialPoweredFurnace)te);
			
			}
			break;
		
		case 1:
			te = world.getTileEntity(x, y, z);
			if(te != null && te instanceof TileEntityBoostBlock){
				
				return new ContainerBoostBlock(player.inventory, (TileEntityBoostBlock)te);
				
			}
			break;
			
		case 2:
			te = world.getTileEntity(x, y, z);
			if(te != null && te instanceof TileEntitySpatialPoweredSmasher){
				
				return new ContainerSpatialPoweredSmasher(player.inventory, (TileEntitySpatialPoweredSmasher)te);
				
			}
			break;
			
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity te;
		
		switch(ID){
		case 0:
			te = world.getTileEntity(x, y, z);
			if (te != null && te instanceof TileEntitySpatialPoweredFurnace){
				
				return new GuiSpatialPoweredFurnace(player.inventory, (TileEntitySpatialPoweredFurnace)te);
			
			}
			break;
		
		case 1:
			te = world.getTileEntity(x, y, z);
			if(te != null && te instanceof TileEntityBoostBlock){
				
				return new GuiBoostBlock(player.inventory, (TileEntityBoostBlock)te);
				
			}
			break;
			
		case 2:
			te = world.getTileEntity(x, y, z);
			if(te != null && te instanceof TileEntitySpatialPoweredSmasher){
				
				return new GuiSpatialPoweredSmasher(player.inventory, (TileEntitySpatialPoweredSmasher)te);
				
			}
			break;
						
		}
		
		return null;
	}

}