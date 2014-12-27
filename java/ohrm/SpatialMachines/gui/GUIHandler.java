package ohrm.SpatialMachines.gui;

import ohrm.SpatialMachines.container.ContainerSpatialPoweredFurnace;
import ohrm.SpatialMachines.container.ContainerSpeedBoost;
import ohrm.SpatialMachines.main.SpatialMain;
import ohrm.SpatialMachines.tile.TileEntitySpatialPoweredFurnace;
import ohrm.SpatialMachines.tile.TileEntitySpeedBoost;
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
			if(te != null && te instanceof TileEntitySpeedBoost){
				
				return new ContainerSpeedBoost(player.inventory, (TileEntitySpeedBoost)te);
				
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
			if(te != null && te instanceof TileEntitySpeedBoost){
				
				return new GuiSpeedBoost(player.inventory, (TileEntitySpeedBoost)te);
				
			}
			break;
						
		}
		
		return null;
	}

}