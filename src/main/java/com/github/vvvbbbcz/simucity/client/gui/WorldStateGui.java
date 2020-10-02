package com.github.vvvbbbcz.simucity.client.gui;

import com.github.vvvbbbcz.simucity.world.GameMode;
import com.github.vvvbbbcz.simucity.world.storage.WorldData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WorldStateGui extends AbstractGui {
	public WorldStateGui() {
	}

	public void render() {
		if (WorldData.state.getGamemode() == GameMode.NOT_ASKED || WorldData.state.getGamemode() == GameMode.DO_NOT_RUN) {
			return;
		}

		if (!Minecraft.getInstance().gameSettings.showDebugInfo) {
			// Get the world name.
			String worldName = "unknown";
			if (Minecraft.getInstance().getIntegratedServer() != null) {
				worldName = Minecraft.getInstance().getIntegratedServer().getWorldName();
			}

			String population = I18n.format("text.hud.population");
			String credits = I18n.format("text.hud.credits");

			if (WorldData.state.getGamemode() == GameMode.CREATIVE) {
				this.drawString(Minecraft.getInstance().fontRenderer, worldName + "    " + WorldData.state.getDayOfWeek() + "    " + population/* + SimukraftReloaded.theFolks.size()*/, 2, 2, 0xFFFFFF);
			} else {
				this.drawString(Minecraft.getInstance().fontRenderer, worldName + "    " + WorldData.state.getDayOfWeek() + "    " + population/* + SimukraftReloaded.theFolks.size()*/ + "    " + credits + WorldData.playerCredits.getCredits(), 2, 2, 0xFFFFFF);
			}
		}
	}
}