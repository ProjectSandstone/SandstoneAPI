/**
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
package com.github.projectsandstone.api.event

import com.github.projectsandstone.api.event.annotation.Name
import com.github.projectsandstone.api.event.property.PropertyHolder
import com.github.projectsandstone.api.event.service.ChangeServiceProviderEvent
import com.github.projectsandstone.api.util.internal.gen.event.SandstoneEventGen

/**
 * [Event].
 *
 * Has two ways to implement a event, first is creating your own interface that inherit [Event],
 * and creating abstract property methods (getters and setters) and calling [SandstoneEventGen.gen].
 * The second is: Implementing event in an concrete class, naming constructor parameters with [Name]
 * annotation and mapping properties to getter and setter (if the property is mutable).
 *
 * The second way is not recommended.
 *
 * Example of a event interface with properties: [ChangeServiceProviderEvent].
 *
 * An [Event] may have additional properties, events with additional properties have different
 * class from events without these additional properties, different additional properties generates
 * different event classes, this happens because [SandstoneEventGen] create fields for additional properties
 * and JVM doesn't allow to add fields to existing classes (this is the nature of static languages).
 */
interface Event : PropertyHolder