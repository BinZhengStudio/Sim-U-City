package com.github.vvvbbbcz.simucity.client.gui.screen;

import com.github.vvvbbbcz.simucity.network.GameModePack;
import com.github.vvvbbbcz.simucity.network.Networking;
import com.github.vvvbbbcz.simucity.world.GameMode;
import com.github.vvvbbbcz.simucity.world.storage.WorldState;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RunModScreen extends Screen {
	private final boolean pause;

	public RunModScreen(boolean pause) {
		super(new StringTextComponent("run_mod"));
		this.pause = pause;
	}

	@Override
	public void init() {
		this.addButton(new Button((width / 2 - 75), 40, 150, 20, I18n.format("button.run_mod.do_not_run"), (press) -> {
			Networking.GAME_MODE.sendToServer(new GameModePack(GameMode.DO_NOT_RUN));
			this.getMinecraft().displayGuiScreen((Screen) null);
		}));

		this.addButton(new Button((width / 2 - 75), 90, 150, 20, I18n.format("button.run_mod.normal"), (press) -> {
			Networking.GAME_MODE.sendToServer(new GameModePack(GameMode.NORMAL));
			this.getMinecraft().displayGuiScreen((Screen) null);
		}));

		this.addButton(new Button((width / 2 - 75), 140, 150, 20, I18n.format("button.run_mod.creative"), (press) -> {
			Networking.GAME_MODE.sendToServer(new GameModePack(GameMode.CREATIVE));
			this.getMinecraft().displayGuiScreen((Screen) null);
		}));

		this.addButton(new Button((width / 2 - 75), 190, 150, 20, I18n.format("button.run_mod.hardcore"), (press) -> {
			Networking.GAME_MODE.sendToServer(new GameModePack(GameMode.HARDCORE));
			this.getMinecraft().displayGuiScreen((Screen) null);
		}));
	}

	@Override
	public void render(int mouseX, int mouseY, float particleTick) {
		this.renderBackground();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawCenteredString(this.font, I18n.format("text.run_mod.title"), width / 2, 20, 0xffffff);
		this.drawCenteredString(this.font, I18n.format("text.run_mod.do_not_run_description"), width / 2, 60, 0xffff00);
		this.drawCenteredString(this.font, I18n.format("text.run_mod.normal_description"), width / 2, 110, 0xffff00);
		this.drawCenteredString(this.font, I18n.format("text.run_mod.creative_description"), width / 2, 160, 0xffff00);
		this.drawCenteredString(this.font, I18n.format("text.run_mod.hardcore_description"), width / 2, 210, 0xffff00);
		super.render(mouseX, mouseY, particleTick);
	}

	@Override
	public boolean isPauseScreen() {
		return this.pause;
	}
}
