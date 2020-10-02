package com.github.vvvbbbcz.simucity.network;

import com.github.vvvbbbcz.simucity.SimUCity;
import com.github.vvvbbbcz.simucity.world.storage.WorldData;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerCreditsPack {
	private final int credits;

	public PlayerCreditsPack(PacketBuffer buffer) {
		this.credits = buffer.readInt();
	}

	public PlayerCreditsPack(int	credits) {
		this.credits = credits;
		SimUCity.LOGGER.info("Sent a PlayerCredits pack.");
	}

	public void toBytes(PacketBuffer buffer) {
		buffer.writeInt(this.credits);
	}

	public void handler(Supplier<NetworkEvent.Context> sup) {
		SimUCity.LOGGER.info("Processing a PlayerCredits pack.");
		sup.get().enqueueWork(() -> WorldData.playerCredits.setCredits(this.credits, false));
		sup.get().setPacketHandled(true);
		SimUCity.LOGGER.info("Processed a PlayerCredits pack.");
	}
}
