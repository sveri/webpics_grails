webpics_grails
==============

Is a picture viewer for your own webserver and all the people that dont want to get there pictures into the cloud.

## Usage
* Download the latest war file from: (will be delivered soon). Upload this to your host and run it with your 
preferred Java servlet container like Jetty or Tomcat. You can find all the supported Servers 
under: http://grails.org/Deployment
* Setup your database by adding a new database for pix. As grails uses hibernate many databases are supported. 
This app is only tested with mysql, so dont ask me for support on other databases.
* Add a pix-config.properties to your classpath or one of the following locations: $USER_HOME/.pix/ or $USER_HOME/.grails/
This is an example config file:
