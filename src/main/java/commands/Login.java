package commands;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class Login implements CommandExecutor {
	FileConfiguration config;
	String apiKey;
	String apiSecret;
	String oauthToken;
	String oauthTokenSecret;

	public Login(FileConfiguration config) {
		this.config = config;
		apiKey = config.getString("API.KEY");
		apiSecret = config.getString("API.SECRET");
	}

	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3) {
		AccessToken accessToken = null;
		RequestToken requestToken = null;

		Twitter twitter = TwitterFactory.getSingleton();

		twitter.setOAuthConsumer(apiKey, apiSecret);

		try {
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			// そのうちエラー処理書くよ
		}
		Bukkit.broadcastMessage("ログインコマンド");
		Bukkit.broadcastMessage("ログインしてね" + requestToken.getAuthorizationURL());

		return true;
	}
}