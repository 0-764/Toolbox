package com.j10max.client.impl.mod.impl.world;

import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.Mod;
import com.j10max.client.impl.mod.value.impl.FloatModValue;
import com.j10max.client.impl.util.GLUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchMod extends Mod {

    public FloatModValue searchTicks;
    public FloatModValue searchRadius;

    public List<BlockPos> blocksToRender;
    public List<Block> blocksToFind;
    public int searchCount;

    private SearchModThread searchModThread;

    public SearchMod() {
        super("Search", "1.0", ModType.WORLD);
        this.setKeybind(Keyboard.KEY_X);

        this.searchModThread = new SearchModThread(this);
        this.blocksToRender = new ArrayList<BlockPos>();
        this.blocksToFind = new ArrayList<Block>();
        this.blocksToFind.add(Blocks.GRASS);
        // Mod Values
        this.searchTicks = new FloatModValue("Query ticks", 20.0f);
        this.searchRadius = new FloatModValue("Search Radius", 10.0f);
    }

    @Override
    public void onInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onEnable() {
        this.searchModThread.start();
    }

    @Override
    public void onDisable() {
        this.searchModThread.stop();
    }

    @SubscribeEvent
    public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
        if (getState()) {
            for (BlockPos blockPos : this.blocksToRender) {
            /* Sort out Pos */
                float f = event.getPartialTicks();

                EntityPlayerSP entityPlayer = mcInstance.thePlayer;
                double dx = entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX) * (double) f;
                double dy = entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY) * (double) f;
                double dz = entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ) * (double) f;

                AxisAlignedBB bb = new AxisAlignedBB(blockPos);
                bb = bb.offset(-dx, -dy, -dz);

                GL11.glPushMatrix();

                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                GlStateManager.glLineWidth(2.0F);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(false);

                GLUtil.renderFilledCuboid(bb, Color.white);

                GlStateManager.depthMask(true);
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();

                GL11.glPopMatrix();
            }
        }
    }

    class SearchModThread extends Thread {

        private SearchMod searchMod;

        public SearchModThread(SearchMod searchMod) {
            this.searchMod = searchMod;
        }

        @Override
        public void run() {
            while (this.searchMod.getState() && mcInstance.theWorld != null) {
                this.searchMod.searchCount++;
                if (this.searchMod.searchCount >= this.searchMod.searchTicks.getValue()) {
                    // Radius
                    float radius = this.searchMod.searchRadius.getValue();
                    // Clear blocks to render
                    this.searchMod.blocksToRender.clear();
                    // Player position
                    double posX = mcInstance.thePlayer.posX;
                    double posY = mcInstance.thePlayer.posY;
                    double posZ = mcInstance.thePlayer.posZ;
                    // Loop through radius
                    for (double x = posX; x < (posX + radius); x++) {
                        for (double y = posY; y < (posY + radius); y++) {
                            for (double z = posZ; z < (posZ + radius); z++) {
                                // Block position
                                BlockPos blockPos = new BlockPos(x, y, z);
                                IBlockState blockState = mcInstance.theWorld.getBlockState(blockPos);
                                // Check block
                                for (Block blockToFind : this.searchMod.blocksToFind) {

                                }
                            }
                        }
                    }
                    // Reset search timer
                    this.searchMod.searchCount = 0;
                }
            }
        }

    }

}
