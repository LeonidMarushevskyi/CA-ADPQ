FROM mysql:latest
MAINTAINER Jonathan Martin <jmartin2@surgeforward.com>

##
# Add custom configurations
COPY ./conf.d /etc/mysql/conf.d

##
# Add scripts to execute at startup
COPY ./docker-entrypoint-initdb.d /docker-entrypoint-initdb.d
