package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.world.blocks.*;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> GLYPH_BLOCK = BLOCKS.register("glyph_block", GlyphBlock::new);

    public static final RegistryObject<Block> SFM = BLOCKS.register("surfaced_fire_magic", SurfacedFireMagic::new);

    public static final RegistryObject<Block> SIM = BLOCKS.register("surfaced_ice_magic", SurfacedIceMagic::new);

    public static final RegistryObject<Block> SPM = BLOCKS.register("surfaced_plant_magic", SurfacedPlantMagic::new);

    public static final RegistryObject<Block> SLM = BLOCKS.register("surfaced_light_magic", SurfacedLightMagic::new);
}
