package com.bi11cypher.quickbuild.client.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import com.bi11cypher.quickbuild.data.StructureRegistry;
import com.bi11cypher.quickbuild.data.StructureCategory;

public class QuickBuildMainScreen extends Screen {

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_SPACING = 24;

    public QuickBuildMainScreen() {
        super(Text.literal("§l§2QuickBuild"));
    }

    @Override
    protected void init() {
        var categories = StructureRegistry.getCategories();
        int startY = (this.height - (categories.size() * BUTTON_SPACING)) / 2;

        for (int i = 0; i < categories.size(); i++) {
            StructureCategory category = categories.get(i);
            int buttonId = i;
            int yPos = startY + (i * BUTTON_SPACING);

            this.addDrawableChild(ButtonWidget.builder(
                Text.literal(category.label),
                button -> openCategoryScreen(buttonId)
            )
            .dimensions(
                (this.width - BUTTON_WIDTH) / 2,
                yPos,
                BUTTON_WIDTH,
                BUTTON_HEIGHT
            )
            .build());
        }

        // Close button
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Close"),
            button -> this.close()
        )
        .dimensions(
            (this.width - BUTTON_WIDTH) / 2,
            this.height - 30,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        )
        .build());
    }

    private void openCategoryScreen(int categoryId) {
        var categories = StructureRegistry.getCategories();
        if (categoryId >= 0 && categoryId < categories.size()) {
            this.client.setScreen(new QuickBuildCategoryScreen(categories.get(categoryId), this));
        }
    }

    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, "§l§2QuickBuild", this.width / 2, 20, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, "§7Choose a category:", this.width / 2, 40, 0xAAAAAA);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
