package com.github.vvvbbbcz.simucity.network;

import com.github.vvvbbbcz.simucity.SimUCity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {
	private static int id = 0;
	private static final String VERSION = "1.0";
	public static final SimpleChannel WORLD_STATE = NetworkRegistry.newSimpleChannel(new ResourceLocation(SimUCity.MODID, "world_state"), () -> VERSION, VERSION::equals, VERSION::equals);
	public static final SimpleChannel PLAYER_CREDITS = NetworkRegistry.newSimpleChannel(new ResourceLocation(SimUCity.MODID, "player_credits"), () -> VERSION, VERSION::equals, VERSION::equals);

	public static void register() {
		WORLD_STATE.registerMessage(id, WorldStatePack.class, WorldStatePack::toBytes, WorldStatePack::new, WorldStatePack::handler);
		PLAYER_CREDITS.registerMessage(id++, PlayerCreditsPack.class, PlayerCreditsPack::toBytes, PlayerCreditsPack::new, PlayerCreditsPack::handler);
	}
}
