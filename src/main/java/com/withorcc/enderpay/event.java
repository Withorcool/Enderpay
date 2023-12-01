package com.withorcc.enderpay;

import com.withorcc.enderpay.requests.calc;
import com.withorcc.enderpay.requests.database;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static com.withorcc.enderpay.lib.Gui.GuiItem;

public class event implements Listener {



    public static Inventory inv;
    public static void openInventory(final HumanEntity ent) throws SQLException {
        inv = Bukkit.createInventory(null, 9, "");
        ent.openInventory(inv);
        inv.addItem(GuiItem(Material.DIAMOND_ORE, "§bВаш баланс: " + database.getvalue((Player) ent), "§a"));
        inv.addItem(GuiItem(Material.CHEST, "§bСнять АРы с карты", "§a"));
        if(ent.getInventory().getItemInMainHand().getType() == Material.DIAMOND_ORE && ent.getInventory().getItemInMainHand().getType() != null || ent.getInventory().getItemInMainHand().getType() == Material.DEEPSLATE_DIAMOND_ORE || ent.getInventory().getItemInMainHand().getType() == Material.EMERALD_ORE || ent.getInventory().getItemInMainHand().getType() == Material.DEEPSLATE_EMERALD_ORE || ent.getInventory().getItemInMainHand().getType() == Material.ANCIENT_DEBRIS) {
            inv.addItem(GuiItem(Material.ENDER_CHEST, "§bПоложить АРы на карту", "§a"));
        }else {
                ent.sendMessage("§5[EnderPay] §fЧтобы положить §9Ары§f на счет необходимо держать их в руке");
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) return;

        final Player p = (Player) e.getWhoClicked();

        if(e.getRawSlot() == 1){
            transafter.amount(p);
        }
        if(e.getRawSlot() == 2){
            try {
                calc.set(p, p.getInventory().getItemInMainHand().getAmount());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    

}

