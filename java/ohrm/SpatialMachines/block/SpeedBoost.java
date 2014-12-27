package ohrm.SpatialMachines.block;

import ohrm.SpatialMachines.Reference.Reference;
import ohrm.SpatialMachines.main.SpatialMain;
import ohrm.SpatialMachines.tile.TileEntitySpeedBoost;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SpeedBoost extends BlockContainer {

	protected SpeedBoost() {
		
		super(Material.anvil);
		setBlockName(Reference.MODID + "_" + "SpeedBoostBasic");
		setBlockTextureName(Reference.MODID + ":" + "SpeedBoostBasic");
		setCreativeTab(SpatialMain.spatialTab);
		
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		
		return new TileEntitySpeedBoost();
		
	}

	@Override
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		
		p_149727_5_.openGui(SpatialMain.instance, 1, p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
		
		return true;
	}
	
}
