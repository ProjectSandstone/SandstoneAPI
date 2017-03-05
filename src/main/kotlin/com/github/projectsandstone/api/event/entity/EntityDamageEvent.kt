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

/**
 * Fired when an entity is damaged
 */
interface EntityDamageEvent : EntityEvent {

    val cause: Cause

    var damage: Double
    val originalDamage: Double

    val modifiers: MutableMap<DamageModifier, Double>
    val originalModifiers: Map<DamageModifier, Double>

    val modifierFunctions: MutableList<Pair<DamageModifier, (Double) -> Double>>
    val originalModifierFunctions: List<Pair<DamageModifier, (Double) -> Double>>

    fun setDamage(damage: Double, modifier: DamageModifier)
    fun getDamage(modifier: DamageModifier): Double
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

    enum class Cause {
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

}