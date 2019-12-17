package com.github.projectsandstone.api.feature

/**
 * Manager of available features.
 */
interface FeatureManager {

    /**
     * Provides a feature of [type] [T] and returns replaced feature if present.
     */
    fun <T: Feature<T>> provide(type: Class<T>,
                                feature: T): T?

    /**
     * Drops a provided feature of [type] [T] and returns dropped feature if present.
     */
    fun <T: Feature<T>> drop(type: Class<T>): T?

    /**
     * Gets a provided feature of [type] [T].
     */
    operator fun <T: Feature<T>> get(type: Class<T>): Feature<T>
}