# Simple Config API for services.

## Usage :

- basic example : 

```java
ConfigParams config = new ConfigParamsDefault( configProperties );
String value1 = config.getValue( "testconfig.param1" );
Optional<String> value2 = config.getOptionalValue( "testconfig.param2" );
```

- with added behaviours, for instance logging : 

```java
ConfigParams config = ConfigParamsLogger.wrapLogDebug( new ConfigParamsDefault( configProperties ) );
```

- configure an object :

```java
SimpleConfigurable simpleConfig = SimpleConfigFacade.configure( typeName, config );
```

