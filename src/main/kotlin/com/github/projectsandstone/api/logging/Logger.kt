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
package com.github.projectsandstone.api.logging

/**
 * Logging interface.
 */
interface Logger {

    /**
     * Log a formatted message to console.
     *
     * @param level Log Level
     * @param format String format, see [java.util.Formatter]
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     */
    fun log(level: LogLevel, format: String, vararg objects: Any)

    /**
     * Log a plain string message to console.
     *
     * @param level Log Level
     * @param message Message to log
     */
    fun log(level: LogLevel, message: String)

    /**
     * Log plain string message and exception to console.
     *
     * @param level Log Level
     * @param exception Exception to log to console
     * @param message Message to log before [exception]
     */
    fun log(level: LogLevel, exception: Exception, message: String)

    /**
     * Log exception to console.
     *
     * @param level Log Level
     * @param exception Exception to log to console
     */
    fun log(level: LogLevel, exception: Exception)

    /**
     * Log formatted message and exception
     *
     * @param level Log Level
     * @param exception Exception to log to console
     * @param format String format, see [java.util.Formatter]. Log before [exception].
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     */
    fun log(level: LogLevel, exception: Exception, format: String, vararg objects: Any)

    //

    /**
     * Log formatted message to console.
     *
     * Level [LogLevel.DEBUG].
     *
     * @param format String format, see [java.util.Formatter]
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     * @see [log]
     */
    fun debug(format: String, vararg objects: Any) {
        this.log(level = LogLevel.DEBUG, format = format, objects = *objects)
    }

    /**
     * Log plain string message to console.
     *
     * Level [LogLevel.DEBUG].
     *
     * @param message Message to log
     * @see [log]
     */
    fun debug(message: String) {
        this.log(level = LogLevel.DEBUG, message = message)
    }

    /**
     * Log message and exception to console.
     *
     * Level [LogLevel.DEBUG].
     *
     * @param exception Exception to log to console
     * @param message Message to log before [exception]
     * @see [log]
     */
    fun debug(exception: Exception, message: String) {
        this.log(level = LogLevel.DEBUG, exception = exception, message = message)
    }

    /**
     * Log exception to console.
     *
     * Level [LogLevel.DEBUG].
     *
     * @param exception Exception to log to console
     * @see [log]
     */
    fun debug(exception: Exception) {
        this.log(level = LogLevel.DEBUG, exception = exception)
    }

    /**
     * Log formatted string and exception to console.
     *
     * Level [LogLevel.DEBUG].
     *
     * @param exception Exception to log to console
     * @param format String format, see [java.util.Formatter]. Log before [exception].
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     * @see [log]
     */
    fun debug(exception: Exception, format: String, vararg objects: Any) {
        this.log(level = LogLevel.DEBUG, exception = exception, format = format, objects = *objects)
    }

    //

    /**
     * Log formatted message to console.
     *
     * Level [LogLevel.INFO].
     *
     * @param format String format, see [java.util.Formatter]
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     * @see [log]
     */
    fun info(format: String, vararg objects: Any) {
        this.log(level = LogLevel.INFO, format = format, objects = *objects)
    }

    /**
     * Log plain string message to console.
     *
     * Level [LogLevel.INFO].
     *
     * @param message Message to log
     * @see [log]
     */
    fun info(message: String) {
        this.log(level = LogLevel.INFO, message = message)
    }

    /**
     * Log plain string message and exception to console.
     *
     * Level [LogLevel.INFO].
     *
     * @param exception Exception to log to console
     * @param message Message to log before [exception]
     * @see [log]
     */
    fun info(exception: Exception, message: String) {
        this.log(level = LogLevel.INFO, exception = exception, message = message)
    }

    /**
     * Log exception to console.
     *
     * Level [LogLevel.INFO].
     *
     * @param exception Exception to log to console
     * @see [log]
     */
    fun info(exception: Exception) {
        this.log(level = LogLevel.INFO, exception = exception)
    }

    /**
     * Log formatted string and exception to console.
     *
     * Level [LogLevel.INFO].
     *
     * @param exception Exception to log to console
     * @param format String format, see [java.util.Formatter]. Log before [exception].
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     * @see [log]
     */
    fun info(exception: Exception, format: String, vararg objects: Any) {
        this.log(level = LogLevel.INFO, exception = exception, format = format, objects = *objects)
    }


    //

    /**
     * Log formatted string to console.
     *
     * Level [LogLevel.WARN].
     *
     * @param format String format, see [java.util.Formatter]
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     * @see [log]
     */
    fun warn(format: String, vararg objects: Any) {
        this.log(level = LogLevel.WARN, format = format, objects = *objects)
    }

