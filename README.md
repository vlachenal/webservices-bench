# webservices-bench
Project to test and compare different webservices

## Synopsis
I have been asked my wishes for the technical base components by the company in which I am working.

Apart from my wishes, the structural proposals were :
 - switch from Thrift services to REST services
 - switch from Tomcat/Guice to Tomcat/spring-boot
 - switch from Ant/Ivy to Maven

For the department of the enterprise in which I work, the structural proposals for which I voted for are:
 - switch from Subversion to GIT because we have a lot of feature requests on the same time: requests may not have be released on the same time and SVN branches merges are painfully.

This project aims to test these solutions on server side.

I have done these projects on my free time ; that's why they are published under Do What the Fuck You Want to Public License: you can reuse them as you want. I don't care about it.

Feel free to fork (and make pull request) to complete and/or fix my tests.

### Differences between implementations

Although I tried to have the same implementation between the different protocols, there are several notable differences:
 - headers:
     - RESTful service uses HTTP headers
     - Thrift service uses header structure in request
     - SOAP service uses SOAP headers
 - dates:
     - RESTful service uses Date which are serialize as java.util.String
     - Thrift service uses i64 which is translated into long
     - SOAP service uses xsd:date which is translated into XMLGregorianCalendar and serialize as java.util.String

## Maven
When I began my integration tests, I began to write the pom.xml. I found it too complex and too verbose.

So I decided to test Gradle which is almost perfect from my point of view.

## Spring-boot
When spring-boot has been proposed as Guice remplacement, I thought : "Burp .. Ugly XML descriptor files again ..."

I though about former Spring Framework.

When I began my tests, I was immediately convinced that sping-boot will be good for our usage.

### Advantages
Spring-boot is very easy framework to use.

### Disadvantages
Spring-boot does magic so it may be difficult to parameterize it for specific usage. It happened during my tests for Thrift servlet declarations ("bean" names are deduced from class ... so they override each other if you don't set them) but ~~Google~~ [Qwant](https://www.qwant.com/) is your friend.

## RESTful
I will not introduced RESTful web services. You can find informations all over web.

I just will expose what I thought before I began my tests.

### Advantages
RESTful web services use every HTTP verbs. Which is very elegant from my point of view.

RESTful web services which use JSON structures are directly compatible with web client side application.

### Disadvantages
JSON structure (it is even more relevant if you decide to use XML) is not a compact structure like Thrift TCompactProtocol. It will induce overhead on network, serialization and deserialization.

If you use JSON format for RESTful web services, you will not have structure control like in SOAP, Thrift, Protobuf, ...


## Thrift
Thrift is a protocol originally developped by Facebook which has been given to Apache Foundation.

### Advantages
Thrift is independent from programming language. The compiler will generate source code for almost every language ... which is not relevant for the company in which I work because we always use Java.

Unlike CORBA, Thrift provides a ready to use library.

Thrift provides several (de)serialization formats.

Thrift provides several network protocols.

### Disadvantages
Thrift has not been really adopted. Large majority of companies uses RESTful or SOAP services.

Thrift is not designed for client side communication.

## Mapping
As project will test several protocols, it is relevant to test several mapping technologies ...

### Manual mapping
Manual mapping can induce an overload of work but it permits to have a clear way of what is done.

### Dozer
As long as mapping is trivial, Dozer is the easier to use. It will map the structures silently without any configuration.

For complex mapping, it is not friendly. Its API is not easy to use.

Dozer will resolve mapping on runtime by introspection: it will certainly induce overhead.

### MapStruct
MapStruct is easy to use: you just have to declare mapping interface for basic mapping.

For complex mapping, you just have to declare implementation in another class and reference it in mapper interface.

MapStruct will generate implementation on compile time and it is closed to what if you map structures manually ... so I don't think there will be any overhead.

## Results
You can consult results for my laptop configuration (Laptop Core i7-4510U, RAM 8GB, SSD, Tomcat 8 with bootRun task in Eclipse) [here](https://github.com/vlachenal/webservices-bench/blob/master/results.md)

You can consult results for my desktop configuration (Desktop Core i7 920, RAM 24GB, SSD, Tomcat 8.5 with APR) [here](https://github.com/vlachenal/webservices-bench/blob/master/results-desktop.md)

## Conclusions

### Server
On server side, spring-boot is really easy to use. It fulfills perfectly web services development needs.

I personally will not use Maven but Gradle: its syntax is more compact and human readable.

### Client
On client side, spring-boot offers a good way to make a batch application through the 'bootRepackage' task.

However, I will prefer Apache CXF for REST and SOAP clients. I find it more intuitive.

### Performances
Don't use SOAP and don't use Dozer.

SOAP has an really heavy overhead for serialization and deserialization.

Overhead with Dozer is significant enough not to use it.

According to operation, REST service has little overhead comparing to Thrift ... so you can choose one or the other according what you want to do (Thrift will offer structure control but is not used by many ; REST is ready to use on client side ...).
Tests are not finished since I have to launch tests on Tomcat with native network library and compression. So conclusion may changed.

For mapping, I will use MapStruct because it has no overhead compared to manual mapping and the library is easy to use.

## TODO
By priority order:
 - ~~Publish client side benchmark test~~ => [webservices-bench-client project](https://github.com/vlachenal/webservices-bench-client)
 - ~~Publish results~~
 - ~~SOAP web service~~
 - ~~Implements Dozer mapping~~
 - ~~Implements MapStruct mapping~~
 - Protobuf web service
 - Nginx + PHP + Slim + Thrift
 - unrelevant AnCH Framework SQL + Thrift tests ...
