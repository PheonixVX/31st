package me.github.thirtyFirst;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThirtyFirst implements ModInitializer {
    public static final String MOD_ID = "thirtyfirst";
    public static final Logger logger = LogManager.getLogger();

    @Override
    public void onInitialize() {
        logger.info("31st of Halloween loaded!!!");
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        logger.info("Loaded Config!");
    }
}
