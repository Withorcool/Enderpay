package com.withorcc.enderpay.requests;

import com.withorcc.enderpay.Enderpay;
import com.withorcc.enderpay.achivment;
import com.withorcc.enderpay.lib.Achievement;
import com.withorcc.enderpay.transafter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.sql.SQLException;

public class calc {
    public static void get(Player player) throws SQLException {
        if(Integer.parseInt(transafter.amount.get(player)) <= Integer.parseInt(database.getvalue(player))){
            ItemStack i = new ItemStack(Material.DEEPSLATE_DIAMOND_ORE, Integer.parseInt(transafter.amount.get(player)));
            player.getWorld().dropItem(player.getLocation(), i);
            int cl = Integer.parseInt(database.getvalue(player)) - Integer.parseInt(transafter.amount.get(player));
            try {
                webhook.hook("Снятие", player.getName(), Integer.parseInt(database.getvalue(player)), cl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            database.updatev(player, cl);
            transafter.amount.remove(player);

        }
        check(player);
    }
    public static void set(Player player, int s) throws SQLException {
        int cl = 0;
        if(player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_ORE) {
            player.getInventory().removeItem(new ItemStack(Material.DIAMOND_ORE, s));

            int c = s * Enderpay.getPlugin(Enderpay.class).config.getInt("Diamond_ore");
            cl = Integer.parseInt(database.getvalue(player)) + c;
        }else {
            if (player.getInventory().getItemInMainHand().getType() == Material.DEEPSLATE_DIAMOND_ORE) {
                player.getInventory().removeItem(new ItemStack(Material.DEEPSLATE_DIAMOND_ORE, s));
//                int c = s * Enderpay.getPlugin(Enderpay.class).config.getInt("Deepslate_diamond_ore");
                int c = s;
                cl = Integer.parseInt(database.getvalue(player)) + c;
            }else {
                if (player.getInventory().getItemInMainHand().getType() == Material.DEEPSLATE_EMERALD_ORE) {
                    player.getInventory().removeItem(new ItemStack(Material.DEEPSLATE_EMERALD_ORE, s));
                    int c = s * Enderpay.getPlugin(Enderpay.class).config.getInt("Emerald");
                    cl = Integer.parseInt(database.getvalue(player)) + c;
                }else {
                    if (player.getInventory().getItemInMainHand().getType() == Material.EMERALD_ORE) {
                        player.getInventory().removeItem(new ItemStack(Material.EMERALD_ORE, s));
                        int c = s * Enderpay.getPlugin(Enderpay.class).config.getInt("Emerald");
                        cl = Integer.parseInt(database.getvalue(player)) + c;
                    }else{
                        if (player.getInventory().getItemInMainHand().getType() == Material.ANCIENT_DEBRIS) {
                            player.getInventory().removeItem(new ItemStack(Material.ANCIENT_DEBRIS, s));
                            int c = s * Enderpay.getPlugin(Enderpay.class).config.getInt("Ancient_debris");
                            cl = Integer.parseInt(database.getvalue(player)) + c;
                        }
                    }
                }
            }
        }
        try {
            webhook.hook("Пополнение", player.getName(), Integer.parseInt(database.getvalue(player)), cl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        database.updatev(player, cl);
        player.closeInventory();
        check(player);
    }

    public static void check(Player player) throws SQLException {
        if(Integer.parseInt(database.getvalue(player)) >= 10){
            Achievement achievement = achivment.manager.get("root/1");
            if ( achievement != null ) {
                achievement.complete( player );

            }
        }
        if(Integer.parseInt(database.getvalue(player)) >= 100){
            Achievement achievement = achivment.manager.get("root/2");
            if ( achievement != null ) {
                achievement.complete( player );

            }
        }
        if(Integer.parseInt(database.getvalue(player)) >= 1000){
            Achievement achievement = achivment.manager.get("root/3");
            if ( achievement != null ) {
                achievement.complete( player );

            }
        }
        if(Integer.parseInt(database.getvalue(player)) >= 10000){
            Achievement achievement = achivment.manager.get("root/4");
            if ( achievement != null ) {
                achievement.complete( player );

            }
        }
    }
}
