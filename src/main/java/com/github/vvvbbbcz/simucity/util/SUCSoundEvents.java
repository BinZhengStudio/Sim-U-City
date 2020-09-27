package com.github.vvvbbbcz.simucity.util;

import com.github.vvvbbbcz.simucity.SimUCity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SUCSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, SimUCity.MODID);
	public static final SoundEvent COMPUTER = register("computer", new SoundEvent(new ResourceLocation(SimUCity.MODID, "computer")));
	public static final SoundEvent CITY_BOX_ACTIVATED = register("city_box_activated", new SoundEvent(new ResourceLocation(SimUCity.MODID, "city_box_activated")));

	private static SoundEvent register(String name, SoundEvent soundEvent) {
		SOUND_EVENTS.register(name, () -> soundEvent);
		return soundEvent;
	}
}
