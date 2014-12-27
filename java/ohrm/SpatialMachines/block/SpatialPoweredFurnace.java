package ohrm.SpatialMachines.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ohrm.SpatialMachines.Reference.Reference;
import ohrm.SpatialMachines.main.SpatialMain;
import ohrm.SpatialMachines.tile.TileEntitySpatialPoweredFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SpatialPoweredFurnace extends BlockContainer {

	private boolean active;
	@SideOnly(Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconFront;
    @SideOnly(Side.CLIENT)
    private IIcon iconBottom;
    
    private final Random field_149933_a = new Random();
    
    private static boolean field_149934_M;
	
	protected SpatialPoweredFurnace() {
		super(Material.anvil);
		active = false;
		setBlockName(Reference.MODID + "_" + "SpatialPoweredFurnace");
		setCreativeTab(SpatialMain.spatialTab);
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(AddedBlocks.SpatialPoweredFurnace);
    }
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		
		return new TileEntitySpatialPoweredFurnace();
	
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		setDefaultDirection(world, x, y, z);
	}
	
	@Override
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		
		p_149727_5_.openGui(SpatialMain.instance, 0, p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
		
		return true;
	}
	
	private void setDefaultDirection(World world, int x, int y, int z) {
		if(!world.isRemote) {
			Block zNeg = world.getBlock(x, y, z - 1);
			Block zPos = world.getBlock(x, y, z - 1);
			Block xNeg = world.getBlock(x, y, z - 1);
			Block xPos = world.getBlock(x, y, z - 1);
			byte meta = 3;

			if(xNeg.isOpaqueCube() && !xPos.isOpaqueCube()) {
				meta = 5;
			}

			if(xPos.isOpaqueCube() && !xNeg.isOpaqueCube()) {
				meta = 4;
			}

			if(zNeg.isOpaqueCube() && !zPos.isOpaqueCube()) {
				meta = 3;
			}

			if(zPos.isOpaqueCube() && !zNeg.isOpaqueCube()) {
				meta = 2;
			}

			world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		}
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
		if(side == 1) {
			return this.iconTop;
		} else if(side == 0){
			return this.iconBottom;
		}else if(side != meta) {
			return this.blockIcon;
		} else {
			return this.iconFront;
		}
    }
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
        this.blockIcon = icon.registerIcon(Reference.MODID + ":" + "furnace_side");
        this.iconFront = icon.registerIcon(this.active ? Reference.MODID + ":" + "furnace_front_on" : Reference.MODID + ":" + "furnace_front_off");
        this.iconTop = icon.registerIcon(Reference.MODID + ":" + "furnace_top");
        this.iconBottom = icon.registerIcon(Reference.MODID + ":" + "furnace_bottom");
    }
	
	public void updateFurnaceBlockState(boolean p_149931_0_, World p_149931_1_, int p_149931_2_, int p_149931_3_, int p_149931_4_)
    {
        int l = p_149931_1_.getBlockMetadata(p_149931_2_, p_149931_3_, p_149931_4_);
        TileEntity tileentity = p_149931_1_.getTileEntity(p_149931_2_, p_149931_3_, p_149931_4_);
        field_149934_M = true;

        if(tileentity instanceof TileEntitySpatialPoweredFurnace){
        	
        	if(((TileEntitySpatialPoweredFurnace) tileentity).isActive()){
        		
        		active = true;
        		
        	}else{
        		
        		active = false;
        		
        	}
        	
        }
        
        if (p_149931_0_)
        {
            p_149931_1_.setBlock(p_149931_2_, p_149931_3_, p_149931_4_, AddedBlocks.SpatialPoweredFurnace);
        }
        else
        {
            p_149931_1_.setBlock(p_149931_2_, p_149931_3_, p_149931_4_, AddedBlocks.SpatialPoweredFurnace);
        }

        if (tileentity != null)
        {
            tileentity.validate();
            p_149931_1_.setTileEntity(p_149931_2_, p_149931_3_, p_149931_4_, tileentity);
                                
        }
        
        field_149934_M = false;
        p_149931_1_.setBlockMetadataWithNotify(p_149931_2_, p_149931_3_, p_149931_4_, l, 2);
        
    }

	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 1)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }

        if (l == 3)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }

    }
	
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        if (!field_149934_M)
        {
            TileEntitySpatialPoweredFurnace tileentityfurnace = (TileEntitySpatialPoweredFurnace)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

            if (tileentityfurnace != null)
            {
                for (int i1 = 0; i1 < tileentityfurnace.getSizeInventory(); ++i1)
                {
                    ItemStack itemstack = tileentityfurnace.getStackInSlot(i1);

                    if (itemstack != null)
                    {
                        float f = this.field_149933_a.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.field_149933_a.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.field_149933_a.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0)
                        {
                            int j1 = this.field_149933_a.nextInt(21) + 10;

                            if (j1 > itemstack.stackSize)
                            {
                                j1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j1;
                            EntityItem entityitem = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + f), (double)((float)p_149749_3_ + f1), (double)((float)p_149749_4_ + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound())
                            {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double)((float)this.field_149933_a.nextGaussian() * f3);
                            entityitem.motionY = (double)((float)this.field_149933_a.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double)((float)this.field_149933_a.nextGaussian() * f3);
                            p_149749_1_.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
            }
        }

        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random p_149734_5_)
    {
		TileEntitySpatialPoweredFurnace tileentityfurnace = (TileEntitySpatialPoweredFurnace)world.getTileEntity(x, y, z);
        
		if (tileentityfurnace != null)
        {
        	if(tileentityfurnace.isActive()){
        		int l = world.getBlockMetadata(x, y, z);
            	float f = (float)x + 0.5F;
            	float f1 = (float)y + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
            	float f2 = (float)z + 0.5F;
            	float f3 = 0.52F;
            	float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

            	if (l == 4)
            	{
            		world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            		world.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            	}
            	else if (l == 5)
            	{
            		world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            		world.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            	}
            	else if (l == 2)
            	{
            		world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            		world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            	}
            	else if (l == 3)
            	{
            		world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            		world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            	}
        	}
        }
    }
	
	public boolean hasComparatorInputOverride()
    {
        return true;
    }

    public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
    {
        return Container.calcRedstoneFromInventory((IInventory)p_149736_1_.getTileEntity(p_149736_2_, p_149736_3_, p_149736_4_));
    }
	
	 @SideOnly(Side.CLIENT)
	 public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	 {
		 return Item.getItemFromBlock(AddedBlocks.SpatialPoweredFurnace);
	 }
	 
}
