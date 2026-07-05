package io.github.poggersv2.mixin;

import io.github.poggersv2.items.ModItems;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
    @Shadow @Final private Minecraft minecraft;

    // Define the correct path to your custom phone scope
    private static final Identifier PHONE_SCOPE = Identifier.fromNamespaceAndPath("passive_aggression", "textures/misc/phone_scope.png");

    // Injected safely right at the start (HEAD) of the GUI render cycle
    private void renderCustomPhoneScope(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (this.minecraft.player != null && this.minecraft.options.getCameraType().isFirstPerson()) {
            ItemStack itemStack = this.minecraft.player.getUseItem();

            // Check if the item currently being actively used is your phone
            if (itemStack.is(ModItems.PHONE)) {
                int screenWidth = guiGraphics.guiWidth();
                int screenHeight = guiGraphics.guiHeight();

                guiGraphics.pose().pushPose();
                // Draw the custom phone overlay map across the entire width and height
                guiGraphics.blit(PHONE_SCOPE, 0, 0, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);
                guiGraphics.pose().popPose();
            }
        }
    }
}