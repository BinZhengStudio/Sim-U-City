package com.github.vvvbbbcz.simucity.client.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScreenHandler {
	public static void openCityBoxScreen() {
		Minecraft.getInstance().displayGuiScreen(new CityBoxScreen());
	}
}
