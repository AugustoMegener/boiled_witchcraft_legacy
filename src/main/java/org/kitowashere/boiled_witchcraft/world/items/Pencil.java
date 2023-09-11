package org.kitowashere.boiled_witchcraft.world.items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphType;
import org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry;
import org.kitowashere.boiled_witchcraft.world.blocks.GlyphBlock;
import org.kitowashere.boiled_witchcraft.world.player.capabilities.gctx.PlayerGCTXProvider;

import static net.minecraft.world.InteractionHand.MAIN_HAND;
import static net.minecraft.world.InteractionHand.OFF_HAND;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING;
import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.GLYPH_REGISTRY;
import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.GLYPH_ON_A_PAPER;

public class Pencil extends Item  {

    public Pencil() {
        super(new Properties().durability(100));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack offItem = pPlayer.getItemInHand(pUsedHand==MAIN_HAND ? OFF_HAND : MAIN_HAND);

        pPlayer.getCapability(PlayerGCTXProvider.PLAYER_CONTEXT).ifPresent(ctx -> {
            if (!pLevel.isClientSide() && offItem.getItem().getDescriptionId().equals("item.minecraft.paper")) {
                offItem.shrink(1);

                ItemStack paper = GLYPH_ON_A_PAPER.get().getDefaultInstance();

                CompoundTag nbt = new CompoundTag();
                ctx.saveNBTData(nbt);

                paper.setTag(nbt);

                pPlayer.getInventory().add(paper);
                pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 40);
            }
        });

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();

        if (player!=null && !context.getLevel().isClientSide() && !player.isShiftKeyDown()) {
            player.getCapability(PlayerGCTXProvider.PLAYER_CONTEXT).ifPresent(ctx -> {
                CompoundTag nbt = new CompoundTag();
                ctx.saveNBTData(nbt);

                DrawnGlyph(context, nbt);
                player.getCooldowns().addCooldown(context.getItemInHand().getItem(), 40);

                context.getItemInHand().hurtAndBreak(1, player, x -> {});
            });
        }

        return InteractionResult.SUCCESS;
    }

    private void DrawnGlyph(UseOnContext context, CompoundTag nbt) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().mutable().move(context.getClickedFace());

        if (level.isEmptyBlock(pos) && level.getBlockState(context.getClickedPos()).isSolidRender(level, context.getClickedPos())) {
            GlyphType glyph = GLYPH_REGISTRY.get().getValue(ResourceLocation.of(nbt.getString("glyph"), ':'));

            if (glyph != null) {
                GlyphTypeRegistry.getGlyphBlock(glyph).ifPresent(blockReference -> {
                    BlockState glyphBlockState = blockReference.get().defaultBlockState().setValue(FACING, context.getClickedFace()); //

                    level.setBlock(pos, glyphBlockState, 0);
                    GlyphBlock.setGlyphCTX(level, pos, nbt);
                });
            }
        }
    }
}