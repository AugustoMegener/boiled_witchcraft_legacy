package org.kitowashere.boiled_witchcraft.core.glyph.magic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.common.util.INBTSerializable;
import org.kitowashere.boiled_witchcraft.core.glyph.context.GlyphContext;
import org.kitowashere.boiled_witchcraft.world.entities.ThrowableMagicEntity;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING;

public abstract class GlyphMagic implements INBTSerializable<CompoundTag> {
    public final GlyphContext[] CONTEXT_KIT;

    public GlyphMagic() { CONTEXT_KIT = newContextKit(); }

    public abstract GlyphContext[] newContextKit();

    public abstract void glyphTouched(BlockState state, Level level, BlockPos pos, Entity entity, Direction surface);

    public abstract void applyOnSurface(Level level, BlockPos pos, Direction surface);

    public abstract void useOnPaper(ServerLevel level, LivingEntity shooter, float vel);

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();

        for (int i = 0; i < CONTEXT_KIT.length; i++) {
            nbt.put(""+i, CONTEXT_KIT[i].serializeNBT());
        }

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        for (int i = 0; i < CONTEXT_KIT.length; i++) {
            CONTEXT_KIT[i].deserializeNBT((CompoundTag) nbt.get(""+i));
        }
    }

    public void fromBytes(FriendlyByteBuf buf) {
        for (GlyphContext i : CONTEXT_KIT) { i.fromBytes(buf); }
    }

    public void toBytes(FriendlyByteBuf buf) {
        for (GlyphContext i : CONTEXT_KIT) { i.toBytes(buf); }
    }

    protected void makePillar(Block block, int range, Level level, BlockPos pos, Direction surface) {
        BlockPos.MutableBlockPos blockPos = pos.mutable();

        for (int i = 0; i < range; i++) {
            level.setBlock(blockPos, block.defaultBlockState().setValue(FACING, surface), UPDATE_ALL);
            blockPos.move(surface);
        }
    }

    protected void makePillar(Block block, int range, Level level, BlockPos pos, Direction surface, IntegerProperty transitionProperty, int transitionRange) {
        BlockPos.MutableBlockPos blockPos = pos.mutable();

        for (int i = 0; i < range-transitionRange; i++) {
            level.setBlock(blockPos, block.defaultBlockState().setValue(FACING, surface).setValue(transitionProperty, 0), UPDATE_ALL);
            blockPos.move(surface);
        }

        for (int i =  Math.max(transitionRange-range, 0); i < transitionRange; i++) {
            level.setBlock(blockPos, block.defaultBlockState().setValue(FACING, surface).setValue(transitionProperty, i), UPDATE_ALL);
            blockPos.move(surface);
        }
    }

    protected void shootProjectile(ThrowableMagicEntity projectile, ServerLevel level, LivingEntity shooter, float vel) {
        projectile.setMagic(this);

        projectile.setPos(shooter.getX(), shooter.getEyeY() - (double)0.1F, shooter.getZ());
        projectile.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, vel, 1.0F);

        level.addFreshEntity(projectile);
    }
}
