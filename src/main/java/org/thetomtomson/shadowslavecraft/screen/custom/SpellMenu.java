package org.thetomtomson.shadowslavecraft.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.thetomtomson.shadowslavecraft.data.player.custom.SpellPlayerData;
import org.thetomtomson.shadowslavecraft.data.player.custom.attribute.Attribute;

public class SpellMenu extends Screen {
    private final SpellPlayerData data;

    private static final int TEXTURE_WIDTH = 1696;
    private static final int TEXTURE_HEIGHT = 2528;
    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 250;

    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(
            "shadowslavecraft", "textures/gui/status_page_placeholder_ai_gen.png"
    );

    // Konstruktor przyjmuje teraz dane gracza
    public SpellMenu(SpellPlayerData data) {
        super(Component.literal("Spell Status"));
        this.data = data;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick); // Dodaje przyciemnienie tła gry

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (width - IMAGE_WIDTH) / 2;
        int y = (height - IMAGE_HEIGHT) / 2;

        // Rysowanie tła
        graphics.blit(BACKGROUND, x, y, IMAGE_WIDTH, IMAGE_HEIGHT, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);

        int currentY = y + 20;
        int spacing = 12; // Odstęp między liniami
        int leftPadding = x + 20;
        int color = 0xFFFFFF;

        // --- PODSTAWOWE STATYSTYKI ---
        graphics.drawString(font, "Name: " + data.getName(), leftPadding, currentY, color, false);
        currentY += spacing;

        graphics.drawString(font, "True Name: " + data.getTrueName().name(), leftPadding, currentY, color, false);
        currentY += spacing;

        graphics.drawString(font, "Rank: " + data.getRank().name(), leftPadding, currentY, color, false);
        currentY += spacing;

        graphics.drawString(font, "Soul Core: " + data.getSoulCore().name(), leftPadding, currentY, color, false);
        currentY += spacing;

        String shards = String.format("Soul Shards: %d / %d", data.getSoulShards(), data.getSoulShardsMax());
        graphics.drawString(font, shards, leftPadding, currentY, color, false);
        currentY += spacing * 1.5; // Większy odstęp przed aspektem

        // --- ASPEKT ---
        String aspectName = (data.getPlayerAspect() != null) ? data.getPlayerAspect().getName() : "None";
        graphics.drawString(font, "Aspect: " + aspectName, leftPadding, currentY, 0xFFAA00, false);
        currentY += spacing * 1.5;

        // --- ATRYBUTY ---
        graphics.drawString(font, "Attributes:", leftPadding, currentY, 0x55FF55, false);
        currentY += spacing;

        if (data.getAttributes().isEmpty()) {
            graphics.drawString(font, "- None", leftPadding + 5, currentY, 0xAAAAAA, false);
        } else {
            for (Attribute attr : data.getAttributes()) {
                graphics.drawString(font, "• " + attr.getName(), leftPadding + 5, currentY, color, false);
                currentY += spacing;

                // Jeśli ekran jest za mały, przerywamy rysowanie atrybutów
                if (currentY > y + IMAGE_HEIGHT - 20) break;
            }
        }

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false; // Czy GUI ma pauzować grę w singleplayer
    }
}