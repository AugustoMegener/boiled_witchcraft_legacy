package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;

public enum GlyphType implements StringRepresentable {
    FIRE("fire",  GroundedMagic::fire), ICE("ice", GroundedMagic::ice),
    LIGHT("light",  GroundedMagic::light), PLANT("plant", GroundedMagic::plant);

    private final String NAME;
    private final GroundedMagic ACTION;

    GlyphType(String name, GroundedMagic action) {
        NAME = name;
        ACTION = action;
    }

    @Override
    public String getSerializedName() { return NAME; }

    public void doMagic(Level level, BlockPos pos) { ACTION.execute(level, pos); }
}
