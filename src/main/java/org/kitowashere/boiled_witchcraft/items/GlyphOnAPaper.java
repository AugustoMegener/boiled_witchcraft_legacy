package org.kitowashere.boiled_witchcraft.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.kitowashere.boiled_witchcraft.core.GlyphType;

public class GlyphOnAPaper extends Item {
    public GlyphOnAPaper() { super(new Properties()); }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        ItemStack item = pContext.getItemInHand();
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();

        if (level.getBlockState(pos).isSolidRender(level, pos) && !pContext.getPlayer().isShiftKeyDown()) {
            GlyphType.values()[item.getTag().getInt("glyph")].doMagic(level, pos.above());
            pContext.getItemInHand().shrink(1);
        }

        return super.useOn(pContext);
    }
}