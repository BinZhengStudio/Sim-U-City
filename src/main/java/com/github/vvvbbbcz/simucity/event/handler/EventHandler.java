package com.github.vvvbbbcz.simucity.event.handler;

import com.github.vvvbbbcz.simucity.world.storage.WorldData;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {
//	public static void onPlayerJoin(final EntityJoinWorldEvent event) {
//		if (event.getEntity() instanceof PlayerEntity) {
//			PlayerEntity player = (PlayerEntity) event.getEntity();
//			if (player.world.isRemote) {
//				Minecraft.getInstance().displayGuiScreen(new RunModScreen(player.getEntityWorld()));
//			}
//		}
//	}

	@SubscribeEvent
	public static void onWorldLoad(final WorldEvent.Load event) {
		if (event.getWorld() instanceof ServerWorld) {
			WorldData.setWorld((ServerWorld) event.getWorld());
		}
	}
}
