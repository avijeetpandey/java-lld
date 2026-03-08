# Key System design concepts
Below are key system design concepts frequently asked in the interviews.


## Scalability
Scalability of a system is defined as the ability to handle at peak load , without degrading the performance of the system and functionalities, there are two kinds of scaling and they are
- Vertical scaling
- Horizontal scaling

## Availability
Availability of a system is defined as the time your system is actively serving the users without fail,basically do what it is supposed to do.

## CAP Theorem
Cap theorem states that in case of distributed system only two out of three can be achieved i.e between Consistency, Availability or partial tolerence , and the tradeoff has to be made in Consistency and availability as network always kind of fails.


## ACID Transactions
Acid stands for
A - Atomicity ( Atomicity means that if a transactions happens it should either fail or succeed nothing in between )
C - Consistency ( consistency says that data must follow database rules and constraints )
I - Isolation ( If multiple transactions are happening at once they must not interfere with each other )
D - Durability ( data store should persist even in case of power loss )

## Rate limiting
Rate limiting is basically put in a check on the number of requests that can hit the system or the server at any given point of time , it is basically done to avoid spamming the API calls. it is done via various rate limiting algorithms like
- Token bucket
- Sliding window

## SPOF
SPOF ( single point of failure ) , is any specific piece of hardware of software on which the entire system depends , if this central piece fails , the entire system goes down.

## Fault tolerence
As the name suggests , fault tolerance stands for the fact that if the system is operational even if some of its components are not working properly or are down.

## Distributed tracing
Distributed tracing is basically injecting a unique identifiers to a request in case it goes through multiple services and systems , so that whenever something breaks we can definetely look at the part at which the request broke or the error came. And also the request does not gets lost in case it is routed through multiple services or systems.

## CDN
CDN stands for content delivery network is a group of proxy servers, whose main aim is to serve heavy and static files like images and videos and cache them on physical servers which are very close to the user.

## DNS
DNS stands for domain name system , all the computers on the internet communicate with each others using IP address and which is not humanly readable and understandable , so a DNS translates the IP address to human readable names.

## Database types
There are various kinds of database for example
- Relational databases ( MySQL , Postgres)
- Non- relational databases ( MongoDB )
- Key value databases ( Redis )
- Columnar databases ( Casandra )

## Microservice
Microservices stands on the simple concept that instead of having a massive application we split the application in multiple services , each service has its own database and communication mechanism, this greatly decouples the entire application and removes single point of failure.