package org.kitowashere.boiled_witchcraft.items;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.kitowashere.boiled_witchcraft.core.GlyphType;

import java.util.List;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class GlyphOnAPaper extends Item {
    public GlyphOnAPaper() { super(new Properties()); }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            Level level = pContext.getLevel();
            BlockPos pos = pContext.getClickedPos();

            if (level.getBlockState(pos).isSolidRender(level, pos) && !pContext.getPlayer().isShiftKeyDown()) {
                GlyphType.values()[pContext.getItemInHand().getTag().getInt("glyph")].doMagic(level, pos.above());
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