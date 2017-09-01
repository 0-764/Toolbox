package com.j10max.client.impl.util;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GLUtil {

    public static void renderFilledCuboid(AxisAlignedBB aaBB, Color color) {
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        GL11.glEnable(GL11.GL_BLEND);
        renderCuboid(aaBB, 30, color);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_POLYGON_OFFSET_LINE);
        GL11.glPolygonOffset(-1.f, -1.f);
    }

    public static void renderUnfilledCuboid(AxisAlignedBB aaBB, Color color) {
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        renderCuboid(aaBB, 255, color);
    }

    public static void renderCuboid(AxisAlignedBB bb, int alphaChannel, Color color) {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer worldRenderer = tessellator.getBuffer();

        int colorR = color.getRed();
        int colorG = color.getGreen();
        int colorB = color.getBlue();

        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(bb.minX, bb.minY, bb.minZ)
                .color(colorR, colorG, colorB, alphaChannel)
                .endVertex();
        worldRenderer.pos(bb.maxX, bb.minY, bb.minZ)
                .color(colorR, colorG, colorB, alphaChannel)
                .endVertex();
        worldRenderer.pos(bb.maxX, bb.minY, bb.maxZ)
                .color(colorR, colorG, colorB, alphaChannel)
                .endVertex();
        worldRenderer.pos(bb.minX, bb.minY, bb.maxZ)
                .color(colorR, colorG, colorB, alphaChannel)
                .endVertex();

        if (bb.minY != bb.maxY) {
            worldRenderer.pos(bb.minX, bb.maxY, bb.minZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.maxX, bb.maxY, bb.minZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.maxX, bb.maxY, bb.maxZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.minX, bb.maxY, bb.maxZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();

            worldRenderer.pos(bb.minX, bb.minY, bb.maxZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.minX, bb.maxY, bb.maxZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.maxX, bb.maxY, bb.maxZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.maxX, bb.minY, bb.maxZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();

            worldRenderer.pos(bb.minX, bb.minY, bb.minZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.minX, bb.maxY, bb.minZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.maxX, bb.maxY, bb.minZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.maxX, bb.minY, bb.minZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();

            worldRenderer.pos(bb.minX, bb.minY, bb.minZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.minX, bb.minY, bb.maxZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.minX, bb.maxY, bb.maxZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.minX, bb.maxY, bb.minZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();

            worldRenderer.pos(bb.maxX, bb.minY, bb.minZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.maxX, bb.minY, bb.maxZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.maxX, bb.maxY, bb.maxZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
            worldRenderer.pos(bb.maxX, bb.maxY, bb.minZ)
                    .color(colorR, colorG, colorB, alphaChannel)
                    .endVertex();
        }
        tessellator.draw();
    }

}
