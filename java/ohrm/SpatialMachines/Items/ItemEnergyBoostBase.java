package ohrm.SpatialMachines.Items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import ohrm.SpatialMachines.Reference.Reference;
import ohrm.SpatialMachines.main.SpatialMain;

public class ItemEnergyBoostBase extends Item {

public IIcon[] icons = new IIcon[3];
	
	public ItemEnergyBoostBase(String name) {
		
		setCreativeTab(SpatialMain.spatialTab);
		setUnlocalizedName(Reference.MODID + "_" + name);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
	    for (int i = 0; i < 3; i ++) {
	        this.icons[i] = reg.registerIcon(Reference.MODID + ":energyBoost_" + i);
	    }
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
	    if (meta > 2)
	        meta = 0;

	    return this.icons[meta];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 3; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    switch (stack.getItemDamage()) {
	    case 0:
	        return this.getUnlocalizedName() + "_basic";
	    case 1:
	        return this.getUnlocalizedName() + "_intermediate";
	    case 2:
	        return this.getUnlocalizedName() + "_advanced";
	    default:
	        return this.getUnlocalizedName();
	    }
	}
	
}
