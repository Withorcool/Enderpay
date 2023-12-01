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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * This class represent a achievement (aka Advancement)
 *
 * @author Frozen
 */
public class Achievement {

    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter( List.class, new ListAdapter() )
            .registerTypeAdapter( Map.class, new MapAdapter() )
            .setPrettyPrinting()
            .create();

    private Plugin plugin;
    private NamespacedKey id;

    @Expose
    private Display display;

    @Expose
    private String parent;

    @Expose
    private Map<String, Trigger> criteria;

    @Expose
    private List<List<String>> requirements;

    @Expose
    private Map<String, Object> rewards;

    private Advancement advancement;

    /**
     * Private constructor taking the builder as parameter
     *
     * @param builder AchievementBuilder instance
     */
    private Achievement( @NotNull AchievementBuilder builder ) {
        this.plugin = builder.achievementManager.getPlugin();
        this.id = builder.id;
        this.display = builder.display;
        this.criteria = builder.criteria;
        this.parent = builder.parent;
        this.requirements = builder.requirements;
        this.rewards = builder.rewards;
        this.advancement = builder.advancement;
    }

    /**
     * Complete this achievement for the selected player
     *
     * @param player Player instance
     */
    public void complete( @NotNull Player player ) {
        AdvancementProgress progress = player.getAdvancementProgress( advancement );
        if ( !progress.isDone() ) {
            progress.getRemainingCriteria().forEach( progress::awardCriteria );
        }
    }

    /**
     * Revoke this achievement for the selected player
     *
     * @param player Player instance
     */
    public void revoke( @NotNull Player player ) {
        AdvancementProgress progress = player.getAdvancementProgress( advancement );
        if ( progress.isDone() ) {
            progress.getAwardedCriteria().forEach( progress::revokeCriteria );
        }
    }

    /**
     * This method is useful for show the toast, message in chat etc..
     * It will call {@link #complete(Player)} and {@link #revoke(Player)} 1 second later
     *
     * @param player The player instance
     */
    public void show( @NotNull Player player ) {
        complete( player );
        new BukkitRunnable() {
            @Override
            public void run() {
                revoke( player );
            }
        }.runTaskLater( plugin, 20L );
    }

    NamespacedKey getId() {
        return id;
    }

    @Override
    public boolean equals( @NotNull Object o ) {
        if ( this == o ) return true;
        if ( getClass() != o.getClass() ) return false;
        Achievement that = (Achievement) o;
        return id.equals( that.id );
    }

    /**
     * This class is used for build an Advancement
     */
    public static class AchievementBuilder {

        private AchievementManager achievementManager;

        private NamespacedKey id;

        @Expose
        private Display display;

        @Expose
        private String parent;

        @Expose
        private Map<String, Trigger> criteria = new HashMap<>();

        @Expose
        private List<List<String>> requirements = new ArrayList<>();

        @Expose
        private Map<String, Object> rewards = new HashMap<>();

        private Advancement advancement;

        /**
         * This constructor is used for creating an Achievement without display object
         *
         * @param key                The key used for creating a new namespaced key
         * @param achievementManager AchievementManager instance
         */
        AchievementBuilder( @NotNull String key, @NotNull AchievementManager achievementManager ) {
            this.achievementManager = achievementManager;
            this.id = new NamespacedKey( achievementManager.getPlugin(), key );
        }

        /**
         * This constructor is used for create an Achievement using display object
         *
         * @param key                The key used for creating a new namespaced key
         * @param achievementManager AchievementManager instance
         * @param title              The title used by display
         */
        AchievementBuilder( @NotNull String key, @NotNull String title, @NotNull AchievementManager achievementManager ) {
            this( key, achievementManager );
            this.display = new Display( title );
        }

        /**
         * Create/Load this achievement and at it into the manager
         *
         * @return The achievement created
         */
        public Achievement create() {
            /*
              Adding default impossible trigger if trigger list is empty
              to avoid achievement creation error
             */
            if ( criteria.size() == 0 ) {
                criteria.put( "trigger_1", new Trigger( "trigger_1", TriggerType.IMPOSSIBLE ) );
            }
            /*
              Adding default background trigger if background is not set and display is enable
              to avoid achievement without background
             */
            if ( parent == null && display != null && !display.isBackgroundSet() ) {
                display.setBackground( Background.STONE );
            }
            advancement = Bukkit.getAdvancement( id );
            if ( advancement == null ) {
                try {
                    //noinspection deprecation
                    advancement = Bukkit.getUnsafe().loadAdvancement( id, GSON.toJson( this ) );
                } catch ( IllegalArgumentException e ) {
                    Bukkit.getLogger().severe( "Can't load advancement " + id.toString() );
                }
            }
            Achievement achievement = new Achievement( this );
            achievementManager.add( achievement );
            return achievement;
        }

