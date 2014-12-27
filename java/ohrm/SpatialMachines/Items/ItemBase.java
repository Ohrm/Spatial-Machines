package ohrm.SpatialMachines.Items;

import ohrm.SpatialMachines.Reference.Reference;
import ohrm.SpatialMachines.main.SpatialMain;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBase extends Item {
	protected IIcon[] icons;

	public ItemBase(String name){
		
		setUnlocalizedName(Reference.MODID + "_" + name);
		setTextureName(Reference.MODID + ":" + name);
		setCreativeTab(SpatialMain.spatialTab);
		
	}

}