package com.meta.multiversemod.common;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.apache.logging.log4j.util.Supplier;

import java.util.UUID;

public class PlayerSelectedMessage {
    public  final int containerId;
    public final UUID targetUUID;

    public PlayerSelectedMessage(int containerId, UUID targetUUID){
        this.containerId = containerId;
        this.targetUUID = targetUUID;
    }
}
public class ServerMessageHandler {
    public static void handlePlayerSelected(PlayerSelectedMessage message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()-> {
            ServerPlayer player = ctx.get().getSender();
            if(player !=null) {
                ServerPlayer target =
                        player.getServer().getPlayerList().getPlayer(message.targetUUID);
            if (target !=null) {
                ServerLevel serverLevel = target.getServer().getLevel(target.level().dimension());
                player.teleportTo(serverLevel,target.getX(),target.getY(),target.getZ(),target.getYRot(),target.getXRot());

            }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
