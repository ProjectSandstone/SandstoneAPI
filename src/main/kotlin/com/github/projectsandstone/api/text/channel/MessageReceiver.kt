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
package com.github.projectsandstone.api.text.channel

import com.github.projectsandstone.api.text.Text

/**
 * Created by jonathan on 28/08/16.
 */
interface MessageReceiver {

    /**
     * Send a [Text] to this [MessageReceiver]
     *
     * @param text Message to send
     */
    fun sendMessage(text: Text)

    /**
     * Send multiple [Text]s to this [MessageReceiver]
     *
     * @param text [Text]s to send
     */
    fun sendMessages(vararg text: Text) {
        text.forEach {
            sendMessage(it)
        }
    }

    /**
     * Send multiple [Text]s to this [MessageReceiver]
     *
     * @param text [Text]s to send
     */
    fun sendMessages(text: Iterable<Text>) {
        text.forEach {
            sendMessage(it)
        }
    }

    /**
     * Gets the current message channel.
     *
     * @return Current message channel.
     */
    fun getMessageChannel(): MessageChannel? = null

    /**
     * Sets current message channel.
     *
     * @param channel Current message channel.
     */
    fun setMessageChannel(channel: MessageChannel?) {

    }

}