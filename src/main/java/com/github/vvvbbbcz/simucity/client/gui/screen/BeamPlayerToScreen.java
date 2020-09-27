package com.github.vvvbbbcz.simucity.client.gui.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BeamPlayerToScreen extends Screen {
	private PlayerEntity player;

	protected BeamPlayerToScreen(PlayerEntity player) {
		super(new StringTextComponent("beam_player_to"));
		this.player = player;
	}

	@Override
	protected void init() {
		this.buttons.clear();
		this.buttons.add(new Button(5, 5, 50, 20, I18n.format("button.beam_player_to.cancel"), (press) -> {
			this.getMinecraft().displayGuiScreen((Screen) null);
		}));
	}
}
