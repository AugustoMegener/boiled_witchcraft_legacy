package org.kitowashere.boiled_witchcraft.core;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.phys.Vec3;
import org.kitowashere.boiled_witchcraft.entities.MagicFireBallEntity;

import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.TFM;

public interface ThrowableMagic {
    void execute(ServerLevel level, BlockPos pos, Vec3 dir, float vel);

    static void fire(ServerLevel level, BlockPos pos, Vec3 dir, float vel) {
        MagicFireBallEntity fireBallEntity = TFM.get().create(level, null, null, pos, MobSpawnType.EVENT, false, false);
        level.addFreshEntity(fireBallEntity);
        fireBallEntity.shoot(dir.x, dir.y, dir.z, vel, 0);
    }
}
