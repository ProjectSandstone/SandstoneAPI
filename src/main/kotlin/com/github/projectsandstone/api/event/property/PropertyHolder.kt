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
package com.github.projectsandstone.api.event.property

import com.github.projectsandstone.api.event.property.primitive.*

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
    fun <R> getProperty(type: Class<R>, name: String): Property<R>? {
        val property = this.getProperties()[name]
        // type.isAssignableFrom(propertyType)
        fun checkTypeCompatibility(propType: Class<*>, reqType: Class<*>) =
                reqType.isAssignableFrom(propType) ||
                        ((reqType == java.lang.Byte.TYPE
                                || reqType == java.lang.Short.TYPE
                                || reqType == java.lang.Character.TYPE
                                || reqType == java.lang.Integer.TYPE)
                                && propType == java.lang.Integer.TYPE)
                        || ((reqType == java.lang.Double.TYPE
                        || reqType == java.lang.Float.TYPE)
                        && propType == java.lang.Double.TYPE)

        @Suppress("UNCHECKED_CAST")
        return if (property != null && checkTypeCompatibility(property.type, type)) property as Property<R> else null
    }

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
    fun hasProperty(type: Class<*>, name: String): Boolean = this.getProperty(type, name) != null

    /**
     * Gets the property map.
     * @return Immutable property map.
     */
    fun getProperties(): Map<String, Property<*>>


    // Primitive versions

    // Boolean

    /**
     * Boolean property.
     *
     * [getProperty]
     */
    fun getBooleanProperty(name: String): BooleanProperty? =
            this.getProperty(java.lang.Boolean.TYPE, name) as? BooleanProperty

    /**
     * Boolean property.
     *
     * [getGetterProperty]
     */
    fun getBooleanGetterProperty(name: String): BooleanGetterProperty? =
            this.getProperty(java.lang.Boolean.TYPE, name) as? BooleanGetterProperty

    /**
     * Boolean property.
     *
     * [getSetterProperty]
     */
    fun getBooleanSetterProperty(name: String): BooleanSetterProperty? =
            this.getProperty(java.lang.Boolean.TYPE, name) as? BooleanSetterProperty

    /**
     * Boolean property.
     *
     * [getGSProperty]
     */
    fun getBooleanGSProperty(name: String): BooleanGSProperty? =
            this.getProperty(java.lang.Boolean.TYPE, name) as? BooleanGSProperty

    // Double

    /**
     * Double property.
     *
     * [getProperty]
     */
    fun getDoubleProperty(name: String): DoubleProperty? =
            this.getProperty(java.lang.Double.TYPE, name) as? DoubleProperty

    /**
     * Double property.
     *
     * [getGetterProperty]
     */
    fun getDoubleGetterProperty(name: String): DoubleGetterProperty? =
            this.getProperty(java.lang.Double.TYPE, name) as? DoubleGetterProperty

    /**
     * Double property.
     *
     * [getSetterProperty]
     */
    fun getDoubleSetterProperty(name: String): DoubleSetterProperty? =
            this.getProperty(java.lang.Double.TYPE, name) as? DoubleSetterProperty

    /**
     * Double property.
     *
     * [getGSProperty]
     */
    fun getDoubleGSProperty(name: String): DoubleGSProperty? =
            this.getProperty(java.lang.Double.TYPE, name) as? DoubleGSProperty

    // Int

    /**
     * Int property.
     *
     * [getProperty]
     */
    fun getIntProperty(name: String): IntProperty? =
            this.getProperty(java.lang.Integer.TYPE, name) as? IntProperty

    /**
     * Int property.
     *
     * [getGetterProperty]
     */
    fun getIntGetterProperty(name: String): IntGetterProperty? =
            this.getProperty(java.lang.Integer.TYPE, name) as? IntGetterProperty

    /**
     * Int property.
     *
     * [getSetterProperty]
     */
    fun getIntSetterProperty(name: String): IntSetterProperty? =
            this.getProperty(java.lang.Integer.TYPE, name) as? IntSetterProperty

    /**
     * Int property.
     *
     * [getGSProperty]
     */
    fun getIntGSProperty(name: String): IntGSProperty? =
            this.getProperty(java.lang.Integer.TYPE, name) as? IntGSProperty

    // Long

    /**
     * Long property.
     *
     * [getProperty]
     */
    fun getLongProperty(name: String): LongProperty? =
            this.getProperty(java.lang.Long.TYPE, name) as? LongProperty

    /**
     * Long property.
     *
     * [getGetterProperty]
     */
    fun getLongGetterProperty(name: String): LongGetterProperty? =
            this.getProperty(java.lang.Long.TYPE, name) as? LongGetterProperty

    /**
     * Long property.
     *
     * [getSetterProperty]
     */
    fun getLongSetterProperty(name: String): LongSetterProperty? =
            this.getProperty(java.lang.Long.TYPE, name) as? LongSetterProperty

    /**
     * Long property.
     *
     * [getGSProperty]
     */
    fun getLongGSProperty(name: String): LongGSProperty? =
            this.getProperty(java.lang.Long.TYPE, name) as? LongGSProperty

}