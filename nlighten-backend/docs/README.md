# Documentation for Nlighten

## recommendation for documentation process

Every important part of the solution should be documented in a simple form of Markdown files in this folder.  
For example `server-configuration.md` with brief info about widlfly setup, `security.md` for details about security handling.

If possible, keep the docs short and up-to-date. 

# CDI Logging Extension
CDILoggingExtension class list all available beans during application startup. This feature is by default disabled, but can be enabled by setting system variable "NLIGHTEN_ENABLE_CDI_DEBUG" to true on your server (-DNLIGHTEN_ENABLE_CDI_DEBUG=true).

# CDI Trace Interceptor

`TracedLoggingInterceptor` logs information about CDI bean method invocations (method name, class, start time stop time, success). To enable trace either add an annotation `Traced` on a CDI bean or on it's methods. Optionally the verbosity level can be specified by passing a value to `Traced` of type `VerbosityLevel`. The values are `VerbosityLevel.BASIC` (default) and `VerbosityLevel.VERBOSE` (includes the size of returned collection by a traced method).

# Testing

To have functioning tests the `JAVA_HOME` env. variable has to be set to point to your JDK8. By default the profile `wildfly-managed-arquillian` is enabled. It downloads an instance of Wildfly 10.0.0.CR4 and runs tests on it. An example of a functioning test can be found in `LoggingInterceptorTest.java`.

For debugging tests run maven with profile `wildfly-managed-arquillian-debug`. To attach a debugger to this instance in `Eclipse` create a new debug run configuration of type `Remote Java Application`, set connection type to `Standard(socket attach)` and set it to listen on port `8787` (host = `localhost`).   