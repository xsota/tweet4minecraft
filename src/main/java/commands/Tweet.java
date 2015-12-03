package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.xsota.Tweet4minecraft;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

public class Tweet implements CommandExecutor{
	Twitter twitter;
	FileConfiguration config;
	String apiKey;
	String apiSecret;
	String oauthToken ;
	String oauthTokenSecret;
	
	public Tweet(Tweet4minecraft plugin) {		
		this.config = plugin.getConfig();
		this.twitter = plugin.twitter;
		apiKey = config.getString("API.KEY");
		apiSecret = config.getString("API.SECRET");				
	}

	public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {	
		Player player = null;
		
		if (sender instanceof Player) {
			player = (Player) sender;
			AccessToken accessToken = null;
			String playerName = player.getName();
			String token = config.getString(playerName+".accessToken");
			String tokenSecret = config.getString(playerName+".accessTokenSecret");
			
			accessToken =  new AccessToken(token,tokenSecret);
			twitter.setOAuthAccessToken(accessToken);
		} else {
			Bukkit.broadcastMessage("ログイン失敗");
			return false;
		}
		
		try {		
			twitter.updateStatus(String.join(" ", args));
			Bukkit.broadcastMessage("ツイート成功");
		} catch (TwitterException e) {			
			Bukkit.broadcastMessage("ツイート失敗"+e);
			return false;
		}
		return true;
	}
}
