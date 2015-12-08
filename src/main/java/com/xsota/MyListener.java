package com.xsota;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import commands.Tweet;

public class MyListener implements Listener{
	private FileConfiguration config;
	
	
	public MyListener(Tweet4minecraft plugin) {
		this.config = plugin.getConfig();
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		final String DEATH_MESSAGE = event.getDeathMessage();		
		final String playerName = event.getEntity().getName();
		
		Tweet.send(config.getString("SERVER_ADMIN"), DEATH_MESSAGE);
	}
}
