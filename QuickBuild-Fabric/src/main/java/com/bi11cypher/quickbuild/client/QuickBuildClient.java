package com.bi11cypher.quickbuild.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import com.bi11cypher.quickbuild.QuickBuild;
import com.bi11cypher.quickbuild.client.gui.QuickBuildScreen;

public class QuickBuildClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(this::onClientTick);
        QuickBuild.LOGGER.info("[QuickBuild] Client initialized!");
    }

    private void onClientTick(MinecraftClient client) {
        if (client.player == null) return;

        // Check for compass use (this will be handled via mixin)
    }
}
