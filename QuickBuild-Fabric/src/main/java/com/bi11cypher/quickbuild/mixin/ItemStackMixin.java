package com.bi11cypher.quickbuild.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackActionExceptionDetails;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import com.bi11cypher.quickbuild.client.gui.QuickBuildMainScreen;

@Mixin(ClientPlayerInteractionManager.class)
public class ItemStackMixin {

    /**
     * Intercept right-click action to open menu when compass is right-clicked
     */
    @Inject(
        method = "interactItem",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onInteractItem(PlayerEntity player, Hand hand, CallbackInfo ci) {
        var itemStack = player.getStackInHand(hand);

        if (itemStack.isOf(Items.COMPASS)) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.currentScreen == null) {
                client.setScreen(new QuickBuildMainScreen());
            }
        }
    }
}
