package com.github.projectsandstone.api.feature

import com.github.projectsandstone.api.util.exception.FeatureNotAvailableException

/**
 * Feature interface proxies communication between Sandstone feature and feature implementation.
 *
 * All invocations of feature functions is proxied to the implementation.
 */
interface Feature<T: Feature<T>> {
    /**
     * Is feature available
     */
    val isAvailable: Boolean

    /**
     * Gets the feature implementation value.
     */
    @get:Throws(FeatureNotAvailableException::class)
    val get: T
}