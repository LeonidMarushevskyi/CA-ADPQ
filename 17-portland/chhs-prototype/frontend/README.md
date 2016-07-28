# chhs-frontend [![Build Status](https://travis-ci.org/portlandwebworks/chhs-frontend.svg?branch=develop)](https://travis-ci.org/portlandwebworks/chhs-frontend)

***Requirements:***


* Java 8
* Maven 3.x (must be able to run `mvn` on the command line)
* Node + NPM

***To Run:***

```
npm install
mvn spring-boot:run
```

Point a browser to `http://localhost:8080/index.jsp`

Login and user info all hard coded to accept anything at the moment.

To keep your SASS files compiling and jslint checking run:

```
gulp watch
```

***Current Endpoints:***

* /api/account
  * Supports GET, POST, PUT
  * GET and PUT require user to be logged in
* /login
  * POST only
* /api/adoption-centers/{postalCode}
  * GET only

Refer to model objects in Java source for available properties. 
