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
package com.github.projectsandstone.api.util

import java.util.*

/**
 * Sandstone Identification (SID). The Sandstone Identification may not be persistent between restarts,
 * this identification is used to identify game elements, like entities, players, chunks, world, etc...
 *
 * [SID] is always persistent for Players, for other game elements, it may not be persistent.
 *
 * The persistence depends on the Platform.
 */
sealed class SID : Comparable<SID> {

    data class IntSid(val value: Int) : SID() {
        override fun compareTo(other: SID): Int =
                if (other is IntSid) {
                    this.value.compareTo(other.value)
                } else {
                    -1
                }

        override fun hashCode(): Int = this.value.hashCode()
        override fun equals(other: Any?): Boolean = other != null && other is IntSid && this.value == other.value
        override fun toString(): String = this.value.toString()
    }

    data class LongSid(val value: Long) : SID() {
        override fun compareTo(other: SID): Int =
                if (other is LongSid) {
                    this.value.compareTo(other.value)
                } else {
                    -1
                }


        override fun hashCode(): Int = this.value.hashCode()
        override fun equals(other: Any?): Boolean = other != null && other is LongSid && this.value == other.value
        override fun toString(): String = this.value.toString()
    }

    data class DoubleSid(val value: Double) : SID() {
        override fun compareTo(other: SID): Int =
                if (other is DoubleSid) {
                    this.value.compareTo(other.value)
                } else {
                    -1
                }

        override fun hashCode(): Int = this.value.hashCode()
        override fun equals(other: Any?): Boolean = other != null && other is DoubleSid && this.value == other.value
        override fun toString(): String = this.value.toString()
    }

    data class StringSid(val string: String) : SID() {
        override fun compareTo(other: SID): Int =
                if (other is StringSid) {
                    this.string.compareTo(other.string)
                } else {
                    -1
                }

        override fun hashCode(): Int = this.string.hashCode()
        override fun equals(other: Any?): Boolean = other != null && other is StringSid && this.string == other.string
        override fun toString(): String = this.string

    }

    data class UuidSid(val uuid: UUID) : SID() {
        override fun compareTo(other: SID): Int =
                if (other is UuidSid) {
                    this.uuid.compareTo(other.uuid)
                } else {
                    -1
                }

        override fun hashCode(): Int = this.uuid.hashCode()
        override fun equals(other: Any?): Boolean = other != null && other is UuidSid && this.uuid == other.uuid
        override fun toString(): String = this.uuid.toString()

    }

    abstract override fun hashCode(): Int
    abstract override fun equals(other: Any?): Boolean
    abstract override fun toString(): String

    companion object {
        /**
         * Convert [Int] to Sandstone Id ([SID])
         */
        @JvmStatic
        fun Int.sid() = IntSid(this)

        /**
         * Convert [Long] to Sandstone Id ([SID])
         */
        @JvmStatic
        fun Long.sid() = LongSid(this)

        /**
         * Convert [Double] to Sandstone Id ([SID])
         */
        @JvmStatic
        fun Double.sid() = DoubleSid(this)

        /**
         * Convert [String] to Sandstone Id ([SID])
         */
        @JvmStatic
        fun String.sid() = StringSid(this)

        /**
         * Convert [UUID] to Sandstone Id ([SID])
         */
        @JvmStatic
        fun UUID.sid() = UuidSid(this)
    }
}