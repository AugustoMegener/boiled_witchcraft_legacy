package org.kitowashere.boiled_witchcraft.items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.kitowashere.boiled_witchcraft.blocks.Glyph;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.GLYPH;

public class Pencil extends Item {
    public Pencil() {
        super(new Properties().durability(100));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack item = context.getItemInHand();
        
        CompoundTag nbt = item.getOrCreateTag();

        if(nbt.isEmpty()) {
            System.out.println("limpou");
            nbt.putInt("glyph", 0);
            item.setTag(nbt);
        }


        if(context.getPlayer().isShiftKeyDown()){
            Integer next_glyph = nbt.getInt("glyph");

            if(next_glyph==3) next_glyph = 0;
            else next_glyph++;


            nbt.putInt("glyph", next_glyph);
            item.setTag(nbt);

            context.getPlayer().displayClientMessage(Component.translatable(MODID + ".pencil.message." + next_glyph, true), true);
        } else {
            nbt = item.getOrCreateTag();

            DrawnGlyph(context, nbt.getInt("glyph"));
        }

        return super.useOn(context);
    }

    private void DrawnGlyph(UseOnContext context, Integer glyphNum) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().above();

        if(level.isEmptyBlock(pos)) {
            if(level.getBlockState(context.getClickedPos()).isSolidRender(level, context.getClickedPos())) {
                BlockState glyphBlock = GLYPH.get().defaultBlockState().setValue(Glyph.GLYPH, glyphNum);

                level.setBlock(pos, glyphBlock, 0);

                context.getPlayer().getCooldowns().addCooldown(context.getItemInHand().getItem(), 40);

                if (context.getPlayer() instanceof ServerPlayer) {
                    ItemStack item = context.getItemInHand();
                    item.hurt(1, level.random, context.getPlayer().getServer().getPlayerList().getPlayer(context.getPlayer().getUUID()));

                    if(item.getDamageValue() >= 100) {
                        context.getPlayer().getInventory().removeItem(item);
                    }
                }
            }
        }
    }
}
