# Account Service

A service that emulates a RESTful API (including data model and the backing implementation)
for money transfers between accounts.

JSON file inside ``resources/json/accounts-mock.json`` containing list of account with ID, Name and Balance. Accounts must be
This service parsed from the file and ingested into your application during startup. After importing data User able to transfer fund between users(Credit and Debit).
Technically AccountComponent is class which implemented ApplicationRunner and it will run once when application startup.




### Technology used 
* Java 17
* Maven
* SpringBoot
* Spring Reactive Web
* Swagger
* Javax Validation
* Lombok
