package ru.kode.android.gradle.commons.logger

import org.gradle.api.logging.Logger

/**
 * Creates a [PluginLogger] backed by a plain `(String) -> Unit` sink.
 *
 * Used when no structured [PluginLogger] is supplied: [PluginLogger.info] and [PluginLogger.quiet]
 * are forwarded verbatim, while [PluginLogger.warn] and [PluginLogger.error] are prefixed.
 *
 * @param log the sink that receives every formatted log line
 * @return a [PluginLogger] that forwards to [log]
 */
fun pluginLoggerFromLog(log: (String) -> Unit): PluginLogger =
    object : PluginLogger {
        override val bodyLogging = false

        override fun info(
            message: String,
            exception: Throwable?,
        ) = log(message)

        override fun warn(message: String) = log("WARN: $message")

        override fun error(
            message: String,
            exception: Throwable?,
        ) = log("ERROR: $message ${exception?.message.orEmpty()}")

        override fun quiet(message: String) = log(message)
    }

/**
 * Creates a [PluginLogger] that forwards each level to the matching method of a Gradle [Logger].
 *
 * Complements [pluginLoggerFromLog] (which funnels to a single sink); use this when a full Gradle
 * [Logger] is available so each level routes to its native method, preserving `exception` on error.
 *
 * @param logger the Gradle logger to forward to
 * @return a [PluginLogger] backed by [logger]
 */
fun pluginLoggerFromLogger(logger: Logger): PluginLogger =
    object : PluginLogger {
        override val bodyLogging = false

        override fun info(
            message: String,
            exception: Throwable?,
        ) = logger.info(message)

        override fun warn(message: String) = logger.warn(message)

        override fun error(
            message: String,
            exception: Throwable?,
        ) = logger.error(message, exception)

        override fun quiet(message: String) = logger.quiet(message)
    }
