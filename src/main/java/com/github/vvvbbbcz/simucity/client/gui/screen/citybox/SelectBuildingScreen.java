package com.github.vvvbbbcz.simucity.client.gui.screen.citybox;

import com.github.vvvbbbcz.simucity.world.building.Building;
import com.github.vvvbbbcz.simucity.world.building.Reader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class SelectBuildingScreen extends Screen {
	public SelectBuildingScreen() {
		super(new StringTextComponent("select_building"));
	}

	@Override
	protected void init() {
		Reader.readAll();
		this.buttons.clear();
		List<Building> buildings = Building.getBuildingList();
		int page = 1;
		int widthIn = 10, heightIn = 60;
		for (int index = (page - 1) * 6; index < page * 6 && index < buildings.size(); index++) {
			this.addButton(new Button(widthIn, heightIn, 120, 20, buildings.get(index).getInformation().getName(), (press) -> {

			}));
			widthIn += 120;
			if (widthIn + 120 > this.width) {
				widthIn = 10;
				heightIn += 71;
			}
			if (heightIn + 80 > this.height) {
				break;
			}
		}
	}
}
