package ru.kode.android.gradle.commons.util

import okhttp3.Request
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.URI

fun cannotCreateHttpProxyMessage(
    host: String?,
    port: String?,
): String {
    return """

        |============================================================
        |                 PROXY CONFIGURATION ERROR
        |============================================================
        | Failed to create HTTP proxy
        |
        | Host: ${host ?: "Not specified"}
        | Port: ${port ?: "Not specified"}
        |
        | ACTION REQUIRED:
        |  1. Verify your proxy host and port configuration
        |  2. Ensure the proxy server is reachable
        |  3. Try to run task with --stacktrace option to get more details
        |============================================================
        """.trimMargin()
}

fun createHttpProxyMessage(
    host: String,
    port: String,
): String {
    return """

        |============================================================
        |            HTTP PROXY CONFIGURED SUCCESSFULLY
        |============================================================
        | Host: $host
        | Port: $port
        |
        | All HTTP traffic will be routed through this proxy
        |============================================================
        """.trimMargin()
}

fun cannotCreateHttpsProxyMessage(
    host: String?,
    port: String?,
): String {
    return """

        |============================================================
        |              HTTPS PROXY CONFIGURATION ERROR
        |============================================================
        | Failed to create HTTPS proxy
        |
        | Host: ${host ?: "Not specified"}
        | Port: ${port ?: "Not specified"}
        |
        | ACTION REQUIRED:
        |  1. Verify your HTTPS proxy settings
        |  2. Ensure the proxy supports HTTPS connections
        |  3. Check for any SSL/TLS configuration issues
        |  4. Try to run task with --stacktrace option to get more details
        |============================================================
        """.trimMargin()
}

fun createHttpsProxyMessage(
    host: String,
    port: String,
): String {
    return """

        |============================================================
        |            HTTPS PROXY CONFIGURED SUCCESSFULLY
        |============================================================
        | Host: $host
        | Port: $port
        |
        | All HTTPS traffic will be routed through this proxy
        |============================================================
        """.trimMargin()
}

fun proxyConnectionFailedMessage(uri: URI?): String {
    return """

        |============================================================
        |                  PROXY CONNECTION FAILED
        |============================================================
        | Failed to connect to proxy server
        |
        | Target URI: ${uri ?: "Not specified"}
        |
        | POSSIBLE CAUSES:
        |  1. Proxy server is not running
        |  2. Network connectivity issues
        |  3. Incorrect proxy configuration
        |  4. Authentication required but not provided
        |
        | ACTION REQUIRED:
        |  1. Verify proxy server is running and accessible
        |  2. Check network connectivity
        |  3. Review proxy configuration
        |============================================================
        """.trimMargin()
}

fun returnAndApplyProxyMessage(
    uri: URI,
    proxyAddress: InetSocketAddress?,
): String {
    val string = if (proxyAddress != null) "proxy" else "direct"
    return """

        |============================================================
        |               APPLYING PROXY CONFIGURATION
        |============================================================
        | Target URI: $uri
        | Proxy: ${proxyAddress ?: "No proxy (direct connection)"}
        |
        | Proceeding with $string connection...
        |============================================================
        """.trimMargin()
}

fun applyProxyAuthMessage(proxyUser: String): String {
    return """

        |============================================================
        |               APPLYING PROXY AUTHENTICATION
        |============================================================
        | Authenticating as: ${SecretRedaction.redactCredential(proxyUser)}
        |
        | Proxy authentication credentials will be used for the connection
        |============================================================
        """.trimMargin()
}

fun requestingWithoutProxyMessage(request: Request): String {
    return """

        |============================================================
        |                     DIRECT CONNECTION
        |============================================================
        | Sending request without proxy
        |
        | URL: ${SecretRedaction.redactUrl(request.url.toString())}
        | Method: ${request.method}
        |
        | Proceeding with direct connection...
        |============================================================
        """.trimMargin()
}

fun requestingProxyMessage(
    proxy: Proxy?,
    request: Request,
): String {
    return """

        |============================================================
        |                      PROXIED REQUEST
        |============================================================
        | Sending request via proxy
        |
        | Proxy: ${proxy ?: "None"}
        | URL: ${SecretRedaction.redactUrl(request.url.toString())}
        | Method: ${request.method}
        |
        | Request will be routed through the configured proxy
        |============================================================
        """.trimMargin()
}

fun proxyCredsNotSpecified(): String {
    return """

        |============================================================
        |                PROXY AUTHENTICATION MISSING
        |============================================================
        | Proxy authentication credentials are not specified
        |
        | ACTION REQUIRED:
        |  1. Add the following to your gradle.properties file:
        |     systemProp.https.proxyUser=<your_username>
        |     systemProp.https.proxyPassword=<your_password>
        |
        |  2. OR set them as environment variables:
        |     export GRADLE_OPTS=\"-Dhttps.proxyUser=<user> -Dhttps.proxyPassword=<password>\"
        |
        |  3. For security, consider using a credential manager instead of
        |     storing passwords in plain text
        |
        | NOTE:
        | These credentials will be used to authenticate with
        | the proxy server
        |============================================================
        """.trimMargin()
}

fun proxyAuthenticatorTriggeredMessage(
    host: String?,
    port: Int,
    scheme: String?,
    userName: String?,
): String {
    return """
        |🎯 AUTHENTICATOR TRIGGERED!
        |  Host: $host:$port
        |  Scheme: $scheme
        |  UserName: ${userName?.let { SecretRedaction.redactCredential(it) }}
        |  Password: <hidden>
        """.trimMargin()
}

fun requiredConfigurationNotFoundMessage(
    name: String,
    defaultName: String,
): String {
    return """

        |============================================================
        |                    CONFIGURATION ERROR
        |============================================================
        | Required configuration not found
        |
        | Expected one of these configurations:
        |   - $name
        |   - $defaultName (fallback)
        |
        | POSSIBLE CAUSES:
        |   1. The configuration was not registered in the build script
        |   2. The configuration name is incorrect
        |   3. There are syntax errors in the build script
        |
        | ACTION REQUIRED:
        |   1. Verify the configuration names in your build script
        |   2. Check for any syntax errors
        |   3. Ensure the plugin is applied correctly
        |   4. Try to run task with --stacktrace option to get more details
        |============================================================
        """.trimMargin()
}
