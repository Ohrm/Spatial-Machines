package ohrm.SpatialMachines.gui;

import ohrm.SpatialMachines.container.ContainerSpatialPoweredFurnace;
import ohrm.SpatialMachines.main.SpatialMain;
import ohrm.SpatialMachines.tile.TileEntitySpatialPoweredFurnace;
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
		switch (ID){
		case 0:
			TileEntity te = world.getTileEntity(x, y, z);
			if(te != null && te instanceof TileEntitySpatialPoweredFurnace){
				return new ContainerSpatialPoweredFurnace(player.inventory, (TileEntitySpatialPoweredFurnace)te);
			}
			break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case 0:
			TileEntity te = world.getTileEntity(x, y, z);
			if (te != null && te instanceof TileEntitySpatialPoweredFurnace){
				return new GuiSpatialPoweredFurnace(player.inventory, (TileEntitySpatialPoweredFurnace)te);
			}
			break;
		}
		return null;
	}

}