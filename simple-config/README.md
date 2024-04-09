# Simple Config API for services.

## Usage :

```java
ConfigParams config = new ConfigParamsDefault( configProperties );
String value1 = config.getValue( "testconfig.param1" );
Optional<String> value2 = config.getOptionalValue( "testconfig.param2" );
```
