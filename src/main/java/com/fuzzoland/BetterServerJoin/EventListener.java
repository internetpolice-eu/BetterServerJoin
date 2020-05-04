package com.fuzzoland.BetterServerJoin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class EventListener
implements Listener {
    @EventHandler
    public void onChat(ChatEvent event) {
        if (event.isCommand()) {
            String name = event.getMessage().split(" ")[0].replaceFirst("/", "");
            Boolean found = false;
            for (ServerInfo info : ProxyServer.getInstance().getServers().values()) {
                if (!name.equals(info.getName())) continue;
                found = true;
                break;
            }
            if (found.booleanValue()) {
                event.setCancelled(true);
                if (event.getSender() instanceof ProxiedPlayer) {
                    ProxiedPlayer player = (ProxiedPlayer)event.getSender();
                    ServerInfo info = ProxyServer.getInstance().getServerInfo(name);
                    if (!player.getServer().getInfo().equals(info)) {
                        player.connect(info);
                    } else {
                        player.sendMessage(ChatColor.RED + "You're already on that server.");
                    }
                }
            }
        }
    }
}

