package com.xsota;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;



public class Tweet4minecraft extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("plugin has been enable.");
		this.saveDefaultConfig();
		
		FileConfiguration config = this.getConfig();
		//this.getConfig().set("API.KEY", "SuucnlrR6yAOXz3AQaJG4Q");
		//this.getConfig().set("API.SECRET", "wCL1YIuGH8fIKvBfXKrBbacGF8mhJHBR3A11MpmyPw");
		
		//if(config.get("API.KEY"))
	    
		this.saveConfig();
	}
	
	@Override
	public void onDisable() {
		getLogger().info("plugin has been disable.");
	}
}
