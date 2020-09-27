package com.github.vvvbbbcz.simucity.network;

import com.github.vvvbbbcz.simucity.SimUCity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {
	private static int id = 0;
	private static final String VERSION = "1.0";
	public static final SimpleChannel GAME_MODE = NetworkRegistry.newSimpleChannel(new ResourceLocation(SimUCity.MODID, "game_mode"), () -> VERSION, VERSION::equals, VERSION::equals);

	public static void register() {
		GAME_MODE.registerMessage(id, GameModePack.class, GameModePack::toBytes, GameModePack::new, GameModePack::handler);
	}
}
