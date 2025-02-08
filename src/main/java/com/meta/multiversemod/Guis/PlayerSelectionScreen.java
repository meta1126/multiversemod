package com.meta.multiversemod.Guis;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import net.minecraft.client.gui.components.Button;


import java.util.List;

public class PlayerSelectionScreen extends Screen {
    private final List<ServerPlayer> players;
    private final ServerPlayer user;
    public PlayerSelectionScreen(ServerPlayer user, List<ServerPlayer> players) {
        super(Component.literal("Select a Player"));
        this.user =user;
        this.players =players;
    }
    @Override
    protected void init() {
        int y = 20;
        for (ServerPlayer player : players) {
            Button button = Button.builder(
                    Component.literal(player.getName().getString()),
                                    b -> selectPlayer(player)
                            )
                    .pos(this.width / 2 - 75, y)
                    .size(150, 20)
                    .build();
            addRenderableWidget(button);
            y += 25;


        }
    }


        private void selectPlayer(ServerPlayer target) {
            this.user.teleportTo(target.level(), target.getX(), target.getY(),target.getZ(), target.getYRot(), target.getXRot());
            this.minecraft.setScreen(null);
        }

        @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta){
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        }
}
