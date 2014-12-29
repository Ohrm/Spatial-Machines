package ohrm.SpatialMachines.Tabs;

import ohrm.SpatialMachines.block.AddedBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SpatialTab extends CreativeTabs{

	public SpatialTab(int p_i1853_1_, String p_i1853_2_) {
		super(p_i1853_1_, p_i1853_2_);
	}
		
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem(){
				
		return Item.getItemFromBlock(AddedBlocks.SpatialPoweredFurnace);			
			
	}
	
}
