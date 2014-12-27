package ohrm.SpatialMachines.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import ohrm.SpatialMachines.container.ContainerSpeedBoost;
import ohrm.SpatialMachines.tile.TileEntitySpeedBoost;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiSpeedBoost extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("spatialmachines", "textures/gui/speedBoost.png");
    private TileEntitySpeedBoost tileEntitySpeedBoost;
    private static final String __OBFID = "CL_00000758";

    public GuiSpeedBoost(InventoryPlayer player, TileEntitySpeedBoost tile)
    {
        super(new ContainerSpeedBoost(player, tile));
        this.tileEntitySpeedBoost = tile;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String s = this.tileEntitySpeedBoost.hasCustomInventoryName() ? this.tileEntitySpeedBoost.getInventoryName() : I18n.format("Speed Booster", new Object[0]);
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