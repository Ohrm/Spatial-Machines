package ohrm.SpatialMachines.Items;

import java.util.List;

import ohrm.SpatialMachines.Reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemSpeedBoostBase extends ItemBase {

	public IIcon[] icons = new IIcon[3];
	
	public ItemSpeedBoostBase(String name) {
		
		super(name);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
	    for (int i = 0; i < 3; i ++) {
	        this.icons[i] = reg.registerIcon(Reference.MODID + ":speedBoost_" + i);
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
