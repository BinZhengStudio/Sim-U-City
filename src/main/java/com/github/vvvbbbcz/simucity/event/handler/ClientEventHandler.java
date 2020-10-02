package com.github.vvvbbbcz.simucity.event.handler;

import com.github.vvvbbbcz.simucity.client.gui.WorldStateGui;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientEventHandler {
	@SubscribeEvent
	public static void onOverlayRender(RenderGameOverlayEvent event) {
		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
			return;
		}

		WorldStateGui worldStateGui = new WorldStateGui();
		worldStateGui.render();
	}
}
