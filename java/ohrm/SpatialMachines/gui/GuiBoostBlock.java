package ohrm.SpatialMachines.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import ohrm.SpatialMachines.container.ContainerBoostBlock;
import ohrm.SpatialMachines.tile.TileEntityBoostBlock;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiBoostBlock extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("spatialmachines", "textures/gui/speedBoost.png");
    private TileEntityBoostBlock tileEntitySpeedBoost;
    private static final String __OBFID = "CL_00000758";

    public GuiBoostBlock(InventoryPlayer player, TileEntityBoostBlock tile)
    {
        super(new ContainerBoostBlock(player, tile));
        this.tileEntitySpeedBoost = tile;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String s = this.tileEntitySpeedBoost.hasCustomInventoryName() ? this.tileEntitySpeedBoost.getInventoryName() : I18n.format("Booster", new Object[0]);
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

    }
}