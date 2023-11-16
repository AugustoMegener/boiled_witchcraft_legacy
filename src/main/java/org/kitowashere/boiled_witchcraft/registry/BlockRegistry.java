package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.world.blocks.*;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.FluidRegistry.SOURCE_DTB;
import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.ITEMS;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> FIRE_GLYPH_BLOCK = BLOCKS.register("fire_glyph_block", GlyphBlock::new);
    public static final RegistryObject<Block> ICE_GLYPH_BLOCK = BLOCKS.register("ice_glyph_block", GlyphBlock::new);
    public static final RegistryObject<Block> LIGHT_GLYPH_BLOCK = BLOCKS.register("light_glyph_block", GlyphBlock::new);
    public static final RegistryObject<Block> PLANT_GLYPH_BLOCK = BLOCKS.register("plant_glyph_block", GlyphBlock::new);

    public static final RegistryObject<Block> SFM = BLOCKS.register("surfaced_fire_magic", SurfacedFireMagic::new);
    public static final RegistryObject<Block> SIM = BLOCKS.register("surfaced_ice_magic", SurfacedIceMagic::new);
    public static final RegistryObject<Block> SPM = BLOCKS.register("surfaced_plant_magic", SurfacedPlantMagic::new);
    public static final RegistryObject<Block> SLM = BLOCKS.register("surfaced_light_magic", SurfacedLightMagic::new);
    public static final RegistryObject<Block> SPRAYER = BLOCKS.register("sprayer", Sprayer::new);
    public static final RegistryObject<BlockItem> SPRAYER_ITEM = ITEMS.register("sprayer",
            () -> new BlockItem(SPRAYER.get(), new Item.Properties())
    );

    public static final RegistryObject<LiquidBlock> DTB_BLOCK = BLOCKS.register("diluted_titan_blood", () -> new LiquidBlock(
            SOURCE_DTB, BlockBehaviour.Properties.copy(Blocks.WATER)
    ));
}
