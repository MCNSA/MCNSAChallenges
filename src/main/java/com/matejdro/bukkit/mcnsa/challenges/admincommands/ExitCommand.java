package com.matejdro.bukkit.mcnsa.challenges.admincommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.matejdro.bukkit.mcnsa.challenges.EditWizard;
import com.matejdro.bukkit.mcnsa.challenges.Setting;
import com.matejdro.bukkit.mcnsa.challenges.Settings;
import com.matejdro.bukkit.mcnsa.challenges.Util;

public class ExitCommand extends BaseAdminCommand {
	public ExitCommand()
	{
		desc = "Exit editing mode";
		needPlayer = true;
		permission = "exit";
	}


	public Boolean run(CommandSender sender, String[] args) {	
		
		if (EditWizard.players.containsKey(((Player) sender).getName()))
		{
			EditWizard.players.remove(((Player) sender).getName());
			Util.Message(Settings.getString(Setting.MESSAGE_EDITING_MODE_EXIT), sender);
		}
		else
			Util.Message(Settings.getString(Setting.MESSAGE_NOT_IN_EDIT_MODE), sender);

		return true;
	}
	
}
