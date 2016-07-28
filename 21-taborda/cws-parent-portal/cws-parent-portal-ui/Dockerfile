FROM centos:centos7
MAINTAINER Chris O'Meara

RUN yum update -y && yum install -y epel-release && yum install -y nginx && yum clean all

COPY dist /usr/local/share/cws-parent-portal-ui
COPY nginx.conf /usr/local/etc/cws-parent-portal-ui/nginx.conf

VOLUME /var/log/cws-parent-portal-ui

EXPOSE 8080

ENTRYPOINT [ "nginx" ]
CMD [ "-c", "/usr/local/etc/cws-parent-portal-ui/nginx.conf" ]

