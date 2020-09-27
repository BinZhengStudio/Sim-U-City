package com.github.vvvbbbcz.simucity.client.gui.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CityBoxScreen extends Screen {
	private boolean isSetup = false;
	private PlayerEntity player;
	private Button done, choose_building, hire_builders, fire_builders, planning_area, hire_planners, create_new_city, show_employees;

	public CityBoxScreen(PlayerEntity player) {
		super(new StringTextComponent("city_box"));
		this.player = player;
	}

	@Override
	protected void init() {
		this.buttons.clear();
		this.addButton(this.done = new Button(2, 12, 50, 20, I18n.format("button.city_box.done"), (press) -> {
			this.getMinecraft().displayGuiScreen((Screen) null);
		}));

		if (isSetup) {
			this.addButton(this.choose_building = new Button(this.width / 2 - 60, 150, 120, 20, I18n.format("button.city_box.choose_building"), (press) -> {

			}));

			this.addButton(this.hire_builders = new Button(this.width / 2 - 180, 150, 120, 20, I18n.format("button.city_box.hire_builders"), (press) -> {

			}));

			this.addButton(this.fire_builders = new Button(this.width / 2 + 60, 150, 120, 20, I18n.format("button.city_box.fire_builders"), (press) -> {

			}));

			this.addButton(this.planning_area = new Button(this.width / 2 - 60, 170, 120, 20, I18n.format("button.city_box.planning_area"), (press) -> {

			}));

			this.addButton(this.hire_planners = new Button((width / 2) - 180, 170, 120, 20, I18n.format("button.city_box.hire_planners"), (press) -> {

			}));

			this.addButton(this.show_employees = new Button(this.width / 2 + 60, 170, 120, 20, I18n.format("button.city_box.show_employees"), (press) -> {

			}));
		} else {
			this.addButton(this.create_new_city = new Button(this.width / 2 - 50, this.height / 2 - 10, 100, 20, I18n.format("button.city_box.create_new_city"), (press) -> {
				this.getMinecraft().displayGuiScreen(new RunModScreen(false));
			}));
		}
	}

	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		this.renderBackground();
		this.drawCenteredString(this.font, I18n.format("text.city_box.city_control_panel"), width / 2, 17, 0xFFFFFF);
		super.render(p_render_1_, p_render_2_, p_render_3_);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
