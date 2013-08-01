package com.mcnsa.challenges.modcommands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mcnsa.challenges.IO;
import com.mcnsa.challenges.MCNSAChallenges;
import com.mcnsa.challenges.Setting;
import com.mcnsa.challenges.Settings;
import com.mcnsa.challenges.Util;

public class UnclaimCommand extends BaseModCommand {
	
	public UnclaimCommand()
	{
		desc = "Remove moderator claim from submission";
		needPlayer = true;
		permission = "unclaim";
	}


	public Boolean run(CommandSender sender, String[] args) {
		Integer id = null;
		if (args.length < 1 || !Util.isInteger(args[0]))
		{
			id = MCNSAChallenges.lastTeleport.get(((Player) sender).getName());
			if (id == null)
			{
				Util.Message("Usage: /chm unclaim [ID]", sender);
				return true;
			}
			else
			{
				Util.Message(Settings.getString(Setting.MESSAGE_USING_PREVIOUS_ID).replace("<ID>", Integer.toString(id)), sender);
			}
			
		}
		else
			id = Integer.parseInt(args[0]);
		
		unclaimSubmission(id);
		
		return true;
	}
	
	public static void unclaimPlayer(String player)
	{
		try
		{
			PreparedStatement statement = IO.getConnection().prepareStatement("UPDATE weekly_completed SET ClaimedBy = NULL WHERE ClaimedBy = ?");
			statement.setString(1, player);
			
			statement.executeUpdate();
			IO.getConnection().commit();
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void unclaimSubmission(int id)
	{
		try
		{
			PreparedStatement statement = IO.getConnection().prepareStatement("UPDATE weekly_completed SET ClaimedBy = NULL WHERE ID = ?");
			statement.setInt(1, id);
			
			statement.executeUpdate();
			IO.getConnection().commit();
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
