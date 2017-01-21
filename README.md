### Deploy to Heroku: 
git push heroku master

### Push to git
git push origin master

### Run local: run Application as a java

## Technology stack:
Java 8
Spring boot (https://projects.spring.io/spring-boot/)
STOPM over WebSockets (https://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html#websocket-stomp)
RESTful services (https://spring.io/guides/gs/rest-service/)
Running in Heroku (https://dashboard.heroku.com/apps)

Google App Engine does not support WebSockets in standard environment, nor Spring Boot (as requires Servlet Container v3 and GAE supports only 2.5). Latest Spring Boot version runs with Java 8.

### Spring Boot WebSockets example: 
https://spring.io/guides/gs/messaging-stomp-websocket/
https://github.com/spring-guides/gs-messaging-stomp-websocket
Serving static content:
https://spring.io/blog/2013/12/19/serving-static-web-content-with-spring-boot
	src/main/resources/public/here.txt
	src/main/resources/static/here.txt
