/*
    MIT License
    
    Copyright (c) 2018 FrozenLegend
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
*/
package com.withorcc.enderpay.lib;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

/**
 * This manager is used for storing all achievements created by
 * this manager
 *
 * @author Frozen
 */
public class AchievementManager {

    private Plugin plugin;

    private Set<Achievement> achievements = new HashSet<>();

    /**
     * Default constructor
     *
     * @param plugin The plugin instance
     */
    public AchievementManager( @NotNull Plugin plugin ) {
        this.plugin = plugin;
    }

    /**
     * Get the achievement using his creation key
     *
     * @param key The key used when creating this achievement
     * @return The achievement found or null
     */
    @Nullable
    public Achievement get( @NotNull String key ) {
        return achievements.stream()
                .filter( a -> a.getId().equals( new NamespacedKey( plugin, key ) ) )
                .findFirst()
                .orElse( null );
    }

    /**
     * Create a new instance of {@link Achievement.AchievementBuilder}
     *
     * @param key The key used for this achievement
     * @return The instance created
     */
    @NotNull
    public Achievement.AchievementBuilder build( @NotNull String key ) {
        return new Achievement.AchievementBuilder( key, this );
    }

    /**
     * Create a new instance of {@link Achievement.AchievementBuilder}
     * This builder allow you to interact with {@link Display} field of achievement
     *
     * @param key   The key used for this achievement
     * @param title The title of this achievement
     * @return The instance created
     */
    @NotNull
    public Achievement.AchievementBuilder build( @NotNull String key, @NotNull String title ) {
        return new Achievement.AchievementBuilder( key, title, this );
    }

    /**
     * Add this achievement into the set
     *
     * @param achievement Achievement to add
     */
    void add( Achievement achievement ) {
        achievements.add( achievement );
    }

    /**
     * Get the plugin instance of this builder
     *
     * @return The plugin instance
     */
    Plugin getPlugin() {
        return plugin;
    }

}
