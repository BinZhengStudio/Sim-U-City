package com.github.vvvbbbcz.simucity.network;

import com.github.vvvbbbcz.simucity.SimUCity;
import com.github.vvvbbbcz.simucity.world.storage.WorldData;
import com.github.vvvbbbcz.simucity.world.storage.WorldState;
import com.google.gson.Gson;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class WorldStatePack {
	private final String worldState;

	public WorldStatePack(PacketBuffer buffer) {
		this.worldState = buffer.readString(32767);
	}

	public WorldStatePack(String worldState) {
		this.worldState = worldState;
		SimUCity.LOGGER.info("Sent a WorldState pack.");
	}

	public void toBytes(PacketBuffer buffer) {
		buffer.writeString(this.worldState);
	}

	public void handler(Supplier<NetworkEvent.Context> sup) {
		SimUCity.LOGGER.info("Processing a WorldState pack.");
		sup.get().enqueueWork(() -> WorldData.state = new Gson().fromJson(this.worldState, WorldState.class));
		sup.get().setPacketHandled(true);
		SimUCity.LOGGER.info("Processed a WorldState pack.");
	}
}
