package org.kitowashere.boiled_witchcraft.core;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.phys.Vec3;

import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.TFM;

public interface ThrowableMagic {
    void execute(ServerLevel level, BlockPos pos, Vec3 dir, float vel);

    static void fire(ServerLevel level, BlockPos pos, Vec3 dir, float vel) {
        TFM.get().create(level, null, null, pos, MobSpawnType.EVENT, false, false).shoot(dir.x, dir.y, dir.z, vel, 0);
    }
}
