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
package com.github.projectsandstone.api.scheduler

/**
 * Created by jonathan on 27/08/16.
 */
/**
 * Represents submitted task
 */
interface SubmittedTask {
    /**
     * Submitted task
     */
    val task: Task

    /**
     * Cancel task, the task will be not cancelled immediately if [isRunning] or is not [isSubmitted].
     */
    fun cancel()

    /**
     * True if is task is currently running, false otherwise.
     * @return True if is task is currently running, false otherwise.
     */
    fun isRunning(): Boolean

    /**
     * True if the task is in task list (submitted), false otherwise.
     *
     * If this task is [isRunning], this method will return false.
     *
     * @return True if the task is in task list (submitted), false otherwise.
     */
    fun isSubmitted(): Boolean

    /**
     * True if submitted task is alive (running, or submitted).
     * @return True if submitted task is alive (running, or submitted).
     */
    fun isAlive(): Boolean = this.isRunning() || this.isSubmitted()

    /**
     * Wait the task finish.
     *
     * Tasks is marked as finished after all executions.
     *
     * If this task is a repeating task, this method may sleep infinitely.
     */
    fun waitFinish()
}