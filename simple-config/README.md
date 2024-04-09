# Simple Config API for services.

## Usage :

```java
ConfigParams config = new ConfigParamsDefault( configProperties );
String value1 = config.getValue( "testconfig.param1" );
Optional<String> value2 = config.getOptionalValue( "testconfig.param2" );
```

Or added behaviours, for instance logging : 

```java
ConfigParams config = ConfigParamsLogger.wrapLogDebug( new ConfigParamsDefault( configProperties ) );
```

