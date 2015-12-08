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
	static Twitter twitter;
	static FileConfiguration config;
	String apiKey;
	String apiSecret;
	String oauthToken ;
	String oauthTokenSecret;
	
	public Tweet(Tweet4minecraft plugin) {		
		config = plugin.getConfig();
		twitter = plugin.twitter;
		apiKey = config.getString("API.KEY");
		apiSecret = config.getString("API.SECRET");				
	}

	public static boolean send(String playerName, String text){
		String token = config.getString(playerName+".accessToken");
		String tokenSecret = config.getString(playerName+".accessTokenSecret");
		
		AccessToken accessToken = null;
		
		accessToken =  new AccessToken(token,tokenSecret);
		twitter.setOAuthAccessToken(accessToken);
		
		try {		
			twitter.updateStatus(text);
			Bukkit.broadcastMessage("ツイート成功");
			return true;
		} catch (TwitterException e) {			
			Bukkit.broadcastMessage("ツイート失敗"+e);
			return false;
		}
	}
	
	public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {	
		Player player = null;
		String playerName = null;
		
		if (sender instanceof Player) {
			player = (Player) sender;
		
			 playerName = player.getName();
			
		} else {
			Bukkit.broadcastMessage("ログイン失敗");
			return false;
		}
		
		return this.send(playerName,String.join(" ", args));	
		
	}
}
