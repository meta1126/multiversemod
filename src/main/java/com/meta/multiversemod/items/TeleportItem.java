package com.meta.multiversemod.items;

import com.meta.multiversemod.Guis.PlayerSelectionScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class TeleportItem extends Item {

    public TeleportItem(Properties p_41383_) {
        super(p_41383_);
    }
    private List<ServerPlayer> getAllOnlinePlyers(ServerPlayer user) {
        return user.getServer().getPlayerList().getPlayers();
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            openPlayerSelectionScreen(serverPlayer);
            return
           InteractionResultHolder.consume(player.getItemInHand(hand));
                }
                return InteractionResultHolder.pass(player.getItemInHand(hand));

        }

    private void openPlayerSelectionScreen(ServerPlayer player) {
        List<ServerPlayer> players =
                player.getServer().getPlayerList().getPlayers();
        NetworkHooks.openScreen(player, new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("Select a Player");
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player player) {
                return new PlayerSelectionScreen(windowId, players,(ServerPlayer) player);
            }
        });
    }

    }

