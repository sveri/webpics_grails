webpics_grails
==============

Is a picture viewer for your own webserver and all the people that dont want to get there pictures into the cloud.

You can get the current version at: http://sonstiges.sveri.de/pix-0.2.3.war

A demo is available under: http://pix-demo.sveri.de/

Username: demo

Password: demodemo

Uploading and adding/editing users/roles is disabled on this demo.


## Features
The following features are currently available.

### Uploading
* Upload multiple image files via javascript
* Upload zip files located on the server on a path which is accessible by the server

### Album
* Album creation/deletion/renaming
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
* Rotation state of images is persisted to the backend, images will be loaded in the saved state


## Usage
* Download the latest war file from: http://sonstiges.sveri.de/pix-0.2.3.war . Upload this to your host and run it with your 
preferred Java servlet container like Jetty or Tomcat. You can find all the supported Servers 
under: http://grails.org/Deployment
* Setup your database by adding a new database for pix. As grails uses hibernate many databases are supported. 
This app is only tested with mysql, so dont ask me for support on other databases.
* Add a pix-config.properties to your classpath or one of the following locations: $USER_HOME/.pix/ or $USER_HOME/.grails/
This is an example config file:

```bash
dataSource.driverClassName=com.mysql.jdbc.Driver                    # mandantory
dataSource.url=jdbc:mysql://localhost/database_name                 # mandantory
dataSource.username=database_username                               # mandantory
dataSource.password=database_password                               # mandantory
pix.image_base_path=/path/to/where/the/pictures/shall/be/stored     # mandantory
pix.copyright_notice=copyright notice                               # optional
pix.keep_originals=true                                             # optional - defaults to false
pix.size.big=1900                                                   # optional - defaults to 1900
pix.size.normal=800                                                 # optional - defaults to 800
pix.size.thumbs=20                                                  # optional - defaults to 20
```

## Feature History
See all features as they came in

### 0.2.3
* save rotation state of images
* delete albums with images
* rename albums
* added favicon
* minor fixes

### 0.2.2
* added tests for services
* omit an hourly ajax get request to stay logged in as long as the browser is opened
* a user can only assign the rights he himself has
* minor fixes

### 0.2.1
* added a bit logging
* Administrator can see all albums
* If a new user adds an album his groups gain the right to see that album

### 0.2 
* assing albums to groups
* as a user i want to see the login form on the index page 



