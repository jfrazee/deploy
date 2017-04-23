FROM tomcat:7-jre8
MAINTAINER "Steve Blackmon <sblackmon@apache.org>"

COPY ./tomcat-users.xml /usr/local/tomcat/conf/

COPY ./usergrid-deployment.local.properties /usr/local/tomcat/lib/usergrid-deployment.properties
# COPY ./usergrid-deployment.mesos.properties /usr/local/tomcat/lib/usergrid-deployment.properties

WORKDIR /usr/local/tomcat/webapps/

RUN rm -r ROOT

COPY ./ROOT.war ./ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
