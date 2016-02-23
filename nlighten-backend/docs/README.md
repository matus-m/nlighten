# Documentation for Nlighten

## recommendation for documentation process

Every important part of the solution should be documented in a simple form of Markdown files in this folder.  
For example `server-configuration.md` with brief info about widlfly setup, `security.md` for details about security handling.

If possible, keep the docs short and up-to-date. 

# CDI Logging Extension

`CDILoggingExtension` class list all available beans during application startup. This feature is by default disabled, but can be enabled by setting system variable `NLIGHTEN_ENABLE_CDI_DEBUG` to true on your server (-DNLIGHTEN_ENABLE_CDI_DEBUG=true).

# CDI Trace Interceptor

`TracedLoggingInterceptor` logs information about CDI bean method invocations (method name, class, start time stop time, success). To enable trace either add an annotation `Traced` on a CDI bean or on it's methods. Optionally the verbosity level can be specified by passing a value to `Traced` of type `VerbosityLevel`. The values are `VerbosityLevel.BASIC` (default) and `VerbosityLevel.VERBOSE` (includes the size of returned collection by a traced method).

# Testing

To have functioning tests the `JAVA_HOME` env. variable has to be set to point to your JDK8. By default the profile `wildfly-managed-arquillian` is enabled. It downloads an instance of Wildfly 10.0.0.CR4 and runs tests on it. An example of a functioning test can be found in `LoggingInterceptorTest.java`.

For debugging tests run maven with profile `wildfly-managed-arquillian-debug`. To attach a debugger to this instance in `Eclipse` create a new debug run configuration of type `Remote Java Application`, set connection type to `Standard(socket attach)` and set it to listen on port `8787` (host = `localhost`).

# Configuration framework

`NlightenConfigService` loads configuration data from `JSON` file to `POJO`. `JSON` has to be located in the same classpath as `POJO` or it's path can be set by system variable `NGLIGHTEN_CONFIGURATION_FILE_PATH`. Also after `POJO` is loaded, data are set to configuration cache, so every next call for already loaded `POJO` gets data from cache. In order to reload configuration cache with new data, corresponding method has to be called. For more details see `NlightenConfigServiceTest`.

# Internationalization
`I18nUtil` is class dedicated to translating given `Enum` keys, to their respective translated texts.
You can use different declarations o `translate` method to achieve this goal. For translation to be successful, one must adhere to following conventions:
1. There must be a `Enum` class `[EnumName]` defined in the source folder with path `[EnumPath]`.
2. There must be property file containg transations in the resource folder with file path:	
	a. System property `SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS` is set to `true`:
		translation file is defined in the folder structure
	`\translations\[EnumPath]\[EnumName]_[language as ISO 639-1].properties`	
	b. System property `SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS` is not defined:
		translation file name is the same, as is `Enum` class FQN + suffix
	`\translations\[EnumPath].[EnumName]_[language as ISO 639-1].properties`	
3. For each `Enum` class value there must be key matching it's name in property file.
4. The value of the property key is the translation, that is returned by `I18nUtil#translate`.
P.S.: Use of `CAMEL_CASE` formatting for enum\property keys is recommended.

		
 