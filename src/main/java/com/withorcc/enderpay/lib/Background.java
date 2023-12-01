/*
 * Copyright (c) 2018 Frozencraft
 */
package com.withorcc.enderpay.lib;

/**
 * This enum is used for setting the background of the achievement window
 * It's only needed for the root achievement
 *
 * @author Frozen
 */
public enum Background {

    ADVENTURE( "minecraft:textures/gui/advancements/backgrounds/adventure.png" ),
    END( "minecraft:textures/gui/advancements/backgrounds/end.png" ),
    HUSBANDRY( "minecraft:textures/gui/advancements/backgrounds/husbandry.png" ),
    NETHER( "minecraft:textures/gui/advancements/backgrounds/nether.png" ),
    STONE( "minecraft:textures/gui/advancements/backgrounds/stone.png" );

    private final String name;

    Background( String name ) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
