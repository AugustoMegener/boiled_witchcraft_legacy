package org.kitowashere.boiled_witchcraft.items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.kitowashere.boiled_witchcraft.blocks.GlyphBlock;
import org.kitowashere.boiled_witchcraft.core.GlyphType;

import java.util.List;

import static net.minecraft.world.InteractionHand.*;
import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.GLYPH;
import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.GLYPH_ON_A_PAPER;

public class Pencil extends Item  {
    public Pencil() {
        super(new Properties().durability(100));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack item = null;

        switch (pUsedHand) {
            case MAIN_HAND -> item = pPlayer.getItemInHand(OFF_HAND);
            case OFF_HAND -> item = pPlayer.getItemInHand(MAIN_HAND);
        }

        if (item.getItem().getDescriptionId().equals("item.minecraft.paper")) {
            item.shrink(1);

            ItemStack paper = GLYPH_ON_A_PAPER.get().getDefaultInstance();
            paper.setTag(pPlayer.getItemInHand(pUsedHand).getTag());

            pPlayer.getInventory().add(paper);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack item = context.getItemInHand();
        
        CompoundTag nbt = item.getOrCreateTag();

        if (nbt.isEmpty()) {
            nbt.putInt("glyph", 0);
            item.setTag(nbt);
        }

        if (context.getPlayer().isShiftKeyDown()) {
            nbt.putInt("glyph", nbt.getInt("glyph")==3 ? 0 : nbt.getInt("glyph")+1);
            item.setTag(nbt);

            context.getPlayer().displayClientMessage(Component.translatable(MODID + ".pencil.message." + nbt.getInt("glyph"), true), true);
        } else {
            nbt = item.getOrCreateTag();

            DrawnGlyph(context, GlyphType.fromIndex(nbt.getInt("glyph")));
        }

        return super.useOn(context);
    }

    private void DrawnGlyph(UseOnContext context, GlyphType glyphType) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().above();

        if (level.isEmptyBlock(pos)) {
            if (level.getBlockState(context.getClickedPos()).isSolidRender(level, context.getClickedPos())) {
                BlockState glyphBlock = GLYPH.get().defaultBlockState().setValue(GlyphBlock.GLYPH, GlyphType.indexOf(glyphType));

                level.setBlock(pos, glyphBlock, 0);

                context.getPlayer().getCooldowns().addCooldown(context.getItemInHand().getItem(), 40);

                if (context.getPlayer() instanceof ServerPlayer) {
                    ItemStack item = context.getItemInHand();
                    item.hurt(1, level.random, context.getPlayer().getServer().getPlayerList().getPlayer(context.getPlayer().getUUID()));

                    if (item.getDamageValue() >= 100) context.getPlayer().getInventory().removeItem(item);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        pTooltipComponents.add(1, Component.translatable(MODID + ".pencil.message." + pStack.getTag().getInt("glyph")));
    }
}