# Eclipse MicroProfile Service Model

[![Keep a Changelog v1.1.0 badge](https://img.shields.io/badge/changelog-Keep%20a%20Changelog%20v1.1.0-%23E05735)](https://github.com/fugerit-org/fj-service-helper-bom/blob/main/emp-service-model/CHANGELOG.md) 

This project contains a collection of model object (like beans or POJOS) to be used in service implementation.

## 1. ServiceMessage

Sample usage : 

```java
ServiceResponse response = new ServiceResponse()
        .addAllBySeverity( ServiceMessage.newMessage( "400001", ServiceMessage.Severity.ERROR, "Test bad request message" ),
                ServiceMessage.newMessage( ServiceMessage.Severity.INFO, "Test info message" ),
                ServiceMessage.newMessage( ServiceMessage.Severity.WARNING, "Test warning message" ) );
```

Json output : 

```json
{
  "errors" : [ {
    "code" : "400001",
    "severity" : "E",
    "text" : "Test bad request message"
  } ],
  "warnings" : [ {
    "code" : "W",
    "severity" : "W",
    "text" : "Test warning message"
  } ],
  "infos" : [ {
    "code" : "I",
    "severity" : "I",
    "text" : "Test info message"
  } ]
}
```

## 2. WAExHelper

Sample usage :

```java
throw WAExHelper.newEx( Response.Status.BAD_REQUEST, "Test bad request message" );
```
json output : 

```json
{
  "status" : 400,
  "entity" : {
    "errors" : [ {
      "code" : "E",
      "severity" : "E",
      "text" : "Test bad request message"
    } ]
  },
  "headers" : { },
  "entityStream" : null,
  "stringHeaders" : { },
  "entityAnnotations" : null,
  "closed" : false,
  "length" : -1,
  "location" : null,
  "language" : null,
  "date" : null,
  "lastModified" : null,
  "statusInfo" : {
    "reasonPhrase" : "Bad Request",
    "family" : "CLIENT_ERROR",
    "statusCode" : 400
  },
  "mediaType" : null,
  "cookies" : { },
  "entityTag" : null,
  "links" : [ ],
  "metadata" : { },
  "allowedMethods" : [ ]
}

```
