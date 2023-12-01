package com.withorcc.enderpay;

import com.withorcc.enderpay.requests.calc;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.withorcc.enderpay.Enderpay.enderpay;
import static com.withorcc.enderpay.event.inv;

public class transafter {
    public static HashMap<Player, String> amount = new HashMap<>();
    public static void amount(Player player){


        new AnvilGUI.Builder()
                .onClick((slot, stateSnapshot) -> { // Either use sync or async variant, not both
                    if(slot != AnvilGUI.Slot.OUTPUT) {

                    }

                    amount.put(player, stateSnapshot.getText().toString());
                    try {
                        calc.get(player);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return Arrays.asList(AnvilGUI.ResponseAction.close());

                })
                .text("0")
                .title("Сколько аров снять")
                .plugin(enderpay)
                .open(player);

    }

}
