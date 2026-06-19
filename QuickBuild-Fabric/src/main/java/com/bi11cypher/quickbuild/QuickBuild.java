package com.bi11cypher.quickbuild;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickBuild implements ModInitializer {
    public static final String MOD_ID = "quickbuild";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[QuickBuild] ✔ Script is running. Version 1.3.0 by Bi11_Cypher.");
    }
}
