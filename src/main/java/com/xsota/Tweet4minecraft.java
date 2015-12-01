package com.xsota;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import commands.Login;
import commands.Tweet;



public class Tweet4minecraft extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("plugin has been enable.");
		this.saveDefaultConfig();
		
		FileConfiguration config = this.getConfig();
		
		//set command
		getCommand("tweet").setExecutor(new Tweet(config));
		getCommand("login").setExecutor(new Login(config));
		
	    
		this.saveConfig();
	}
	
	@Override
	public void onDisable() {
		getLogger().info("plugin has been disable.");
	}
}
