/*
 *      SandstoneAPI - Minecraft Server Modding API
 *
 *         The MIT License (MIT)
 *
 *      Copyright (c) Sandstone <https://github.com/ProjectSandstone/>
 *      Copyright (c) contributors
 *
 *
 *      Permission is hereby granted, free of charge, to any person obtaining a copy
 *      of this software and associated documentation files (the "Software"), to deal
 *      in the Software without restriction, including without limitation the rights
 *      to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *      copies of the Software, and to permit persons to whom the Software is
 *      furnished to do so, subject to the following conditions:
 *
 *      The above copyright notice and this permission notice shall be included in
 *      all copies or substantial portions of the Software.
 *
 *      THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *      IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *      FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *      AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *      LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *      OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *      THE SOFTWARE.
 */
package com.github.projectsandstone.api.event.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import com.github.projectsandstone.eventsys.ap.Factory;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

import static com.github.projectsandstone.api.event.Constants.FACTORY_CLASS;

/**
 * Fired when an entity is damaged.
 */
@Factory(FACTORY_CLASS)
public interface EntityDamageEvent extends EntityEvent {

    /**
     * Gets the cause of damage.
     *
     * @return Cause of damage.
     */
    @NotNull
    DamageType getCause();

    /**
     * Gets the modified/current damage.
     *
     * @return Damage.
     */
    double getDamage();

    /**
     * Gets the original damage.
     *
     * @return Original damage.
     */
    double getOriginalDamage();

    /**
     * Gets a mutable map with damage modifiers.
     *
     * @return Mutable map with damage modifiers.
     */
    Map<DamageModifier, Double> getModifiers();

    /**
     * Gets original damage modifiers.
     *
     * @return Original damage modifiers.
     */
    ImmutableMap<DamageModifier, Double> getOriginalModifiers();

    /**
     * Gets mutable list of modifier functions.
     *
     * @return Mutable list of modifier functions.
     */
    List<DamageFunction> getModifierFunctions();

    /**
     * Gets original modifier functions.
     *
     * @return Original modifier functions.
     */
    ImmutableList<DamageFunction> getOriginalModifierFunctions();

    /**
     * Sets the {@code function} to be applied to {@code modifier}.
     *
     * @param modifier Modifier type.
     * @param function Function to apply.
     */
    void setDamage(DamageModifier modifier, DoubleUnaryOperator function);

    /**
     * Gets the damage for the {@code modifier}.
     *
     * @param modifier Modifier
     * @return Damage.
     */
    double getDamage(DamageModifier modifier);

    /**
     * Returns true if {@code modifier} is applicable to current event.
     *
     * @param modifier Modifier.
     * @return True if {@code modifier} is applicable to current event.
     */
    boolean isApplicable(DamageModifier modifier);

    enum DamageModifier {
        ABSORPTION,
        ARMOR,
        BASE,
        BLOCKING,
        HARD_HAT,
        MAGIC,
        RESISTANCE
    }

    enum DamageType {
        DROWNING,
        CONTACT,
        CRAMMING,
        ENTITY_ATTACK,
        EXPLOSION,
        FALL,
        FIRE,
        KINETIC_ENERGY,
        HOT_FLOOR,
        LAVA,
        MAGIC,
        PROJECTILE,
        SUICIDE,
        VOID,
        WITHER
    }

    interface DamageFunction {
        /**
         * Gets the damage modifier which this function applies to.
         *
         * @return Damage modifier which this function applies to.
         */
        @NotNull
        DamageModifier getModifier();

        /**
         * Gets the function to apply.
         *
         * @return Function to apply.
         */
        @NotNull
        DoubleUnaryOperator getFunction();
    }
}
