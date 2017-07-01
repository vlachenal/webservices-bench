# webservices-bench
Project to test and compare different webservices

## Synopsis
I have been asked my wishes for the technical base components by the company in which I am working.

Apart from my wishes, the structural proposals were :
 - switch from Thrift services to REST services
 - switch from Tomcat/Guice to Tomcat/spring-boot
 - switch from Ant/Ivy to Maven

This project aims to test these solutions on server side.

## Maven
When I began my integration tests, I began to write the pom.xml. I found it too complex and too verbose.

So I decided to test Gradle which is almost perfect from my point of view.

## Spring-boot
TODO

## RESTful
I will not introduced RESTful web services. You can find informations all over web.

I just will expose what I thought before I began my tests.

### Advantages
RESTful web services use every HTTP verbs. Which is very elegant from my point of view.

RESTful web services which use JSON structures are directly compatible with web client side application.

### Disadvantages
JSON structure (it is even more relevant if you decide to use XML) is not a compact strcture like Thrift TCompactProtocol. It will induce overhead on network, serialization and deserialization.

If you use JSON format for RESTful web services, you will not have structure control like in SOAP, Thrift, Protobuf, ...


## Thrift
TODO

## TODO
SOAP

Protobuf
