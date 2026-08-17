# Gradle Plugin Commons

Shared generic utilities for appKODE Gradle plugins.

## Install

Published to Maven Central.

```kotlin
implementation("ru.kode.android:gradle-plugin-commons:1.0.0")
```

## What's included

### `logger`

Shared logging abstraction for plugins, independent of any specific logging backend.

- `PluginLogger` — logging interface (`info`/`warn`/`error`, etc.).
- `DefaultPluginLogger` — default implementation.
- `LoggerService` — Gradle `BuildService` that shares one logger across tasks/plugins in a build.
- `pluginLoggerFromLog` / `pluginLoggerFromLogger` — factories for wrapping a simple lambda or a Gradle `Logger`.

Register it via `Project.getOrRegisterLoggerService()`.

### `util` — HTTP / Basic Auth / proxy

- `buildBasicAuthInterceptor` — OkHttp interceptor for Basic Auth requests.
- `addProxyIfAvailable` (on `OkHttpClient.Builder`) / `ProxyAuthenticator` — HTTP and HTTPS proxy support, auto-detected from environment variables and system properties.
- `buildLoggingInterceptor` — HTTP logging interceptor wired to a `PluginLogger`.
- `Call<Unit>.executeNoResult()` — Retrofit `Call` extension for fire-and-forget requests.
- `SecretRedaction` — masks tokens/credentials before they hit logs.

### `util` — config merge DSL

- `MergeStrategy` / `CollectionStrategy` — merge vs. replace semantics for scalar and collection properties.
- `CommonConfigMergeable<T>` — contract for config types that can inherit from a common config.
- `inheritFrom` / `inheritNamedFrom` — implements a `common { }` + `buildVariant("x") { }` DSL pattern for plugin extensions, mirroring AGP's `defaultConfig` + product flavors.

### `util` — Gradle / Groovy interop

- `GradleApiExtensions` — `common` / `buildVariant` extension functions on `NamedDomainObjectContainer`.
- `configureGroovy` — lets consumers configure types via a Groovy `Closure`.
- `PluginSetupExtensions.applyWithOptionalAndroid` — runs a block only when the Android Gradle Plugin is applied.

### `api`

- `PluginConfigurableExtension` — base class for extensions with a common + per-variant configuration shape.
- `PluginDomainObjectContainer` — domain object container backing that shape.
- `BasicAuthConfig` / `BasicAuthCredentials` — reusable Basic Auth configuration model.

### Small helpers

- `StringExtensions` — `capitalized`, `ellipsizeAt`, `mask`, `replaceLast`.
- `ServiceNaming.serviceName` — deterministic, per-project build service names.

## Requirements

- JVM 17 toolchain.
- A Gradle plugin project.
- Kotlin 2.4.0.
- AGP 9.2.1 as `compileOnly` — consumers supply AGP at their own version.

## Building & testing

```bash
./gradlew build
./gradlew test
./gradlew ktlintCheck detekt
```

## Publishing

Pushing a tag triggers `.github/workflows/release.yml`, which publishes to Maven Central via the vanniktech `publishAndReleaseToMavenCentral` plugin with a signed release. See [CHANGELOG.md](CHANGELOG.md) for version history.

## License

[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)
