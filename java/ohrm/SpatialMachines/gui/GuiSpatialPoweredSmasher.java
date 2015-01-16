package ohrm.SpatialMachines.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import ohrm.SpatialMachines.container.ContainerSpatialPoweredFurnace;
import ohrm.SpatialMachines.container.ContainerSpatialPoweredSmasher;
import ohrm.SpatialMachines.tile.TileEntitySpatialPoweredFurnace;
import ohrm.SpatialMachines.tile.TileEntitySpatialPoweredSmasher;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiSpatialPoweredSmasher extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("spatialmachines", "textures/gui/furnace.png");
    private TileEntitySpatialPoweredSmasher tileEntitySpatialPoweredSmasher;
    private static final String __OBFID = "CL_00000758";

    public GuiSpatialPoweredSmasher(InventoryPlayer player, TileEntitySpatialPoweredSmasher tile)
    {
        super(new ContainerSpatialPoweredSmasher(player, tile));
        this.tileEntitySpatialPoweredSmasher = tile;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String s = this.tileEntitySpatialPoweredSmasher.hasCustomInventoryName() ? this.tileEntitySpatialPoweredSmasher.getInventoryName() : I18n.format("Spatial Powered Smasher", new Object[0]);
        String energy = Integer.toString(this.tileEntitySpatialPoweredSmasher.getEnergyStored(ForgeDirection.UNKNOWN));
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float x, int y, int z)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        
        if(this.tileEntitySpatialPoweredSmasher.isActive()){
        	int i1 = this.tileEntitySpatialPoweredSmasher.getIsActive(13);
        	this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        	i1 = this.tileEntitySpatialPoweredSmasher.getCookProgressScaled(24);
        	this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        }
    }
}