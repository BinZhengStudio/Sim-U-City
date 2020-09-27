package com.github.vvvbbbcz.simucity.network;

import com.github.vvvbbbcz.simucity.world.GameMode;
import com.github.vvvbbbcz.simucity.world.storage.WorldState;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class GameModePack {
	private final GameMode message;

	public GameModePack(PacketBuffer buffer) {
		message = buffer.readEnumValue(GameMode.class);
	}

	public GameModePack(GameMode gameMode) {
		this.message = gameMode;
	}

	public void toBytes(PacketBuffer buffer) {
		buffer.writeEnumValue(this.message);
	}

	public void handler(Supplier<NetworkEvent.Context> sup) {
		sup.get().enqueueWork(() -> WorldState.setGameMode(this.message));
		sup.get().setPacketHandled(true);
	}
}
