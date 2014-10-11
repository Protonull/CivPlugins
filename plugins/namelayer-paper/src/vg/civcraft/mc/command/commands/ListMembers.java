package vg.civcraft.mc.command.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vg.civcraft.mc.GroupManager.PlayerType;
import vg.civcraft.mc.NameAPI;
import vg.civcraft.mc.command.PlayerCommand;
import vg.civcraft.mc.group.Group;

public class ListMembers extends PlayerCommand{

	public ListMembers(String name) {
		super(name);
		setDescription("This command is used to list the members in a group");
		setUsage("/groupslistmembers <group> (PermissionType)");
		setIdentifier("groupslistmembers");
		setArguments(1,2);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)){
			sender.sendMessage("\"Pretend this is red:\" no.");
			return true;
		}
		Player p = (Player) sender;
		Group g = gm.getGroup(args[0]);
		UUID uuid = NameAPI.getUUID(p.getName());
		if (!g.isMember(uuid) && !(p.isOp() || p.hasPermission("namelayer.admin"))){
			p.sendMessage(ChatColor.RED + "You are not on this group.");
			return true;
		}
		
		List<UUID> uuids = null;
		if (args.length > 1){
			PlayerType type = PlayerType.getPlayerType(args[1]);
			if (type == null){
				PlayerType.displayPlayerTypes(p);
				return true;
			}
			uuids = g.getAllMembers(type);
		}
		else
			uuids = g.getAllMembers();
		String x = "Members are as follows: ";
		for (UUID uu: uuids)
			x += uu.toString() + " ";
		p.sendMessage(ChatColor.GREEN + x);
		return true;
	}

}
