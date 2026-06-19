package com.bi11cypher.quickbuild.client.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.entity.player.PlayerEntity;
import com.bi11cypher.quickbuild.data.StructureCategory;
import com.bi11cypher.quickbuild.util.StructureSpawner;

public class QuickBuildCategoryScreen extends Screen {

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_SPACING = 24;

    private final StructureCategory category;
    private final Screen parentScreen;

    public QuickBuildCategoryScreen(StructureCategory category, Screen parentScreen) {
        super(Text.literal(category.label));
        this.category = category;
        this.parentScreen = parentScreen;
    }

    @Override
    protected void init() {
        int startY = (this.height - ((this.category.structures.size() + 1) * BUTTON_SPACING)) / 2;

        for (int i = 0; i < this.category.structures.size(); i++) {
            StructureCategory.Structure structure = this.category.structures.get(i);
            int structureIndex = i;
            int yPos = startY + (i * BUTTON_SPACING);

            this.addDrawableChild(ButtonWidget.builder(
                Text.literal(structure.label),
                button -> spawnStructure(structure.name)
            )
            .dimensions(
                (this.width - BUTTON_WIDTH) / 2,
                yPos,
                BUTTON_WIDTH,
                BUTTON_HEIGHT
            )
            .build());
        }

        // Back button
        int backY = startY + (this.category.structures.size() * BUTTON_SPACING);
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("§7← Back to categories"),
            button -> this.client.setScreen(parentScreen)
        )
        .dimensions(
            (this.width - BUTTON_WIDTH) / 2,
            backY,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        )
        .build());
    }

    private void spawnStructure(String structureName) {
        PlayerEntity player = this.client.player;
        if (player != null) {
            StructureSpawner.spawnStructure(player, structureName);
            this.close();
        }
    }

    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.category.label, this.width / 2, 20, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, "§7Pick a structure to spawn 10 blocks ahead:", this.width / 2, 40, 0xAAAAAA);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
