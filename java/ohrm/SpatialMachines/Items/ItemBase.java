package ohrm.SpatialMachines.Items;

import ohrm.SpatialMachines.Reference.Reference;
import ohrm.SpatialMachines.main.SpatialMain;
import net.minecraft.item.Item;

public class ItemBase extends Item {

	public ItemBase(String name){
		
		setUnlocalizedName(Reference.MODID + "_" + name);
		setTextureName(Reference.MODID + ":" + name);
		setCreativeTab(SpatialMain.spatialTab);
		
	}
	
}
