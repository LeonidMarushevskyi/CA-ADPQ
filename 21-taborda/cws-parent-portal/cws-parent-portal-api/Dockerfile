FROM centos:centos7
MAINTAINER Chris O'Meara

RUN yum update -y && yum install -y java-1.8.0-openjdk && yum clean all

COPY build/libs/parentportal-all.jar /usr/local/share/java/cws-parent-portal-api/cws-parent-portal-api.jar
COPY config/parentportal_config.yml /usr/local/etc/cws-parent-portal-api/cws-parent-portal-api.yml

VOLUME /var/log/cws-parent-portal-api

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/usr/local/share/java/cws-parent-portal-api/cws-parent-portal-api.jar" ]

CMD [ "server", "/usr/local/etc/cws-parent-portal-api/cws-parent-portal-api.yml" ]
