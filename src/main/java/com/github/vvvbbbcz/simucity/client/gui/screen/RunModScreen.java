package com.github.vvvbbbcz.simucity.client.gui.screen;

import com.github.vvvbbbcz.simucity.network.WorldStatePack;
import com.github.vvvbbbcz.simucity.network.Networking;
import com.github.vvvbbbcz.simucity.world.GameMode;
import com.github.vvvbbbcz.simucity.world.storage.WorldData;
import com.github.vvvbbbcz.simucity.world.storage.WorldState;
import com.google.gson.Gson;
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
		Gson gson = new Gson();

		this.addButton(new Button((width / 2 - 75), this.height / 2 - 70, 150, 20, I18n.format("button.run_mod.do_not_run"), (press) -> {
			Networking.WORLD_STATE.sendToServer(new WorldStatePack(gson.toJson(new WorldState(GameMode.DO_NOT_RUN, -1))));
			WorldData.state = new WorldState(GameMode.DO_NOT_RUN, -1);
			this.getMinecraft().displayGuiScreen((Screen) null);
		}));

		this.addButton(new Button((width / 2 - 75), this.height / 2 - 20, 150, 20, I18n.format("button.run_mod.normal"), (press) -> {
			Networking.WORLD_STATE.sendToServer(new WorldStatePack(gson.toJson(new WorldState(GameMode.NORMAL, 1))));
			WorldData.state = new WorldState(GameMode.NORMAL, 1);
			this.getMinecraft().displayGuiScreen((Screen) null);
		}));

		this.addButton(new Button((width / 2 - 75), this.height / 2 + 30, 150, 20, I18n.format("button.run_mod.creative"), (press) -> {
			Networking.WORLD_STATE.sendToServer(new WorldStatePack(gson.toJson(new WorldState(GameMode.CREATIVE, 1))));
			WorldData.state = new WorldState(GameMode.CREATIVE, 1);
			this.getMinecraft().displayGuiScreen((Screen) null);
		}));

		this.addButton(new Button((width / 2 - 75), this.height / 2 + 80, 150, 20, I18n.format("button.run_mod.difficult"), (press) -> {
			Networking.WORLD_STATE.sendToServer(new WorldStatePack(gson.toJson(new WorldState(GameMode.DIFFICULT, 1))));
			WorldData.state = new WorldState(GameMode.DIFFICULT, 1);
			this.getMinecraft().displayGuiScreen((Screen) null);
		}));
	}

	@Override
	public void render(int mouseX, int mouseY, float particleTick) {
		this.renderBackground();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawCenteredString(this.font, I18n.format("text.run_mod.title"), width / 2, 20, 0xffffff);
		this.drawCenteredString(this.font, I18n.format("text.run_mod.do_not_run_description"), width / 2, this.height / 2 - 48, 0xffff00);
		this.drawCenteredString(this.font, I18n.format("text.run_mod.normal_description"), width / 2, this.height / 2 + 2, 0xffff00);
		this.drawCenteredString(this.font, I18n.format("text.run_mod.creative_description"), width / 2, this.height / 2 + 52, 0xffff00);
		this.drawCenteredString(this.font, I18n.format("text.run_mod.difficult_description"), width / 2, this.height / 2 + 102, 0xffff00);
		super.render(mouseX, mouseY, particleTick);
	}

	@Override
	public boolean isPauseScreen() {
		return this.pause;
	}
}
