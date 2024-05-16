#/bin/sh

rm -rf target
mvn package
mv target/email-service-1.0.war $TOMCAT_HOME/webapps
