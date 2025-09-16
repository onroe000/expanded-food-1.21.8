package com.expandedfood;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpandedFood implements ModInitializer {
    public static final String MOD_ID = "expandedfood";
    static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        Foods.initialize();
        LOGGER.info("Expanded Food mod initialized");
    }
}