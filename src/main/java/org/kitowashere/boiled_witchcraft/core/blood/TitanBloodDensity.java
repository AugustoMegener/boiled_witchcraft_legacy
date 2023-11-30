package org.kitowashere.boiled_witchcraft.core.blood;


import net.minecraft.world.level.chunk.LevelChunk;

public class TitanBloodDensity implements ITitanBloodHandler {

    private int bloodAmount = 0;
    private final LevelChunk chunk;

    public TitanBloodDensity(LevelChunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public int getAmount() {
        return bloodAmount;
    }

    @Override
    public void set(int value) {
        bloodAmount = value;
        chunk.setUnsaved(true);
    }
}
