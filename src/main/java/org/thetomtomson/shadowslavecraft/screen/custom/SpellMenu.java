package org.thetomtomson.shadowslavecraft.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public class SpellMenu extends Screen {
    // Rozmiar pliku PNG
    private static final int TEXTURE_WIDTH = 1696;
    private static final int TEXTURE_HEIGHT = 2528;

    // Rozmiar okna GUI (tyle zajmuje na ekranie)
    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 250;

    private static final int  TEXT_PADDING_LEFT = 20;
    private static final Map<String, Integer> DISPLAY_TEXT_AND_PADDING_TOP = Map.of(
            "Name :", 20,
            "True Name :", 40,
            "Rank :", 60,
            "Soul Core :", 80,
            "Soul Shards :", 100
    );

    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(
            "shadowslavecraft", "textures/gui/status_page_placeholder_ai_gen.png"
    );
    public SpellMenu() {
        super(Component.literal("Moje GUI"));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);

        int x = (width - IMAGE_WIDTH) / 2;
        int y = (height - IMAGE_HEIGHT) / 2;

        graphics.blit(
                BACKGROUND,
                x, y,
                IMAGE_WIDTH, IMAGE_HEIGHT,   // ← rozmiar na ekranie (200x200)
                0, 0,
                TEXTURE_WIDTH, TEXTURE_HEIGHT, // ← wycinek z pliku (cały)
                TEXTURE_WIDTH, TEXTURE_HEIGHT  // ← pełny rozmiar pliku
        );

        DISPLAY_TEXT_AND_PADDING_TOP.forEach((text, paddingTop) -> {
            graphics.drawString(
                    this.font,
                    text,
                    x + TEXT_PADDING_LEFT,
                    y + paddingTop,
                    0xFFFFFF,
                    false
            );
        });

    }


}