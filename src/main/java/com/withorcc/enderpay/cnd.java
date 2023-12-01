package com.withorcc.enderpay;

import com.withorcc.enderpay.lib.Achievement;
import com.withorcc.enderpay.requests.database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class cnd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        try {
            event.openInventory((Player) commandSender);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Achievement achievement = achivment.manager.get("root");
        achievement.complete( (Player) commandSender );
        return true;
    }
}
