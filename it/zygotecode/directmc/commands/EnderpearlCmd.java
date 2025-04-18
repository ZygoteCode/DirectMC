package it.zygotecode.directmc.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.zygotecode.directmc.command.Cmd;
import it.zygotecode.directmc.main.DirectMC;
import it.zygotecode.directmc.utils.PermissionsUtils;
import it.zygotecode.directmc.utils.ProjectileUtils;
public class EnderpearlCmd extends Cmd{
	public EnderpearlCmd(){
		super("enderpearl");
		addString("no-player", "&7Only players can execute this command.");
		addString("no-permission", "&7You do not have permission to run this command.");
		addString("enderpearl", "&7You have been launched an enderpearl.");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (sender instanceof Player){
			Player p = (Player) sender;
			if (PermissionsUtils.hasPermission(p, "enderpearl")){
				ProjectileUtils.LaunchEnderPearl(p);
				DirectMC.sendMessage(p, getString("enderpearl"));
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