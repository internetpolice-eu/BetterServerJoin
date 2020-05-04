package com.fuzzoland.BetterServerJoin;

import net.md_5.bungee.api.plugin.Plugin;

public class BSJ extends Plugin {
    public void onEnable() {
        this.getProxy().getPluginManager().registerListener(this, new EventListener());
    }
}

