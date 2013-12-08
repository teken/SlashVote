package Teken.vote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class vote extends JavaPlugin{
	public static Plugin instance;
	String name = "Slash Vote";
	String voteMessage = "";
	//String voteURL = "http://tekkitserverlist.com/server/2360/vote";
	String voteTextFileURL = "voteText.txt";

	public vote(){
		instance = this;
	}

	@Override
	public void onEnable(){
		getLogger().info(name+" has been enabled");
		reload();
	}

	@Override
	public void onDisable(){
		getLogger().info(name+" has been disabled");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		try{
			if(cmd.getName().equalsIgnoreCase("vote")){
				if(sender instanceof Player){
					/*if(Desktop.isDesktopSupported())
					{
						Desktop.getDesktop().browse(new URI(voteURL));
					}*/
					sender.sendMessage(textToColor(voteMessage));
				}else{
					sender.sendMessage("You are no player");
				}
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("votereload") && sender.isOp()){
				reload();
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			sender.sendMessage("Something went really wrong, please try again");
		}
		return false;
	}
	
	private void reload(){
		loadVoteMessageFromFile();
	}
	
	private void loadVoteMessageFromFile(){
		try{
			BufferedReader br = new BufferedReader(new FileReader(voteTextFileURL));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append("\n");
		            line = br.readLine();
		        }
		         voteMessage = sb.toString();
		    } finally {
		        br.close();
		    }
		}catch(Exception e){
			try {
				new BufferedWriter(new FileWriter(voteTextFileURL)).write("Vote Text Here");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static String textToColor(String text)
	{
		text = text.replaceAll("&0", ChatColor.BLACK+"");
		text = text.replaceAll("&1", ChatColor.DARK_BLUE+"");
		text = text.replaceAll("&2", ChatColor.DARK_GREEN+"");
		text = text.replaceAll("&3", ChatColor.DARK_AQUA+"");
		text = text.replaceAll("&4", ChatColor.DARK_RED+"");
		text = text.replaceAll("&5", ChatColor.DARK_PURPLE+"");
		text = text.replaceAll("&6", ChatColor.GOLD+"");
		text = text.replaceAll("&7", ChatColor.GRAY+"");
		text = text.replaceAll("&8", ChatColor.DARK_GRAY+"");
		text = text.replaceAll("&9", ChatColor.BLUE+"");
		text = text.replaceAll("&A", ChatColor.GREEN+"");
		text = text.replaceAll("&B", ChatColor.AQUA+"");
		text = text.replaceAll("&C", ChatColor.RED+"");
		text = text.replaceAll("&D", ChatColor.LIGHT_PURPLE+"");
		text = text.replaceAll("&E", ChatColor.YELLOW+"");
		text = text.replaceAll("&F", ChatColor.WHITE+"");
		text = text.replaceAll("&a", ChatColor.GREEN+"");
		text = text.replaceAll("&b", ChatColor.AQUA+"");
		text = text.replaceAll("&c", ChatColor.RED+"");
		text = text.replaceAll("&d", ChatColor.LIGHT_PURPLE+"");
		text = text.replaceAll("&e", ChatColor.YELLOW+"");
		text = text.replaceAll("&u", ChatColor.UNDERLINE+"");
		text = text.replaceAll("&U", ChatColor.UNDERLINE+"");
		text = text.replaceAll("&n", ChatColor.BOLD+"");
		text = text.replaceAll("&N", ChatColor.BOLD+"");
		text = text.replaceAll("&o", ChatColor.ITALIC+"");
		text = text.replaceAll("&O", ChatColor.ITALIC+"");
		text = text.replaceAll("&i", ChatColor.ITALIC+"");
		text = text.replaceAll("&I", ChatColor.ITALIC+"");
	//Magic will be built in, in version 1.2
		text = text.replaceAll("&k", ChatColor.MAGIC+"");
		text = text.replaceAll("&K", ChatColor.MAGIC+"");
		return text;
	}
}
