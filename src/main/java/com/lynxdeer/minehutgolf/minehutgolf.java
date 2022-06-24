package com.lynxdeer.minehutgolf;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("minehutgolf")
public class minehutgolf {

	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();

	public minehutgolf() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		// some preinit code
		LOGGER.info("hey look, the mod loaded correctly");
	}

	public boolean showInfo = false;

	@SubscribeEvent
	public void tickEvent(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END) return;
		if (showInfo) {
			float yaw = Math.round(Minecraft.getInstance().player.getYRot() * 100f)/100f;
			float pitch = Math.round(Minecraft.getInstance().player.getXRot() * 100f)/100f;
			String message = "Y " + yaw + "   |   P " + pitch;
			Minecraft.getInstance().player.displayClientMessage(Component.nullToEmpty(message), true);
		}
	}

	@SubscribeEvent
	public void onPlayerPressKey(InputEvent.KeyInputEvent event) {

		if (!InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), InputConstants.KEY_0) && Minecraft.getInstance().screen == null && event.getKey() == InputConstants.KEY_0) {
			showInfo = !showInfo;
			Minecraft.getInstance().player.sendMessage(Component.nullToEmpty((showInfo) ? ("Toggled rotation info on.") : "Toggled rotation info off."), Minecraft.getInstance().player.getUUID());
		}

	}
}
