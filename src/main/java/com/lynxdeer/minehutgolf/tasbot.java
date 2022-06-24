package com.lynxdeer.minehutgolf;


import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.Arrays;

import static com.google.common.base.Strings.nullToEmpty;
import static com.lynxdeer.minehutgolf.minehutgolf.psendmessage;

public class tasbot {

	private static final ArrayList<String> validCommands = new ArrayList<>(Arrays.asList("start", "stop", "add", "add5", "back"));
	public static int shots = 1;

	@SubscribeEvent
	public static void onChat(ClientChatEvent event) {
		LocalPlayer player = Minecraft.getInstance().player;
		LogManager.getLogger().info(event.getMessage());
		assert player != null;



		if (event.getMessage().startsWith("-sp")) {
			String command = nullToEmpty(event.getMessage().replaceFirst("-sp ", ""));
			float alignment = player.getXRot();
			try {alignment = Float.parseFloat(command);}
			catch(Exception e) {
				psendmessage("Invalid float value! See logs for more details.");
				LogManager.getLogger().info("Exception catch: " + e);
			}
			psendmessage("Set pitch to " + command);
			player.setXRot(alignment);

		} if (event.getMessage().startsWith("-sy")) {
			String command = nullToEmpty(event.getMessage().replaceFirst("-sy ", ""));
			float alignment = player.getYRot();
			try {alignment = Float.parseFloat(command);}
			catch(Exception e) {
				psendmessage("Invalid float value! See logs for more details.");
				LogManager.getLogger().info("Exception catch: " + e);
			}
			psendmessage("Set yaw to " + command);
			player.setYRot(alignment);
		}



		if (event.getMessage().startsWith("-t")) {

			event.setCanceled(true);
			String command = nullToEmpty(event.getMessage().replaceFirst("-t ", ""));

			if (!validCommands.contains(command)) {
				psendmessage("Failed to execute invalid command. Valid commands: " + String.join(", ", validCommands));
				return;
			}
			psendmessage("Successfully executed command " + "\"" + command + "\".");
			if (command.equals("start")){shots=1; psendmessage("Restarted the TAS to 1.");}
			if (command.equals("back")){shots--; psendmessage("-1 shot pos [" + shots + "]");}
			if (command.equals("add")) {shots++; psendmessage("+1 shot pos [" + shots + "]");}
			if (command.equals("add5")){shots+=5; psendmessage("+5 shot pos [" + shots + "]");}

		}
	}

	@SubscribeEvent
	public static void onAlignKey(InputEvent.KeyInputEvent event) {

		LocalPlayer player = Minecraft.getInstance().player;
		if (!InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), InputConstants.KEY_R) && Minecraft.getInstance().screen == null && event.getKey() == InputConstants.KEY_R) {
			psendmessage("Loaded position " + shots + ".");
			assert player != null;
			if (shots == 1) {player.setYRot(26.3f);  player.setXRot(-54.4f);}

			if (shots == 2) {player.setYRot(27.0f);  player.setXRot(-27.1f);}
			if (shots == 3) {player.setYRot(139.5f); player.setXRot(-66.5f);}

			if (shots == 4) {player.setYRot(199.0f); player.setXRot(-35.0f);}
			if (shots == 5) {player.setYRot(-173.9f); player.setXRot(-28.6f);}

			if (shots == 6) {player.setYRot(-143.3f); player.setXRot(-50.0f);}
			if (shots == 7) {player.setYRot(-191.2f); player.setXRot(-41.4f);}

			if (shots == 8) {player.setYRot(-166.2f); player.setXRot(-33.4f);}
			if (shots == 9) {player.setYRot(169.6f); player.setXRot(-39.9f);}
			if (shots ==10) {player.setYRot(-191.2f); player.setXRot(-39.7f);}

			if (shots ==11) {player.setYRot(114.9f); player.setXRot(-28.4f);}
			if (shots ==12) {player.setYRot(143.2f); player.setXRot(-14.7f);}

			if (shots ==13) {player.setYRot(124.3f); player.setXRot(-28.9f);}
			if (shots ==14) {player.setYRot(139.0f); player.setXRot(-35.6f);}

			if (shots ==15) {player.setYRot(19.3f); player.setXRot(-12.2f);}
			if (shots ==16) {player.setYRot(21.2f); player.setXRot(-39.7f);}
			if (shots ==17) psendmessage("No need for alignment. You got this buddy!");
			// Hole 9. Oh boy. This shot is so unbelievably precise.
			if (shots ==18) {player.setYRot(118.17f); player.setXRot(-38.62f);}
			if (shots ==19) {player.setYRot(72.13f); player.setXRot(-43.71f);}
			if (shots ==20) {player.setYRot(131.54f); player.setXRot(-60.66f);}
			shots++;
		}

	}

}
