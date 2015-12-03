package commands;


import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class Login implements CommandExecutor {
	Twitter twitter;
	FileConfiguration config;
	JavaPlugin plugin;	
	String oauthToken;
	String oauthTokenSecret;
	HashMap<String,RequestToken> requestTokens ;
	

	public Login(JavaPlugin plugin) {
		this.plugin = plugin;
		this.config = plugin.getConfig();
		this.requestTokens = new HashMap<String,RequestToken>();
		String apiKey = config.getString("API.KEY");
		String apiSecret = config.getString("API.SECRET");
		this.twitter = TwitterFactory.getSingleton();
		this.twitter.setOAuthConsumer(apiKey, apiSecret);
	}

	public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
		String pin = null;
		if(args.length == 1){
			pin = args[0];
		}
		
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;			
		} else {
			return false;
		}
				
		AccessToken accessToken = null;
		RequestToken requestToken = null;
		if(requestTokens.containsKey(player.getName())){
			requestToken = requestTokens.get(player.getName());
		}
		
		if(pin != null && requestToken != null){
			try {				
				accessToken = twitter.getOAuthAccessToken(requestToken, pin);				
				config.set(player.getName()+".accessToken", accessToken.getToken());
				config.set(player.getName()+".accessTokenSecret", accessToken.getTokenSecret());				
				plugin.saveConfig();
				Bukkit.broadcastMessage("ログイン成功");
				return true;
			} catch (TwitterException e) {				
				Bukkit.broadcastMessage("ログイン失敗:"+e);
				return false;
			}
		}

		try {
			requestToken = twitter.getOAuthRequestToken();
			requestTokens.put(player.getName(), requestToken);
		} catch (TwitterException e) {
			return false;
		}
		Bukkit.broadcastMessage("ログインしてね" + requestToken.getAuthorizationURL());
		
		plugin.saveConfig();
		return true;
	}
}