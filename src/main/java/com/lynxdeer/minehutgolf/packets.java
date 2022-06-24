package com.lynxdeer.minehutgolf;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class packets {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation("minehutgolf", "main"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
	);

	public static void rightClick(float duration) {

		LocalPlayer player = Minecraft.getInstance().player;
		assert player != null;
		INSTANCE.sendToServer(new ServerboundUseItemPacket(InteractionHand.MAIN_HAND));
		// start using the item
		// wait here
		// stop the item use

	}

}
