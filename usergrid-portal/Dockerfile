FROM tomcat:7-jre8
MAINTAINER "Steve Blackmon <sblackmon@apache.org>"

COPY ./tomcat-users.xml /usr/local/tomcat/conf/

WORKDIR /usr/local/tomcat/webapps

COPY ./portal.zip /usr/local/tomcat/webapps

RUN unzip portal.zip

EXPOSE 3000

CMD ["catalina.sh", "run"]
