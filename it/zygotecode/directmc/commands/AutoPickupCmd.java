package it.zygotecode.directmc.commands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.zygotecode.directmc.command.Cmd;
import it.zygotecode.directmc.main.DirectMC;
import it.zygotecode.directmc.utils.PermissionsUtils;
public class AutoPickupCmd extends Cmd{
	public AutoPickupCmd(){
		super("autopickup");
		addString("no-player", "&7Only players can execute this command.");
		addString("no-permission", "&7You do not have permission to run this command.");
		addString("no-online", "&7This player is not online.");
		addString("no-enabled", "&7This function is not enabled.");
		addString("enabled", "&7Your &aauto pickup &7has been enabled.");
		addString("disabled", "&7Your &aauto pickup &7has been disabled.");
		addString("enabled-to", "&7The &aauto pickup &7of the player &c{PLAYER} &7has been enabled.");
		addString("disabled-to", "&7The &aauto pickup &7of the player &c{PLAYER} &7has been disabled.");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (sender instanceof Player){
			Player p = (Player) sender;
			if (PermissionsUtils.hasPermission(p, "autopickup")){
				if (DirectMC.getPlugin().getConfig().getConfigurationSection("functions").getConfigurationSection("auto-pickup").getBoolean("enabled")){
					try{
						Player player = Bukkit.getPlayerExact(args[0]);
						if (player == null){
							DirectMC.sendMessage(p, getString("no-online"));
							return true;
						}else{
							if (!DirectMC.hasAutoPickup(player)){
								DirectMC.getAutoPickups().add(player);
								DirectMC.sendMessage(player, getString("enabled"));
								DirectMC.sendMessage(p, getString("enabled-to").replace("{PLAYER}", player.getName()));
								return true;
							}else{
								DirectMC.getAutoPickups().remove(player);
								DirectMC.sendMessage(player, getString("disabled"));
								DirectMC.sendMessage(p, getString("disabled-to").replace("{PLAYER}", player.getName()));
								return true;
							}
						}
					}catch(Exception e){
						if (!DirectMC.hasAutoPickup(p)){
							DirectMC.getAutoPickups().add(p);
							DirectMC.sendMessage(p, getString("enabled"));
							return true;
						}else{
							DirectMC.getAutoPickups().remove(p);
							DirectMC.sendMessage(p, getString("disabled"));
							return true;
						}
					}
				}else{
					DirectMC.sendMessage(p, getString("no-enabled"));
					return true;
				}
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