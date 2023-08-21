package org.kitowashere.boiled_witchcraft.items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.kitowashere.boiled_witchcraft.core.GlyphType;

import java.util.List;
import java.util.Objects;

import static net.minecraft.core.Direction.UP;
import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class GlyphOnAPaper extends Item {
    public GlyphOnAPaper() { super(new Properties()); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        CompoundTag nbt = pPlayer.getItemInHand(pUsedHand).getTag();

        if (!pLevel.isClientSide() && pPlayer.isShiftKeyDown() && nbt != null && nbt.contains("glyph")) {

            GlyphType.fromIndex(nbt.getInt("glyph")).trowMagic((ServerLevel) pLevel, pPlayer.getOnPos(), pPlayer.getLookAngle(), 10);
        }

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            Level level = pContext.getLevel();
            BlockPos pos = pContext.getClickedPos();

            if (level.getBlockState(pos).isSolidRender(level, pos) && !pContext.getPlayer().isShiftKeyDown()) {
                GlyphType.fromIndex(pContext.getItemInHand().getTag().getInt("glyph")).doMagicInSurface(level, pos.above(), UP);
                pContext.getItemInHand().shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        pTooltipComponents.add(1, Component.translatable(MODID + ".pencil.message." + pStack.getTag().getInt("glyph")));
    }
}