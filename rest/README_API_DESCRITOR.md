# API Descriptor example CREST application

## Introduction

This is a sample CREST application that uses the API Descriptor framework.
In the source code you can find examples how to use the API Descriptor annotations with detailed descriptions.
This readme will guide you how to clone and start up the application and how to send different `curl` commands.
More help about the ForgeRock CREST protocol can be found here: [CREST example readme file](README.md).

## Api Descriptor example CREST application structure

### Model

The application model is based on users and user devices.
Devices can only be added as a user sub-resource

#### User

name|type|
----|----|
uid | String
username | String
password | String
* | [Devices](Device)  

#### Device

name|type|
----|----|
did | String
name| String
type | String
stolen | boolean
rollOutDate | Date

### Packages

All code is under ```org.forgerock.json.resource.descriptor.examples``` package

* ```handler``` package is where the UserCollectionHandler class lives. It is responsible for 
    creating the version 1.0 and version 2.0 users and device handlers and also the Route to them
* ```model``` package where the User and Device beans stored
* ```provder``` package has ```version1``` and ```version2``` sub-packages with the corresponding
    Device and User collection handler classes.

## Cloning and Running the service

Using the following command clone the repository and start embedded maven jetty server to run the application.

```
$ git clone ssh://git@stash.forgerock.org:7999/commons/forgerock-commons.git
$ cd forgerock-commons

--- At the moment the api descriptor branch has to be check-out but later on it will be merged to the main branch
--- So the followint two git commands has to be run
$ git fetch
$ git checkout api-descriptor

$ mvn clean:install
$ cd json-resource-examples
$ mvn jetty:run
```


## Available REST calls

Once the Jetty server is runnig the following commands are available on the service.

### User calls

#### Create user. Id is generated by server

```
curl -H "Content-Type: application/json" -H "accept-api-version: resource=1.0"  -d '{ "uid" : "user1", "name" : "name1", "password" : "password1" }' 'http://localhost:8080/api/users?_action=create&_prettyPrint=true'
{
  "_id" : "1",
  "_rev" : "0",
  "uid" : "user1",
  "name" : "name1",
  "password" : "password1"
}
```

#### Create user. Id is passed by client

```
curl --request PUT -H "Content-Type: application/json" -H "accept-api-version: resource=1.0" -d '{ "uid" : "5", "username" : "testuser5", "password" : "password5" }' 'http://localhost:8080/api/users/test1?_prettyPrint=true'
{
  "_id" : "test1",
  "_rev" : "0",
  "uid" : "5",
  "username" : "testuser5",
  "password" : "password5"
}
```

#### Read user

```
curl --request GET -H "accept-api-version: resource=1.0" 'http://localhost:8080/api/users/test1?_prettyPrint=true'
{
  "_id" : "test1",
  "_rev" : "0",
  "uid" : "5",
  "username" : "testuser5",
  "password" : "password5"
}
```

#### Query users

##### Query all users
```
curl -H "accept-api-version: resource=1.0" 'http://localhost:8080/api/users?_queryFilter=true&_prettyPrint=true'
{
  "result" : [ {
    "_id" : "0",
    "_rev" : "0",
    "uid" : "user1",
    "name" : "name1",
    "password" : "password1"
  }, {
    "_id" : "1",
    "_rev" : "0",
    "uid" : "user1",
    "name" : "name1",
    "password" : "password1"
  }, {
    "_id" : "test1",
    "_rev" : "0",
    "uid" : "5",
    "username" : "testuser5",
    "password" : "password5"
  } ],
  "resultCount" : 3,
  "pagedResultsCookie" : null,
  "totalPagedResultsPolicy" : "NONE",
  "totalPagedResults" : -1,
  "remainingPagedResults" : -1
}
```

##### Query users where uid equals "user1" and return the "username" field only

```
curl -H "accept-api-version: resource=1.0" 'http://localhost:8080/api/users?_queryFilter=uid+eq+"user1"&_fields=username&_prettyPrint=true'
{
  "result" : [ {
    "_id" : "0",
    "_rev" : "0",
    "username" : "testuser1"
  }, {
    "_id" : "test1",
    "_rev" : "3",
    "username" : "testuser1PATCHED"
  } ],
  "resultCount" : 2,
  "pagedResultsCookie" : null,
  "totalPagedResultsPolicy" : "NONE",
  "totalPagedResults" : -1,
  "remainingPagedResults" : -1
}
```

#### Update user

```
curl --request PUT -H "Content-Type: application/json" -H "accept-api-version: resource=1.0" -d '{ "uid": "user1",  "username" : "testuser1UPD", "pasword": "password1" }' 'http://localhost:8080/api/users/test1?_prettyPrint=true'
{
  "_id" : "test1",
  "_rev" : "1",
  "uid" : "user1",
  "username" : "testuser1UPD",
  "password" : "password1"
}
```

