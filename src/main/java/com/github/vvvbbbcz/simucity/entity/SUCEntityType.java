package com.github.vvvbbbcz.simucity.entity;

import com.github.vvvbbbcz.simucity.SimUCity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SUCEntityType {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = new DeferredRegister<>(ForgeRegistries.ENTITIES, SimUCity.MODID);
	public static final EntityType<CitizenEntity> CITIZEN = register("citizen", EntityType.Builder.create(CitizenEntity::new, EntityClassification.CREATURE).size(0.6F, 1.8F).build("citizen"));

	private static <T extends Entity> EntityType<T> register(String name, EntityType<T> type) {
		ENTITY_TYPE.register(name, () -> type);
		return type;
	}
}
