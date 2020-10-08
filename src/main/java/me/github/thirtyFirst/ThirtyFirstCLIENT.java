package me.github.thirtyFirst;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class ThirtyFirstCLIENT implements ClientModInitializer {

	@Override
	public void onInitializeClient () {
		AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
		ThirtyFirst.logger.info("Loaded Config!");
	}
}
