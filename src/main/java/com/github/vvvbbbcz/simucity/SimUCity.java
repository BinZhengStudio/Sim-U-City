package com.github.vvvbbbcz.simucity;

import com.github.vvvbbbcz.simucity.block.SUCBlocks;
import com.github.vvvbbbcz.simucity.entity.SUCEntityType;
import com.github.vvvbbbcz.simucity.item.SUCItems;
import com.github.vvvbbbcz.simucity.network.Networking;
import com.github.vvvbbbcz.simucity.world.building.Building;
import com.github.vvvbbbcz.simucity.world.building.Reader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SimUCity.MODID)
public class SimUCity {
	public static final String MODID = "simucity";
	public static final Logger LOGGER = LogManager.getLogger();

	public SimUCity() {
		SUCBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		SUCItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		SUCEntityType.ENTITY_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onServerStarting);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		Networking.register();
		Reader.readAll();
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
	}

	private void onServerStarting(final FMLServerStartingEvent event) {
	}
}
