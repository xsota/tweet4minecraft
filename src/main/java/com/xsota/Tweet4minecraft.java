package com.xsota;

import org.bukkit.plugin.java.JavaPlugin;



public class Tweet4minecraft extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("plugin has been enable.");			
	}
	
	@Override
	public void onDisable() {
		getLogger().info("plugin has been disable.");
	}
}
