Elastic Search Object Persistence and Retrievel

In this project I am using Elastic Search as a database. The aim is to find the most efficient way to write data to Elastic Search and parse the json data into Scala Objects. 
These objects will be in different types and structure. 
As their fields they will include:
 - All the primitive types (Int, Double, Boolean String)
 - Enumerations
 - Other Objects
 - Array's of both primitives and Objects
 - Option values of both primitives and Objects

* For enumerations Enumeratum Library will be used -> https://github.com/lloydmeta/enumeratum
* To convert Objects into Json and parse Json data into objects there will be two different methodology.
  1) Elastic Search Indexable & HitAs methods.
  2) Arganout.io Library (JSON Library for Scala) -> https://github.com/argonaut-io/argonaut
