package com.j10max.client.impl.panel.impl;

import com.j10max.client.impl.panel.Panel;
import com.j10max.client.impl.panel.component.PanelComponent;
import net.minecraft.block.Block;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameData;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class SearchPanel extends Panel {

    public SearchPanel() {
        super("Search");
        setWidth(150);
    }

    @Override
    public void init() {
        registerComponent(new SearchPanelComponent());
    }

    class SearchPanelComponent extends PanelComponent {

        int currentPage = 1;
        int countOfItemsPerPage = 6;

        private List<Block> blocksForRender;

        public SearchPanelComponent() {
            super("Search", 146, 100);
            this.blocksForRender = new ArrayList<Block>();
        }

        @Override
        public void onDraw(int mouseX, int mouseY, float partialTicks) {
            setHeight(100);
            Gui.drawRect(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + (this.getHeight()), 0xff3c3c3c);
            if (this.blocksForRender.size() == 0) this.blocksForRender = searchForBlocks();
            /* Draw */
            int row = 0, count = 0;
            for (Block block : this.blocksForRender) {
                // Position
                int x = (count * 20) + 4;
                int y = (row * 22) + 3;
                /* Render block */
                Gui.drawRect(this.getPosX() + x, this.getPosY() + y + 1, this.getPosX() + x + 18, this.getPosY() + y + 21, 0xbbFFFFFF);
                GL11.glPushMatrix();
                GL11.glColor4f(1f, 1f, 1f, 1f);
                RenderHelper.enableGUIStandardItemLighting();
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                mcInstance.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(block, 1, 0), this.getPosX() + x + 1, this.getPosY() + y + 3);
                RenderHelper.disableStandardItemLighting();
                GL11.glPopMatrix();
                // Re-position
                if (count == countOfItemsPerPage) {
                    count = 0;
                    row++;
                } else {
                    count++;
                }
            }
            /* Navigation */
            Gui.drawRect(this.getPosX() + this.getWidth() - 32, this.getPosY() + this.getHeight() - 13, this.getPosX() + this.getWidth() - 20, this.getPosY() + this.getHeight() - 2, 0xbbFFFFFF);
            mcInstance.fontRendererObj.drawString("<", this.getPosX() + this.getWidth() - 28, this.getPosY() + this.getHeight() - 11, 0xff000000);
            // Right
            Gui.drawRect(this.getPosX() + this.getWidth() - 15, this.getPosY() + this.getHeight() - 13, this.getPosX() + this.getWidth() - 2, this.getPosY() + this.getHeight() - 2, 0xbbFFFFFF);
            mcInstance.fontRendererObj.drawString(">", this.getPosX() + this.getWidth() - 10, this.getPosY() + this.getHeight() - 11, 0xff000000);

        }

        public List<Block> searchForBlocks() {
            List<Block> blockList = new ArrayList<Block>();
            int count = 0, num = 0;
            for (Block block : GameData.getBlockRegistry()) {
                if (count >= (currentPage * countOfItemsPerPage)) {
                    blockList.add(block);
                    if (num > (countOfItemsPerPage * 4)) {
                        return blockList;
                    }
                    num++;
                }
                count++;
            }
            return blockList;
        }

        @Override
        public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {
            // LEFT
            if (mouseX >= (this.getPosX() + this.getWidth() - 32) && mouseY >= (this.getPosY() + this.getHeight() - 13) && mouseX <= (this.getPosX() + this.getWidth() - 20) && mouseY <= (this.getPosY() + this.getHeight() - 2)) {
                if (currentPage > 1) {
                    currentPage--;
                    // update
                    this.blocksForRender.clear();
                    this.blocksForRender = searchForBlocks();
                }
            }
            // RIGHT
            if (mouseX >= (this.getPosX() + this.getWidth() - 15) && mouseY >= (this.getPosY() + this.getHeight() - 13) && mouseX <= (this.getPosX() + this.getWidth() - 2) && mouseY <= (this.getPosY() + this.getHeight() - 2)) {
                if (currentPage < (GameData.getBlockRegistry().getEntries().size() / this.countOfItemsPerPage)) {
                    currentPage++;
                    // update
                    this.blocksForRender.clear();
                    this.blocksForRender = searchForBlocks();
                }
            }
        }

        @Override
        public void onKeyTyped(char typedChar, int keyCode) {

        }

        @Override
        public void onMouseDragged(int mouseX, int mouseY) {

        }

    }

}