#### PATCH user

```
curl --request PATCH -H "Content-Type: application/json" -H "accept-api-version: resource=1.0" -d '[{ "operation": "replace", "field": "username", "value": "testuser1PATCHED" }]' 'http://localhost:8080/api/users/test1?_prettyPrint=true'
{
  "_id" : "test1",
  "_rev" : "3",
  "uid" : "user1",
  "password" : "password1",
  "username" : "testuser1PATCHED"
}
```


#### Action reset password of user (It is not implemented)

```
curl --request POST -H "accept-api-version: resource=1.0" 'http://localhost:8080/api/users/user1?_action=resetPassword&_prettyPrint=true'
{
  "code" : 501,
  "reason" : "Not Implemented",
  "message" : "Action `resetPassword` reached. As it is an example service it has not been implemented."
}
```

### Device calls

All the above calls need a "user1" uid user to be created. 

#### Create new device. Id generated by server

```
curl -H "Content-Type: application/json" -H "accept-api-version: resource=1.0" -d '{ "did" : "device1", "name" : "devicename1", "type" : "type1" }' 'http://localhost:8080/api/users/user1/devices?_action=create&_prettyPrint=true'
{
  "_id" : "0",
  "_rev" : "0",
  "did" : "device1",
  "name" : "devicename1",
  "type" : "type1"
}
```

#### Create new device. Id passed by client
```
curl --request PUT -H "Content-Type: application/json" -H "accept-api-version: resource=1.0" -d '{ "did" : "device3", "name" : "devicename3", "type" : "type3" }' 'http://localhost:8080/api/users/user1/devices/dev3?_prettyPrint=true'
{
  "_id" : "dev3",
  "_rev" : "0",
  "did" : "device3",
  "name" : "devicename3",
  "type" : "type3"
}
```

#### Query devices of a user

```
curl -H "accept-api-version: resource=1.0" 'http://localhost:8080/api/users/user1/devices?_queryFilter=true&_prettyPrint=true'
{
  "result" : [ {
    "_id" : "0",
    "_rev" : "0",
    "did" : "device1",
    "name" : "devicename1",
    "type" : "type1"
  }, {
    "_id" : "dev3",
    "_rev" : "0",
    "did" : "device3",
    "name" : "devicename3",
    "type" : "type3"
  } ],
  "resultCount" : 2,
  "pagedResultsCookie" : null,
  "totalPagedResultsPolicy" : "NONE",
  "totalPagedResults" : -1,
  "remainingPagedResults" : -1
}
```

#### Delete device

```
 curl --request DELETE -H "accept-api-version: resource=1.0" 'http://localhost:8080/api/users/user1/devices/dev3?_prettyPrint=true'
{
  "_id" : "dev3",
  "_rev" : "0",
  "did" : "device3",
  "name" : "devicename3",
  "type" : "type3"
}
```

#### Update device

```
curl --request PUT -H "Content-Type: application/json" -H "accept-api-version: resource=1.0" -d '{ "did" : "device1", "name" : "devicename1UPD", "type" : "type1UPD" }' 'http://localhost:8080/api/users/user1/devices/0?_prettyPrint=true'
{
  "_id" : "0",
  "_rev" : "1",
  "did" : "device1",
  "name" : "devicename1UPD",
  "type" : "type1UPD"
}
```

#### Patch device

```
curl --request PATCH -H "Content-Type: application/json" -H "accept-api-version: resource=1.0" -d '[{ "operation": "replace", "field": "name", "value": "device1PATCHED" }]' 'http://localhost:8080/api/users/user1/devices/0?_prettyPrint=true'
{
  "_id" : "0",
  "_rev" : "2",
  "did" : "device1",
  "type" : "type1UPD",
  "name" : "device1PATCHED"
}
```

#### Call action `markAsStolen` on device

```
curl --request POST -H "accept-api-version: resource=1.0" 'http://localhost:8080/api/users/user1/devices/0?_action=markAsStolen&_prettyPrint=true'
{
  "code" : 501,
  "reason" : "Not Implemented",
  "message" : "Action `markAsStolen` reached. As it is an example service it has not been implemented."
}
```

#### Call action `rollOut` on device. Supported by handler version 2.0

```
curl --request POST -H "accept-api-version: resource=2.0" 'http://localhost:8080/api/users/user1/devices/0?_action=rollOut&_prettyPrint=true'
{
  "code" : 501,
  "reason" : "Not Implemented",
  "message" : "Action `rollOut` reached. As it is an example service it has not been implemented."
}
```