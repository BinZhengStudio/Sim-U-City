package com.github.vvvbbbcz.simucity.client.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScreenHandler {
	public static void openCityBoxScreen(PlayerEntity player) {
		Minecraft.getInstance().displayGuiScreen(new CityBoxScreen(player));
	}
}
