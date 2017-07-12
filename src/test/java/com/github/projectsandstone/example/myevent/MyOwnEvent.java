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
package com.github.projectsandstone.example.myevent;

import com.github.jonathanxd.iutils.type.TypeInfo;
import com.github.projectsandstone.api.event.JavaEvent;
import com.github.projectsandstone.eventsys.event.Event;
import com.github.projectsandstone.eventsys.event.property.GetterProperty;
import com.github.projectsandstone.eventsys.event.property.Property;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyOwnEvent implements JavaEvent {

    private final Map<String, Property<?>> propertyMap = new HashMap<>();
    private final Map<String, Property<?>> unmodPropertyMap = Collections.unmodifiableMap(this.propertyMap);
    private final MyObj myObj;

    public MyOwnEvent(MyObj myObj) {
        this.myObj = myObj;
        this.propertyMap.put("myObj", new GetterProperty.Impl<>(MyObj.class, this::getMyObj));
    }

    public MyObj getMyObj() {
        return this.myObj;
    }

    @NotNull
    @Override
    public TypeInfo<? extends Event> getEventTypeInfo() {
        return TypeInfo.of(MyOwnEvent.class);
    }

    @NotNull
    @Override
    public Map<String, Property<?>> getProperties() {
        return this.unmodPropertyMap;
    }
}
