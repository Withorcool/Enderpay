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
import org.apache.commons.lang.NotImplementedException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representation of a trigger json object
 *
 * @author Frozen
 */
public class Trigger {

    private String name;

    @Expose
    private String trigger;

    private Map<String, Object> conditions = new HashMap<>();

    /**
     * Default constructor
     *
     * @param name    Name of the trigger
     * @param trigger Type of the trigger
     */
    public Trigger( @NotNull String name, @NotNull TriggerType trigger ) {
        this.name = name;
        this.trigger = trigger.toString();
    }

    /**
     * Add a condition to this trigger
     *
     * @param name  Name of this condition
     * @param value Value of this condition
     * @return Current instance
     */
    public Trigger addCondition( @NotNull String name, @NotNull Object value ) {
        throw new NotImplementedException( "The conditions is not implemented yet" );
    }

    /**
     * Get the name of this trigger
     *
     * @return The name of this trigger
     */
    public String getName() {
        return name;
    }

}
