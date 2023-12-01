package com.withorcc.enderpay;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.advancement.AdvancementDisplay;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Enderpay extends JavaPlugin {

    public static Enderpay enderpay;
    public FileConfiguration config = getConfig();
    @Override
    public void onEnable() {
        enderpay = this;
        Bukkit.getServer().getPluginManager().registerEvents(new event(), this);
        this.getCommand("bank").setExecutor(new cnd());
        System.out.println("WithoR WorkS");
        config.addDefault("Diamond_ore", 2);
        config.addDefault("Emerald", 3);
        config.addDefault("Ancient_debris", 5);
        config.addDefault("webhook", "https://discord.com/api/webhooks/1156308852120223855/ff89HD-gA4dfKUJe9FbO3x5HnTk6YUypodfBAQWnNkWKoe0VFB5SUDKNWDHELadlqcNS");
        config.addDefault("Deepslate_diamond_ore", 1);
        config.addDefault("db_host", "jdbc:mysql://db.worldhosts.fun/");
        config.addDefault("db_user", "");
        config.addDefault("db_password", "");

        config.options().copyDefaults(true);
        saveConfig();
        achivment.createAchievements();

    }

    @Override
    public void onDisable() {
        System.out.println("WithoR WorkS");
    }
}
