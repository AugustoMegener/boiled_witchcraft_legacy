package org.kitowashere.boiled_witchcraft.world.items;

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
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.GlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphType;

import java.util.List;

import static net.minecraft.core.Direction.UP;
import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.FIRE_GLYPH;

public class GlyphOnAPaper extends Item {
    public GlyphOnAPaper() { super(new Properties()); }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        CompoundTag nbt = pPlayer.getItemInHand(pUsedHand).getTag();

        if (!pLevel.isClientSide() && pPlayer.isShiftKeyDown() && nbt != null && nbt.contains("GLYPH_TYPES")) {
            GlyphType glyphType = GlyphType.fromIndex(nbt.getInt("GLYPH_TYPES"));

            GlyphMagic magic = glyphType != null ? glyphType.newMagic() : FIRE_GLYPH.newMagic();
            magic.deserializeNBT((CompoundTag) nbt.get("contexts"));

            magic.useOnPaper((ServerLevel) pLevel, pPlayer, 2);
            pPlayer.getItemInHand(pUsedHand).shrink(1);

            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 40);
        }

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide() && pContext.getPlayer()!=null && !pContext.getPlayer().isShiftKeyDown()) {
            Level level = pContext.getLevel();
            BlockPos pos = pContext.getClickedPos();

            CompoundTag nbt =  pContext.getItemInHand().getTag();

            if (level.getBlockState(pos).isSolidRender(level, pos) && nbt != null && nbt.contains("GLYPH_TYPES") && GlyphType.fromIndex(nbt.getInt("GLYPH_TYPES"))!=null) {
                GlyphType glyphType = GlyphType.fromIndex(nbt.getInt("GLYPH_TYPES"));

                GlyphMagic magic = glyphType != null ? glyphType.newMagic() : FIRE_GLYPH.newMagic();
                magic.deserializeNBT((CompoundTag) nbt.get("contexts"));

                magic.applyOnSurface(level, pos.above(), UP);
                pContext.getItemInHand().shrink(1);

                pContext.getPlayer().getCooldowns().addCooldown(pContext.getItemInHand().getItem(), 40);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        if (pStack.getTag() != null && pStack.getTag().contains("GLYPH_TYPES")) {
            pTooltipComponents.add(1, Component.translatable(MODID + ".pencil.message." + pStack.getTag().getInt("GLYPH_TYPES")));
        }
    }
}