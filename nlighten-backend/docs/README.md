# Documentation for Nlighten

## recommendation for documentation process

Every important part of the solution should be documented in a simple form of Markdown files in this folder.  
For example `server-configuration.md` with brief info about widlfly setup, `security.md` for details about security handling.

If possible, keep the docs short and up-to-date. 

# CDI Logging Extension
CDILoggingExtension class list all available beans during application startup. This feature is by default disabled, but can be enabled by setting system variable "NLIGHTEN_ENABLE_CDI_DEBUG" to true on your server (-DNLIGHTEN_ENABLE_CDI_DEBUG=true).