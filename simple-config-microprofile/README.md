# Simple Config API implementation based on MicroProfile.

## Usage : 

```java
ConfigParams config = new ConfigParamsMicroprofile();
String value1 = config.getValue( "testconfig.param1" );
Optional<String> value2 = config.getOptionalValue( "testconfig.param2" );
```
