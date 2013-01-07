webpics_grails
==============

Is a picture viewer for your own webserver and all the people that dont want to get there pictures into the cloud.


## Features
The following features are currently available.

### Uploading
* Upload multiple image files via javascript
* Upload zip files located on the server on a path which is accessible by the server

### Album
* Album creation
* Upload Images to every album

### Rightmanagement
* Authorization is handled via Apache Shiro
* Add, delete and edit users
* Add, delete and edit roles
* Add or delete users to roles
* Detailed management of access rights in form of controller:action for every role
* Allow/Disallow album views on a role basis

### Frontend
* Fullscreen view available
* Navigate through gallery via keyboard shortcuts (left/right key)
* Turn images via keyboard shortcuts (up/down key)


## Usage
* Download the latest war file from: (will be delivered soon). Upload this to your host and run it with your 
preferred Java servlet container like Jetty or Tomcat. You can find all the supported Servers 
under: http://grails.org/Deployment
* Setup your database by adding a new database for pix. As grails uses hibernate many databases are supported. 
This app is only tested with mysql, so dont ask me for support on other databases.
* Add a pix-config.properties to your classpath or one of the following locations: $USER_HOME/.pix/ or $USER_HOME/.grails/
This is an example config file:



