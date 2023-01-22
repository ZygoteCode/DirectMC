package it.gabrielebologna.directmc.commands;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.gabrielebologna.directmc.command.Cmd;
import it.gabrielebologna.directmc.main.DirectMC;
import it.gabrielebologna.directmc.utils.PermissionsUtils;
public class GmsCmd extends Cmd{
	public GmsCmd(){
		super("gms");
		addString("no-player", "&7Only players can execute this command.");
		addString("no-permission", "&7You do not have permission to run this command.");
		addString("no-online", "&7This player is not online.");
		addString("survival", "&7Your &egame mode &7has been set to &csurvival&7.");
		addString("survival-to", "&7The &egame mode &7of the player &a{PLAYER} &7has been set to &csurvival&7.");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (sender instanceof Player){
			Player p = (Player) sender;
			if (PermissionsUtils.hasPermission(p, "gms") || PermissionsUtils.hasPermission(p, "gamemode")){
				try{
					Player player = Bukkit.getPlayerExact(args[0]);
					if (player == null){
						DirectMC.sendMessage(p, getString("no-online"));
						return true;
					}else{
						player.setGameMode(GameMode.SURVIVAL);
						DirectMC.sendMessage(player, getString("survival"));
						DirectMC.sendMessage(p, getString("survival-to").replace("{PLAYER}", player.getName()));
						return true;
					}
				}catch(Exception e){
					p.setGameMode(GameMode.SURVIVAL);
					DirectMC.sendMessage(p, getString("survival"));
				}
				return true;
			}else{
				DirectMC.sendMessage(p, getString("no-permission"));
				return true;
			}
		}else{
			sender.sendMessage(getString("no-player"));
			return true;
		}
	}
}