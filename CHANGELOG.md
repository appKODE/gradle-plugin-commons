# Changelog

All notable changes to this project are documented in this file.

## [Unreleased]

## [1.0.0]
### Added
- Initial scaffold: `LoggerService` build service and `PluginLogger` abstraction for shared plugin logging.
- Basic Auth / HTTP proxy utilities: `buildBasicAuthInterceptor`, proxy-aware `OkHttpClient` support, `SecretRedaction` for masking credentials in logs.
- Common/variant config-merge DSL (`CommonConfigMergeable`, `MergeStrategy`, `CollectionStrategy`) for `common { }` + `buildVariant("x") { }` style Gradle extensions.
- `PluginConfigurableExtension` and `PluginDomainObjectContainer` base types for building configurable Gradle plugin extensions.
- Groovy DSL interop (`configureGroovy`) and misc string/service-naming helpers.
