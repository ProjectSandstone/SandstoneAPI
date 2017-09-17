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
package com.github.projectsandstone.api.event.entity

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap

import com.github.projectsandstone.eventsys.ap.Factory
import java.util.function.DoubleUnaryOperator

import com.github.projectsandstone.api.event.Constants.FACTORY_CLASS

/**
 * Fired when an entity is damaged.
 */
@Factory(FACTORY_CLASS)
interface EntityDamageEvent : EntityEvent {

    /**
     * Cause of damage.
     */
    val cause: DamageType

    /**
     * Modified/current damage.
     */
    val damage: Double

    /**
     * Original damage.
     */
    val originalDamage: Double

    /**
     * Mutable map with damage modifiers.
     */
    val modifiers: Map<DamageModifier, Double>

    /**
     * Original damage modifiers.
     */
    val originalModifiers: ImmutableMap<DamageModifier, Double>

    /**
     * Mutable list of modifier functions.
     */
    val modifierFunctions: List<DamageFunction>

    /**
     * Original modifier functions.
     */
    val originalModifierFunctions: ImmutableList<DamageFunction>

    /**
     * Sets the `function` to be applied to `modifier`.
     *
     * @param modifier Modifier type.
     * @param function Function to apply.
     */
    fun setDamage(modifier: DamageModifier, function: DoubleUnaryOperator)

    /**
     * Gets the damage for the `modifier`.
     *
     * @param modifier Modifier
     * @return Damage.
     */
    fun getDamage(modifier: DamageModifier): Double

    /**
     * Returns true if `modifier` is applicable to current event.
     *
     * @param modifier Modifier.
     * @return True if `modifier` is applicable to current event.
     */
    fun isApplicable(modifier: DamageModifier): Boolean

    enum class DamageModifier {
        ABSORPTION,
        ARMOR,
        BASE,
        BLOCKING,
        HARD_HAT,
        MAGIC,
        RESISTANCE
    }

    enum class DamageType {
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
        val modifier: DamageModifier

        /**
         * Gets the function to apply.
         *
         * @return Function to apply.
         */
        val function: DoubleUnaryOperator
    }
}
