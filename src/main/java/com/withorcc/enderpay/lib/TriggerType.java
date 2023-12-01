/*
 * Copyright (c) 2018 Frozencraft
 */
package com.withorcc.enderpay.lib;

/**
 * This enum contains all trigger type available
 *
 * @author Frozen
 */
public enum TriggerType {

    ARBITRARY_PLAYER_TICK( "minecraft:arbitrary_player_tick" ),
    BRED_ANIMALS( "minecraft:bred_animals" ),
    BREWED_POTION( "minecraft:brewed_potion" ),
    CHANGED_DIMENSION( "minecraft:changed_dimension" ),
    CONSTRUCT_BEACON( "minecraft:construct_beacon" ),
    CONSUME_ITEM( "minecraft:consume_item" ),
    CURED_ZOMBIE_VILLAGER( "minecraft:cured_zombie_villager" ),
    ENCHANTED_ITEM( "minecraft:enchanted_item" ),
    ENTER_BLOCK( "minecraft:enter_block" ),
    ENTITY_HURT_PLAYER( "minecraft:entity_hurt_player" ),
    ENTITY_KILLED_PLAYER( "minecraft:entity_killed_player" ),
    IMPOSSIBLE( "minecraft:impossible" ),
    INVENTORY_CHANGED( "minecraft:inventory_changed" ),
    ITEM_DURABILITY_CHANGED( "minecraft:item_durability_changed" ),
    LEVITATION( "minecraft:levitation" ),
    LOCATION( "minecraft:location" ),
    PLACED_BLOCK( "minecraft:placed_block" ),
    PLAYER_HURT_ENTITY( "minecraft:player_hurt_entity" ),
    PLAYER_KILLED_ENTITY( "minecraft:player_killed_entity" ),
    RECIPE_UNLOCKED( "minecraft:recipe_unlocked" ),
    SLEPT_IN_BED( "minecraft:slept_in_bed" ),
    SUMMONED_ENTITY( "minecraft:summoned_entity" ),
    TAME_ANIMAL( "minecraft:tame_animal" ),
    TICK( "minecraft:tick" ),
    USED_ENDER_EYE( "minecraft:used_ender_eye" ),
    VILLAGER_TRADE( "minecraft:villager_trade" );

    private final String name;

    TriggerType( String name ) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