    /**
     * Log plain string message to console.
     *
     * Level [LogLevel.WARN].
     *
     * @param message Message to log
     * @see [log]
     */
    fun warn(message: String) {
        this.log(level = LogLevel.WARN, message = message)
    }

    /**
     * Log formatted string to console.
     *
     * Level [LogLevel.WARN].
     *
     * @param exception Exception to log to console
     * @param message Message to log before [exception]
     * @see [log]
     */
    fun warn(exception: Exception, message: String) {
        this.log(level = LogLevel.WARN, exception = exception, message = message)
    }

    /**
     * Log exception to console.
     *
     * Level [LogLevel.WARN].
     *
     * @param exception Exception to log to console
     * @see [log]
     */
    fun warn(exception: Exception) {
        this.log(level = LogLevel.WARN, exception = exception)
    }

    /**
     * Log formatted string and exception to console.
     *
     * Level [LogLevel.WARN].
     *
     * @param exception Exception to log to console
     * @param format String format, see [java.util.Formatter]. Log before [exception].
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     * @see [log]
     */
    fun warn(exception: Exception, format: String, vararg objects: Any) {
        this.log(level = LogLevel.WARN, exception = exception, format = format, objects = *objects)
    }

    //

    /**
     * Log formatted string message to console.
     *
     * Level [LogLevel.ERROR].
     *
     * @param format String format, see [java.util.Formatter]
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     * @see [log]
     */
    fun error(format: String, vararg objects: Any) {
        this.log(level = LogLevel.ERROR, format = format, objects = *objects)
    }

    /**
     * Log plain string message to console.
     *
     * Level [LogLevel.ERROR].
     *
     * @param message Message to log
     * @see [log]
     */
    fun error(message: String) {
        this.log(level = LogLevel.ERROR, message = message)
    }

    /**
     *
     * Log message and exception to console.
     *
     * Level [LogLevel.ERROR].
     *
     * @param exception Exception to log to console
     * @param message Message to log before [exception]
     * @see [log]
     */
    fun error(exception: Exception, message: String) {
        this.log(level = LogLevel.ERROR, exception = exception, message = message)
    }

    /**
     * Log exception to console.
     *
     * Level [LogLevel.ERROR].
     *
     * @param exception Exception to log to console
     * @see [log]
     */
    fun error(exception: Exception) {
        this.log(level = LogLevel.ERROR, exception = exception)
    }

    /**
     * Log formatted message and exception to console.
     *
     * Level [LogLevel.ERROR].
     *
     * @param exception Exception to log to console
     * @param format String format, see [java.util.Formatter]. Log before [exception].
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     * @see [log]
     */
    fun error(exception: Exception, format: String, vararg objects: Any) {
        this.log(level = LogLevel.ERROR, exception = exception, format = format, objects = *objects)
    }

    //

    /**
     * Log formatted message to console.
     *
     * Level [LogLevel.EXCEPTION].
     *
     * @param format String format, see [java.util.Formatter]
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     * @see [log]
     */
    fun exception(format: String, vararg objects: Any) {
        this.log(level = LogLevel.EXCEPTION, format = format, objects = *objects)
    }

    /**
     * Log plain string message to console.
     *
     * Level [LogLevel.EXCEPTION].
     *
     * @param message Message to log
     * @see [log]
     */
    fun exception(message: String) {
        this.log(level = LogLevel.EXCEPTION, message = message)
    }

    /**
     * Log formatted string and exception to console.
     *
     * Level [LogLevel.EXCEPTION].
     *
     * @param exception Exception to log to console
     * @param message Message to log before [exception]
     * @see [log]
     */
    fun exception(exception: Exception, message: String) {
        this.log(level = LogLevel.EXCEPTION, exception = exception, message = message)
    }

    /**
     * Log exception to console.
     *
     * Level [LogLevel.EXCEPTION].
     *
     * @param exception Exception to log to console
     * @see [log]
     */
    fun exception(exception: Exception) {
        this.log(level = LogLevel.EXCEPTION, exception = exception)
    }

    /**
     * Log formatted message and exception to console.
     *
     * Level [LogLevel.EXCEPTION].
     *
     * @param exception Exception to log to console
     * @param format String format, see [java.util.Formatter]. Log before [exception].
     * @param objects Elements to be inserted in [format] string. see [java.util.Formatter]
     * @see [log]
     */
    fun exception(exception: Exception, format: String, vararg objects: Any) {
        this.log(level = LogLevel.EXCEPTION, exception = exception, format = format, objects = *objects)
    }
}