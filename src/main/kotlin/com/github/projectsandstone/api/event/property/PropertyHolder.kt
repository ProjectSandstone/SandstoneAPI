/**
 *      SandstoneAPI - Minecraft Server Modding API
 *
 *         The MIT License (MIT)
 *
 *      Copyright (c) 2017 Sandstone <https://github.com/ProjectSandstone/>
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
package com.github.projectsandstone.api.event.property

/**
 * [PropertyHolder] holds information about properties in a class, commonly used in Events,
 * **Sandstone Event Generator** will automatically generate properties.
 */
interface PropertyHolder {
    /**
     * Gets the property of type [type] and name [name]
     *
     * @param type Type of property type.
     * @param name Name of property.
     * @return Property, if exists, or null otherwise.
     */
    fun <R> getProperty(type: Class<R>, name: String): Property<R>?

    /**
     * Gets the getter property of type [type] and name [name]
     *
     * If the property is not a instance of [GetterProperty], returns null.
     *
     * @param type Type of property type.
     * @param name Name of property.
     * @return Getter version of Property, if exists, or null otherwise.
     */
    fun <R> getGetterProperty(type: Class<R>, name: String): GetterProperty<R>? =
            this.getProperty(type, name) as? GetterProperty<R>

    /**
     * Gets the setter property of type [type] and name [name]
     *
     * If the property is not a instance of [SetterProperty], returns null.
     *
     * @param type Type of property type.
     * @param name Name of property.
     * @return Setter version of Property, if exists, or null otherwise.
     */
    fun <R> getSetterProperty(type: Class<R>, name: String): SetterProperty<R>? =
            this.getProperty(type, name) as? SetterProperty<R>

    /**
     * Gets the GS (getter and setter) property of type [type] and name [name]
     *
     * If the property is not a instance of [GSProperty], returns null.
     *
     * @param type Type of property type.
     * @param name Name of property.
     * @return GS version of Property, if exists, or null otherwise.
     */
    fun <R> getGSProperty(type: Class<R>, name: String): GSProperty<R>? =
            this.getProperty(type, name) as? GSProperty<R>

    /**
     * Returns true if the holder has the property.
     *
     * If the holder contains the property but the type of the property isn't instance of [type]
     * the method will returns false.
     *
     * @param type Type of property.
     * @param name Name of property.
     * @return True if the holder has the property.
     */
    fun hasProperty(type: Class<*>, name: String): Boolean

    /**
     * Gets the property map.
     * @return Immutable property map.
     */
    fun getProperties(): Map<String, Property<*>>
}