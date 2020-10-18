package com.github.vvvbbbcz.simucity.client.gui.screen.citybox;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CityBoxTaxesScreen extends Screen {
	private final CityBoxScreen motherScreen;

	int taxPercentage;

	public CityBoxTaxesScreen(CityBoxScreen motherScreen) {
		super(new StringTextComponent("taxes_and_rent"));
		this.motherScreen = motherScreen;
	}

	@Override
	public void init() {
		this.buttons.clear();
		this.buttons.add(new Button(5, 5, 50, 20, "Back", (press) -> {
			this.getMinecraft().currentScreen = motherScreen;
		}));

		this.buttons.add(new Button(width / 2, height / 2 - 40, 60, 20, String.valueOf(taxPercentage), (press) -> {
			// TODO
		}));
	}

	@Override
	public void render(int i, int j, float f) {
		this.renderBackground();
		drawCenteredString(this.font, "Town Taxes", width / 2, 17, 0xffffff);

		super.render(i, j, f);
	}

}
