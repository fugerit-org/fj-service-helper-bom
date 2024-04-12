# Simple Config API implementation based on Spring.

## Usage : 

```java
Environment env = ...;
ConfigParams config = new TestConfigParamsEnvironment( env );
String value1 = config.getValue( "testconfig.param1" );
Optional<String> value2 = config.getOptionalValue( "testconfig.param2" );
```
