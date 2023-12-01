/*
 * Copyright (c) 2018 Frozencraft
 */
package com.withorcc.enderpay.lib;

/**
 * This enum is used for determining the Frame used by an achievement
 * By default an achievement will used {@link #TASK}
 *
 * @author Frozen
 */
public enum Frame {

    TASK( "task" ),
    GOAL( "goal" ),
    CHALLENGE( "challenge" );

    private final String name;

    Frame( String name ) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
