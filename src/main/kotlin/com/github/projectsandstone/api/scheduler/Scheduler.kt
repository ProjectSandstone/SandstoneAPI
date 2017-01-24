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

import java.time.Duration

/**
 * Created by jonathan on 27/08/16.
 */
interface Scheduler {
    /**
     * Create a [Task]
     *
     * @param plugin Plugin that created the task
     * @param name Name of the task
     * @param delay Time to pass before the task start
     * @param interval Interval of task repetition. If [Duration.ZERO], this task will not repeat.
     * @param isAsync True if the task run asynchronous, false otherwise.
     * @param runnable [Runnable] to be invoked.
     * @return Configured [Task]
     */
    fun createTask(plugin: Any,
                   name: String? = null,
                   delay: Duration,
                   interval: Duration = DEFAULT_DURATION,
                   isAsync: Boolean = false,
                   runnable: Runnable): Task

    /**
     * Create a [Task] and [submit]
     *
     * @param plugin Plugin
     * @param name Name of the task
     * @param delay Time to pass before the task start
     * @param interval Interval of task repetition. If [Duration.ZERO], this task will not repeat.
     * @param isAsync True if the task run asynchronous, false otherwise.
     * @param runnable [Runnable] to be invoked.
     * @return [SubmittedTask]
     */
    fun createAndSubmit(plugin: Any,
                        name: String? = null,
                        delay: Duration,
                        interval: Duration = DEFAULT_DURATION,
                        isAsync: Boolean = false,
                        runnable: Runnable): SubmittedTask {
        return this.submit(this.createTask(plugin, name, delay, interval, isAsync, runnable))
    }

    /**
     * Submit the task
     *
     * @param task Task to submit
     * @return Representation of a submitted task.
     */
    fun submit(task: Task): SubmittedTask

    /**
     * Create Async [SandstoneExecutorService]
     *
     * @return Async [SandstoneExecutorService]
     */
    fun createAsyncExecutor(plugin: Any): SandstoneExecutorService

    /**
     * Create Sync [SandstoneExecutorService]
     *
     * @return Sync [SandstoneExecutorService]
     */
    fun createSyncExecutor(plugin: Any): SandstoneExecutorService

    companion object {
        val DEFAULT_DURATION = Duration.ZERO
    }
}