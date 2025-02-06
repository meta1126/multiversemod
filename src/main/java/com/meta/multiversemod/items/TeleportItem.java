package com.meta.multiversemod.items;

import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.logging.Level;

public class TeleportItem extends Item {

    public InteractionResultHolder<ItemStack> use(Level level, Player player,...){
        if(!level.isClientSide) {
            Player target = findTargetPlayer();
            player.teleportTo(
                    target.getX(),
                    target.getY(),
                    target.getZ()
            );
            player.getItemInHand().shrink(1);
        }
        return InteractionResultHolder.success(...);
    }
    public TeleportItem(Properties p_41383_) {
        super(p_41383_);
    }
}
