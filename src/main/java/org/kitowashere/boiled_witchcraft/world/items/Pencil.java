package org.kitowashere.boiled_witchcraft.world.items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kitowashere.boiled_witchcraft.client.ClientGCTX;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.GlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphType;
import org.kitowashere.boiled_witchcraft.networking.ModMessages;
import org.kitowashere.boiled_witchcraft.networking.packet.GCTXPacket;
import org.kitowashere.boiled_witchcraft.world.blocks.GlyphBlock;
import org.kitowashere.boiled_witchcraft.world.player.capabilities.gctx.PlayerGCTXProvider;

import java.util.List;

import static net.minecraft.world.InteractionHand.MAIN_HAND;
import static net.minecraft.world.InteractionHand.OFF_HAND;
import static org.kitowashere.boiled_witchcraft.core.glyph.GlyphType.GLYPH_REGISTRY;
import static org.kitowashere.boiled_witchcraft.core.glyph.GlyphTypeProperty.PRIMAL;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.GLYPH_BLOCK;
import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.GLYPH_ON_A_PAPER;

public class Pencil extends Item  {
    public Pencil() {
        super(new Properties().durability(100));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack offItem = pPlayer.getItemInHand(pUsedHand==MAIN_HAND ? OFF_HAND : MAIN_HAND);

        pPlayer.getCapability(PlayerGCTXProvider.PLAYER_CONTEXT).ifPresent(ctx -> {
            if (!pLevel.isClientSide()) {
                if (pPlayer.isShiftKeyDown()) {
                    GlyphType next = ctx.getSelectedGlyph().next(0, 3, 1);

                    ctx.setSelectedGlyph(next);
                    ModMessages.sendToPlayer(new GCTXPacket(next), (ServerPlayer) pPlayer);

                    pPlayer.displayClientMessage(ctx.getSelectedGlyph().translatableName(), true);

                } else if (pPlayer.isSprinting()) {
                    GlyphMagic glyphMagic = ctx.getGlyphMagic();

                    glyphMagic.CONTEXT_KIT[0].configure();
                    ModMessages.sendToPlayer(new GCTXPacket(ctx.getSelectedGlyph(), glyphMagic), (ServerPlayer) pPlayer);

                } if (offItem.getItem().getDescriptionId().equals("item.minecraft.paper")) {
                    offItem.shrink(1);

                    ItemStack paper = GLYPH_ON_A_PAPER.get().getDefaultInstance();

                    CompoundTag nbt = new CompoundTag();
                    ctx.saveNBTData(nbt);

                    paper.setTag(nbt);

                    pPlayer.getInventory().add(paper);
                    pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 40);
                }
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
        BlockPos pos = context.getClickedPos().above();

        if (level.isEmptyBlock(pos) && level.getBlockState(context.getClickedPos()).isSolidRender(level, context.getClickedPos())) {
            GlyphType glyph = GLYPH_REGISTRY.getValue(ResourceLocation.of(nbt.getString("glyph"), ':'));

            BlockState glyphBlockState = GLYPH_BLOCK.get().defaultBlockState().setValue(PRIMAL, glyph!=null ? glyph : PrimalGlyph.FIRE);

            level.setBlock(pos, glyphBlockState, 0);
            GlyphBlock.setGlyphCTX(level, pos, nbt);
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        pTooltipComponents.add(1, ClientGCTX.getSelectedGlyph().translatableName());
    }
}