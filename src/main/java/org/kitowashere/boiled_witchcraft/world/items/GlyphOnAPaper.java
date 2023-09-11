package org.kitowashere.boiled_witchcraft.world.items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphType;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.GlyphMagic;

import java.util.List;
import java.util.Optional;

import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.GLYPH_REGISTRY;
import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.translatableName;

public class GlyphOnAPaper extends Item {
    public GlyphOnAPaper() { super(new Properties()); }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (!pLevel.isClientSide() && !pPlayer.isShiftKeyDown()) {
            getGlyphMagic(pPlayer.getItemInHand(pUsedHand)).ifPresent(magic -> {
                magic.useOnPaper((ServerLevel) pLevel, pPlayer, 2);
                pPlayer.getItemInHand(pUsedHand).shrink(1);

                pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 40);
            });
        }

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide() && pContext.getPlayer()!=null && !pContext.getPlayer().isShiftKeyDown()) {
            Level level = pContext.getLevel();
            BlockPos pos = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            ItemStack item = pContext.getItemInHand();

            CompoundTag nbt =  pContext.getItemInHand().getTag();

            if (level.getBlockState(pos).isSolidRender(level, pos)) {
                getGlyphMagic(item).ifPresent(magic -> {
                    magic.applyOnSurface(level, pos.mutable().move(pContext.getClickedFace()), pContext.getClickedFace());
                    item.shrink(1);

                    player.getCooldowns().addCooldown(item.getItem(), 40);
                });
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        getGlyphType(pStack).ifPresent(glyphType -> pTooltipComponents.add(1, translatableName(glyphType)));
    }

    public static Optional<GlyphType> getGlyphType(ItemStack stack) {
        CompoundTag nbt = stack.getTag();

        if (nbt != null && nbt.contains("glyph")) {
            return Optional.ofNullable(GLYPH_REGISTRY.get().getValue(ResourceLocation.of(nbt.getString("glyph"), ':')));
        } else {
            return Optional.empty();
        }
    }

    public static Optional<GlyphMagic> getGlyphMagic(ItemStack stack) {
        Optional<GlyphType> glyph = getGlyphType(stack);
        GlyphMagic magic = null;

        CompoundTag nbt = stack.getTag();
        if (glyph.isPresent() && nbt != null && nbt.contains("contexts")) {
            magic = glyph.get().newMagic();
            magic.deserializeNBT((CompoundTag) nbt.get("contexts"));
        }
        return Optional.ofNullable(magic);
    }
}