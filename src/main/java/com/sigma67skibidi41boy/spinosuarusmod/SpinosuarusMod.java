package com.sigma67skibidi41boy.spinosuarusmod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpinosuarusMod implements ModInitializer {
    public static final String MODID = "spinosuarusmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<EntityType<Spinosuarus>> SPINOSUARUS = ENTITY_TYPES.register("spinosuarus", () -> EntityType.Builder.of(Spinosuarus::new, MobCategory.WATER_CREATURE)
            .sized(1.0f, 1.0f)
            .build(new ResourceLocation(MODID, "spinosuarus").toString()));

    public static final RegistryObject<Item> SPINOSUARUS_SPAWN_EGG = ITEMS.register("spinosuarus_spawn_egg", () -> new ForgeSpawnEggItem(SPINOSUARUS, 0x8B4513, 0xFFFFFF, new Item.Properties()));

    @Override
    public void onInitialize() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITY_TYPES.register(eventBus);
        ITEMS.register(eventBus);

        SpawnPlacements.register(SPINOSUARUS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);

        LOGGER.info("Spinosuarus Mod initialized");
    }
}