        /**
         * Define the icon of this achievement
         * Work only with builder created with {@link #AchievementBuilder(String, String, AchievementManager)}
         *
         * @param material Material of the icon
         * @return Current builder instance
         */
        public AchievementBuilder withIcon( @NotNull Material material ) {
            if ( display != null )
                display.setIcon( material );
            return this;
        }

        /**
         * Define the description of this achievement
         * Work only with builder created with {@link #AchievementBuilder(String, String, AchievementManager)}
         *
         * @param description Description of this achievement
         * @return Current builder instance
         */
        public AchievementBuilder withDescription( @NotNull String description ) {
            if ( display != null )
                display.setDescription( description );
            return this;
        }

        /**
         * Define the background of this achievement
         * Work only with builder created with {@link #AchievementBuilder(String, String, AchievementManager)}
         *
         * @param background Background of this achievement
         * @return Current builder instance
         */
        public AchievementBuilder withBackground( @NotNull Background background ) {
            if ( display != null )
                display.setBackground( background );
            return this;
        }

        /**
         * Define the frame of this achievement
         * Work only with builder created with {@link #AchievementBuilder(String, String, AchievementManager)}
         *
         * @param frame Frame of this achievement
         * @return Current builder instance
         */
        public AchievementBuilder withFrame( @NotNull Frame frame ) {
            if ( display != null )
                display.setFrame( frame );
            return this;
        }

        /**
         * Define if the achievement is hide or not
         * Work only with builder created with {@link #AchievementBuilder(String, String, AchievementManager)}
         *
         * @param flag Hide or not
         * @return Current builder instance
         */
        public AchievementBuilder isHidden( boolean flag ) {
            if ( display != null )
                display.setHidden( flag );
            return this;
        }

        /**
         * Define if the toast is show when achievement is completed
         * Work only with builder created with {@link #AchievementBuilder(String, String, AchievementManager)}
         *
         * @param flag Show toast or not
         * @return Current builder instance
         */
        public AchievementBuilder isToastVisible( boolean flag ) {
            if ( display != null )
                display.setToast( flag );
            return this;
        }

        /**
         * Define if the message is show in the chat or not
         * Work only with builder created with {@link #AchievementBuilder(String, String, AchievementManager)}
         *
         * @param flag Message send or not
         * @return Current builder instance
         */
        public AchievementBuilder isAnnounceSend( boolean flag ) {
            if ( display != null )
                display.setAnnounce( flag );
            return this;
        }

        /**
         * Define the parent of this achievement using his name
         *
         * @param parent Name of the parent
         * @return Current builder instance
         */
        public AchievementBuilder withParent( @NotNull String parent ) {
            parent = new NamespacedKey( achievementManager.getPlugin(), parent ).toString();
            return this;
        }

        /**
         * Define the parent of this achievement using Achievement instance
         *
         * @param achievement Instance of the parent
         * @return Current builder instance
         */
        public AchievementBuilder withParent( @NotNull Achievement achievement ) {
            parent = achievement.id.toString();
            return this;
        }

        /**
         * Add a trigger to this achievement
         *
         * @param trigger Trigger to add
         * @return Current builder instance
         */
        public AchievementBuilder addTrigger( @NotNull Trigger trigger ) {
            if ( display != null )
                criteria.put( trigger.getName(), trigger );
            return this;
        }

        /**
         * Add a requirement to this achievement
         *
         * @param requirement Requirement to add
         * @return Current builder instance
         */
        public AchievementBuilder addRequirements( @NotNull String... requirement ) {
            if ( display != null )
                requirements.add( Arrays.asList( requirement ) );
            return this;
        }

        /**
         * Add a reward to this achievement
         *
         * @param type   Type of reward
         * @param object The reward
         * @return Current builder instance
         */
        public AchievementBuilder addReward( RewardType type, Object object ) {
            if ( display != null )
                rewards.put( type.name().toLowerCase(), object );
            return this;
        }

    }

}
