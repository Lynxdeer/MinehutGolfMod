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
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("minehutgolf")
public class minehutgolf {

	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();

	public static void psendmessage(String message) {

		assert Minecraft.getInstance().player != null;
		Minecraft.getInstance().player.sendMessage(Component.nullToEmpty(message), Minecraft.getInstance().player.getUUID());

	}

	/*public void renderTextOnScreen(String text, float x, float y) {
		Minecraft.getInstance().font.draw(new PoseStack(), text, x, y, 1);
	}*/

	public minehutgolf() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(tasbot.class);
	}

	private void setup(final FMLCommonSetupEvent event) {
		// some preinit code
		LOGGER.info("hey look, the mod loaded correctly");

	}

	public static String getCurrentTitle(){
		try {
			return (String) ObfuscationReflectionHelper.findField(minehutgolf.class, "field_175201_x").get(Minecraft.getInstance().gui);
		} catch (IllegalAccessException e) {}
		return "";
	}

	public boolean showInfo = false;

	@SubscribeEvent
	public void tickEvent(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END) return;
		if (showInfo) {
			assert Minecraft.getInstance().player != null;
			//float yaw = Math.round(Minecraft.getInstance().player.getYRot() * 100f)/100f;
			//float pitch = Math.round(Minecraft.getInstance().player.getXRot() * 100f)/100f;
			String yaw = String.format("%.2f", Minecraft.getInstance().player.getYRot());
			String pitch = String.format("%.2f", Minecraft.getInstance().player.getXRot());
			String message = "Y " + yaw + "   |   P " + pitch;
			Minecraft.getInstance().player.displayClientMessage(Component.nullToEmpty(message), true);
		}
	}

	@SubscribeEvent
	public void onRotationInfoKey(InputEvent.KeyInputEvent event) {

		if (!InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), InputConstants.KEY_0) && Minecraft.getInstance().screen == null && event.getKey() == InputConstants.KEY_0) {
			showInfo = !showInfo;
			psendmessage(showInfo ? "Toggled rotation info on." : "Toggled rotation info off.");
		}

	}
}
