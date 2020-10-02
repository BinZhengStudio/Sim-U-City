package com.github.vvvbbbcz.simucity.event.handler;

import com.github.vvvbbbcz.simucity.SimUCity;
import com.github.vvvbbbcz.simucity.item.SUCItems;
import com.github.vvvbbbcz.simucity.network.Networking;
import com.github.vvvbbbcz.simucity.network.PlayerCreditsPack;
import com.github.vvvbbbcz.simucity.network.WorldStatePack;
import com.github.vvvbbbcz.simucity.world.storage.WorldData;
import com.google.gson.Gson;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.ItemHandlerHelper;

@Mod.EventBusSubscriber
public class ServerEventHandler {
	@SubscribeEvent
	public static void onWorldLoad(final WorldEvent.Load event) {
		if (event.getWorld() instanceof ServerWorld) {
			WorldData.loadData(event.getWorld().getWorld());
		}
	}

	@SubscribeEvent
	public static void onWorldSave(final WorldEvent.Save event) {
		if (event.getWorld() instanceof ServerWorld) {
			WorldData.saveData(event.getWorld().getWorld());
		}
	}

	@SubscribeEvent
	public static void onPlayerJoin(final PlayerEvent.PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		CompoundNBT nbt = player.getPersistentData();
		CompoundNBT data = nbt.contains(SimUCity.MODID) ? nbt.getCompound(SimUCity.MODID) : new CompoundNBT();

		Networking.WORLD_STATE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new WorldStatePack(new Gson().toJson(WorldData.state)));

		if (!data.getBoolean("HasGuideBook")) {
			ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(SUCItems.CITY_BOX)); // TODO Haven't added guide book :-).
			data.putBoolean("HasGuideBook", true);
		}

		if (!data.contains("credits")) {
			data.putInt("credits", 10);
		}
		Networking.PLAYER_CREDITS.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new PlayerCreditsPack(data.getInt("credits")));

		nbt.put(SimUCity.MODID, data);
	}

//	@SubscribeEvent
//	public static void onLoadPlayer(final PlayerEvent.LoadFromFile event) {
//	}
//
//	@SubscribeEvent
//	public static void onSavePlayer(final PlayerEvent.SaveToFile event) {
//	}
}
