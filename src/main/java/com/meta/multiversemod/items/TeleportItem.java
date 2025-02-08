package com.meta.multiversemod.items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;


public class TeleportItem extends Item {

    private List<ServerPlayer> getAllOnlinePlyers(ServerPlayer user) {
        return user.getServer().getPlayerList().getPlayers();
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand){
        if(!level.isClientSide) {
            Player target = findTargetPlayer(player);
            player.teleportTo(
                    target.getX(),
                    target.getY(),
                    target.getZ()
            );
            ItemStack itemStack = player.getItemInHand(hand);
            itemStack.shrink(1);
        }
        return InteractionResultHolder.success(player.getItemInHand((hand)));
    }
    private Player findTargetPlayer(Player user, String targetName) {
        if(user.level().isClientSide) return null;

        ServerPlayer target = user.getServer().getPlayerList().getPlayerByName(targetName);

        if (target == null) {
            user.displayClientMessage(Component.literal("プレイヤー" + targetName + "はオフラインです"),false);
            return null;
        }
        return target;
    }
    public TeleportItem(Properties p_41383_) {
        super(p_41383_);
    }
}
