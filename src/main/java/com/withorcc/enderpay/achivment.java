package com.withorcc.enderpay;

import com.withorcc.enderpay.lib.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class achivment {
    public static final AchievementManager manager = new AchievementManager(Enderpay.enderpay);
    public static void createAchievements() {

        Achievement root = manager.build( "root", ChatColor.DARK_AQUA + "Это банк?" )
                .withIcon( Material.DIAMOND )
                .withBackground( Background.END)
                .withDescription( ChatColor.DARK_AQUA + "Открыть банк впервые" )
                .withFrame( Frame.CHALLENGE )
                .isToastVisible( true )
                .isAnnounceSend( true )
                .create();

        manager.build( "root/1", ChatColor.DARK_GREEN + "Арыыыыыыыыы" )
                .withIcon( Material.DEEPSLATE_DIAMOND_ORE )
                .withDescription( ChatColor.WHITE + "Иметь 10 аров на счету" )
                .withFrame( Frame.TASK )
                .withParent( root )
                .isToastVisible( true )
                .isAnnounceSend( true )
                .isHidden(false)
                .create();

        manager.build( "root/2", ChatColor.DARK_GREEN + "Осыпь меня арами" )
                .withIcon( Material.DIAMOND_ORE )
                .withDescription( ChatColor.WHITE + "Иметь 100 аров на счету" )
                .withFrame( Frame.TASK )
                .withParent( root )
                .isToastVisible( true )
                .isAnnounceSend( true )
                .isHidden(false)
                .create();

        manager.build( "root/3", ChatColor.DARK_GREEN + "Шахтёр 24/7" )
                .withIcon( Material.ANCIENT_DEBRIS )
                .withDescription( ChatColor.WHITE + "Иметь 1000 аров на счету" )
                .withFrame( Frame.TASK )
                .withParent( root )
                .isHidden(true)
                .isToastVisible( true )
                .isAnnounceSend( true )
                .create();

        manager.build( "root/4", ChatColor.DARK_GREEN + "Читер" )
                .withIcon( Material.ANCIENT_DEBRIS )
                .withDescription( ChatColor.WHITE + "Иметь 10000 аров на счету" )
                .withFrame( Frame.TASK )
                .withParent( root )
                .isHidden(true)
                .isToastVisible( true )
                .isAnnounceSend( true )
                .create();


    }
}
