#!/bin/bash

set -e

mvn -P fast clean package

WEBAPP_NAME='demo'

WEBAPP_DIR=$(brew --prefix)/opt/tomcat\@9/libexec/webapps/"${WEBAPP_NAME}".war
WARFILE=$( xml sel -N maven=http://maven.apache.org/POM/4.0.0 -T -t -m /maven:project -v "concat('target/', maven:artifactId, '-', maven:version, '.', maven:packaging)" -n pom.xml )

LOG_FILE=$( xml fo -D target/classes/log4j.xml | xml sel -N xmlns:log4j=http://jakarta.apache.org/log4j/ -T -t -m "/log4j:configuration/appender[@name='log']/param[@name='File']" -v @value -n )

ln -f target/dellroad-stuff-vaadin-demo-1.0.0.war $(brew --prefix)/opt/tomcat\@9/libexec/webapps/demo.war

tail -F "${LOG_FILE}"

