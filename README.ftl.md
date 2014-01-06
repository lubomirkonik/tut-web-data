Web application based on tutorials http://spring.io/guides/tutorials/data/ and http://spring.io/guides/tutorials/web.
It uses MongoDB document store, H2 relational database and Gemfire data grid store.

Start application as follows:
MongoDB
path\mongod.exe --dbpath path\data
path\mongo.exe

Gemfire
.\gradlew run

Run application by '.\gradlew tomcatRunWar' to create WAR file run in embedded tomcat server.