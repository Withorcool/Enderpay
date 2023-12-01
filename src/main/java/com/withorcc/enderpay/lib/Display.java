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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

/**
 * Class representation of the display json object
 *
 * @author Frozen
 */
class Display {

    @Expose
    private String background;

    @Expose
    private Icon icon = new Icon( Material.STONE );

    @Expose
    private String title;

    @Expose
    private String description = "";

    @Expose
    private String frame = Frame.TASK.toString();

    @Expose
    @SerializedName( "announce_to_chat" )
    private boolean announce = true;

    @Expose
    private boolean hidden = false;

    @Expose
    @SerializedName( "show_toast" )
    private boolean toast = true;

    /**
     * Default constructor
     *
     * @param title Title of the display
     */
    Display( @NotNull String title ) {
        this.title = title;
    }

    /**
     * Set the background of the display
     *
     * @param background Background of the display
     */
    void setBackground( @NotNull Background background ) {
        this.background = background.toString();
    }

    /**
     * Set the icon of the display
     *
     * @param material Material of the icon
     */
    void setIcon( @NotNull Material material ) {
        this.icon = new Icon( material );
    }

    /**
     * Set the title of the display
     *
     * @param title Title of the display
     */
    void setTitle( @NotNull String title ) {
        this.title = title;
    }

    /**
     * Set the description of the display
     *
     * @param description The description of the display
     */
    void setDescription( @NotNull String description ) {
        this.description = description;
    }

    /**
     * Set the frame of the display
     *
     * @param frame Frame of the display
     */
    void setFrame( @NotNull Frame frame ) {
        this.frame = frame.toString();
    }

    /**
     * Set the announce value of the display
     *
     * @param announce Announce value of the display
     */
    void setAnnounce( boolean announce ) {
        this.announce = announce;
    }

    /**
     * Set the hidden value of the display
     *
     * @param hidden Hidden value of the display
     */
    void setHidden( boolean hidden ) {
        this.hidden = hidden;
    }

    /**
     * Set the toast value of the display
     *
     * @param toast Toast value of the display
     */
    void setToast( boolean toast ) {
        this.toast = toast;
    }

    /**
     * Check if the background is set or not
     *
     * @return Background set or not
     */
    boolean isBackgroundSet() {
        return background != null;
    }

}