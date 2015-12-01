package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Login implements CommandExecutor{
	FileConfiguration config;
	String apiKey;
	String apiSecret;
	String oauthToken ;
	String oauthTokenSecret;
	
	public Login(FileConfiguration config) {
		this.config = config;
		apiKey = config.getString("API.KEY");
		apiSecret = config.getString("API.SECRET");
	}

	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3) {
		Bukkit.broadcastMessage("apiKey:"+apiKey);
		return false;
	}
}