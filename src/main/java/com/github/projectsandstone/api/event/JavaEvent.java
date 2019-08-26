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
package com.github.projectsandstone.api.event;

import com.github.jonathanxd.iutils.type.TypeInfo;
import com.github.koresframework.eventsys.event.Event;
import com.github.koresframework.eventsys.event.property.GSProperty;
import com.github.koresframework.eventsys.event.property.GetterProperty;
import com.github.koresframework.eventsys.event.property.Property;
import com.github.koresframework.eventsys.event.property.SetterProperty;
import com.github.koresframework.eventsys.event.property.primitive.BooleanGSProperty;
import com.github.koresframework.eventsys.event.property.primitive.BooleanGetterProperty;
import com.github.koresframework.eventsys.event.property.primitive.BooleanProperty;
import com.github.koresframework.eventsys.event.property.primitive.BooleanSetterProperty;
import com.github.koresframework.eventsys.event.property.primitive.DoubleGSProperty;
import com.github.koresframework.eventsys.event.property.primitive.DoubleGetterProperty;
import com.github.koresframework.eventsys.event.property.primitive.DoubleProperty;
import com.github.koresframework.eventsys.event.property.primitive.DoubleSetterProperty;
import com.github.koresframework.eventsys.event.property.primitive.IntGSProperty;
import com.github.koresframework.eventsys.event.property.primitive.IntGetterProperty;
import com.github.koresframework.eventsys.event.property.primitive.IntProperty;
import com.github.koresframework.eventsys.event.property.primitive.IntSetterProperty;
import com.github.koresframework.eventsys.event.property.primitive.LongGSProperty;
import com.github.koresframework.eventsys.event.property.primitive.LongGetterProperty;
import com.github.koresframework.eventsys.event.property.primitive.LongProperty;
import com.github.koresframework.eventsys.event.property.primitive.LongSetterProperty;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Provides {@code Java 8 default methods} for Kotlin {@link Event} interface.
 */
public interface JavaEvent extends Event {

    /**
     * This is used by EventSys to correctly dispatch generic events.
     *
     * {@inheritDoc}
     */
    @NotNull
    @Override
    Type getEventType();

    @NotNull
    @Override
    Map<String, Property<?>> getProperties();

    @Nullable
    @Override
    default <R> Property<R> lookup(Class<R> aClass, String s) {
        return Event.DefaultImpls.lookup(this, aClass, s);
    }

    @Nullable
    @Override
    default <R> Property<R> getProperty(Class<R> aClass, String s) {
        return Event.DefaultImpls.getProperty(this, aClass, s);
    }

    @Nullable
    @Override
    default <R> GetterProperty<R> getGetterProperty(Class<R> aClass, String s) {
        return Event.DefaultImpls.getGetterProperty(this, aClass, s);
    }

    @Nullable
    @Override
    default <R> SetterProperty<R> getSetterProperty(Class<R> aClass, String s) {
        return Event.DefaultImpls.getSetterProperty(this, aClass, s);
    }

    @Nullable
    @Override
    default <R> GSProperty<R> getGSProperty(Class<R> aClass, String s) {
        return Event.DefaultImpls.getGSProperty(this, aClass, s);
    }

    @Override
    default boolean hasProperty(Class<?> aClass, String s) {
        return Event.DefaultImpls.hasProperty(this, aClass, s);
    }

    @Nullable
    @Override
    default BooleanProperty getBooleanProperty(String s) {
        return Event.DefaultImpls.getBooleanProperty(this, s);
    }

    @Nullable
    @Override
    default BooleanGetterProperty getBooleanGetterProperty(String s) {
        return Event.DefaultImpls.getBooleanGetterProperty(this, s);
    }

    @Nullable
    @Override
    default BooleanSetterProperty getBooleanSetterProperty(String s) {
        return Event.DefaultImpls.getBooleanSetterProperty(this, s);
    }

    @Nullable
    @Override
    default BooleanGSProperty getBooleanGSProperty(String s) {
        return Event.DefaultImpls.getBooleanGSProperty(this, s);
    }

    @Nullable
    @Override
    default DoubleProperty getDoubleProperty(String s) {
        return Event.DefaultImpls.getDoubleProperty(this, s);
    }

    @Nullable
    @Override
    default DoubleGetterProperty getDoubleGetterProperty(String s) {
        return Event.DefaultImpls.getDoubleGetterProperty(this, s);
    }

    @Nullable
    @Override
    default DoubleSetterProperty getDoubleSetterProperty(String s) {
        return Event.DefaultImpls.getDoubleSetterProperty(this, s);
    }

    @Nullable
    @Override
    default DoubleGSProperty getDoubleGSProperty(String s) {
        return Event.DefaultImpls.getDoubleGSProperty(this, s);
    }

    @Nullable
    @Override
    default IntProperty getIntProperty(String s) {
        return Event.DefaultImpls.getIntProperty(this, s);
    }

    @Nullable
    @Override
    default IntGetterProperty getIntGetterProperty(String s) {
        return Event.DefaultImpls.getIntGetterProperty(this, s);
    }

    @Nullable
    @Override
    default IntSetterProperty getIntSetterProperty(String s) {
        return Event.DefaultImpls.getIntSetterProperty(this, s);
    }

    @Nullable
    @Override
    default IntGSProperty getIntGSProperty(String s) {
        return Event.DefaultImpls.getIntGSProperty(this, s);
    }

    @Nullable
    @Override
    default LongProperty getLongProperty(String s) {
        return Event.DefaultImpls.getLongProperty(this, s);
    }

    @Nullable
    @Override
    default LongGetterProperty getLongGetterProperty(String s) {
        return Event.DefaultImpls.getLongGetterProperty(this, s);
    }

    @Nullable
    @Override
    default LongSetterProperty getLongSetterProperty(String s) {
        return Event.DefaultImpls.getLongSetterProperty(this, s);
    }

    @Nullable
    @Override
    default LongGSProperty getLongGSProperty(String s) {
        return Event.DefaultImpls.getLongGSProperty(this, s);
    }

    @Nullable
    @Override
    default <T> T getExtension(Class<T> aClass) {
        return Event.DefaultImpls.getExtension(this, aClass);
    }
}
