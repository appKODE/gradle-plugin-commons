package ru.kode.android.gradle.commons.api.extension

import groovy.lang.Closure
import groovy.lang.DelegatesTo
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import ru.kode.android.gradle.commons.api.container.PluginDomainObjectContainer
import ru.kode.android.gradle.commons.util.MergeStrategy
import ru.kode.android.gradle.commons.util.buildVariant
import ru.kode.android.gradle.commons.util.common

/**
 * Base class for configurable extensions in a Gradle plugin.
 *
 * This abstract class serves as the foundation for creating configurable extensions that can be used
 * to customize a build process for different build variants. It provides a consistent
 * way to apply common configurations and build-type specific settings through a type-safe DSL.
 *
 * Key features:
 * - Common configuration that applies to all build variants
 * - Build-type specific configuration (e.g., debug, release)
 * - Support for both standard Gradle containers and custom domain object containers
 * - Type-safe configuration through generic type parameters
 *
 * @see PluginDomainObjectContainer For the custom container implementation used by this extension
 * @see NamedDomainObjectContainer For the standard Gradle container interface
 */
open class PluginConfigurableExtension {
    protected fun <T : Any> common(
        container: NamedDomainObjectContainer<T>,
        configurationAction: Action<in T>,
    ) {
        container.common(configurationAction)
    }

    protected fun <T : Any> common(
        container: NamedDomainObjectContainer<T>,
        @DelegatesTo(
            genericTypeIndex = 0,
            strategy = Closure.DELEGATE_FIRST,
        )
        configurationClosure: Closure<in T>,
    ) {
        container.common(configurationClosure)
    }

    protected fun <T : Any> common(
        container: PluginDomainObjectContainer<T>,
        configurationAction: Action<in T>,
    ) {
        container.common(configurationAction)
    }

    protected fun <T : Any> common(
        container: PluginDomainObjectContainer<T>,
        @DelegatesTo(
            genericTypeIndex = 0,
            strategy = Closure.DELEGATE_FIRST,
        )
        configurationClosure: Closure<in T>,
    ) {
        container.common(configurationClosure)
    }

    protected fun <T : Any> buildVariant(
        buildVariant: String,
        container: NamedDomainObjectContainer<T>,
        strategy: MergeStrategy = MergeStrategy.MERGE,
        configurationAction: Action<in T>,
    ) {
        container.buildVariant(buildVariant, strategy, configurationAction)
    }

    protected fun <T : Any> buildVariant(
        buildVariant: String,
        container: NamedDomainObjectContainer<T>,
        mergeStrategy: MergeStrategy = MergeStrategy.MERGE,
        @DelegatesTo(
            genericTypeIndex = 0,
            strategy = Closure.DELEGATE_FIRST,
        )
        configurationClosure: Closure<in T>,
    ) {
        container.buildVariant(buildVariant, mergeStrategy, configurationClosure)
    }

    protected fun <T : Any> buildVariant(
        buildVariant: String,
        container: PluginDomainObjectContainer<T>,
        strategy: MergeStrategy = MergeStrategy.MERGE,
        configurationAction: Action<in T>,
    ) {
        container.buildVariant(buildVariant, strategy, configurationAction)
    }

    protected fun <T : Any> buildVariant(
        buildVariant: String,
        container: PluginDomainObjectContainer<T>,
        mergeStrategy: MergeStrategy = MergeStrategy.MERGE,
        @DelegatesTo(
            genericTypeIndex = 0,
            strategy = Closure.DELEGATE_FIRST,
        )
        configurationAction: Closure<in T>,
    ) {
        container.buildVariant(buildVariant, mergeStrategy, configurationAction)
    }
}
