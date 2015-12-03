package com.xsota;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import commands.Login;
import commands.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class Tweet4minecraft extends JavaPlugin {
	public Twitter twitter;
	FileConfiguration config;
	
	@Override
	public void onEnable() {
		getLogger().info("plugin has been enable.");
		this.saveDefaultConfig();

		this.config = this.getConfig();
		
		this.initTwitter();
		
		// set command
		getCommand("tweet").setExecutor(new Tweet(this));
		getCommand("login").setExecutor(new Login(this));

		this.saveConfig();
	}

	@Override
	public void onDisable() {
		getLogger().info("plugin has been disable.");
	}
	
	private void initTwitter() {
		String apiKey = config.getString("API.KEY");
		String apiSecret = config.getString("API.SECRET");
		this.twitter = TwitterFactory.getSingleton();
		this.twitter.setOAuthConsumer(apiKey, apiSecret);
	}
}
