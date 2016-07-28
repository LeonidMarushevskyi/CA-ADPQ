FROM centos:centos7
MAINTAINER Chris O'Meara

ADD http://pkg.jenkins-ci.org/redhat/jenkins.repo /etc/yum.repos.d/jenkins.repo
RUN rpm --import http://pkg.jenkins-ci.org/redhat/jenkins-ci.org.key
COPY docker.repo /etc/yum.repos.d/docker.repo
RUN yum install -y epel-release
RUN yum update -y && \
  yum install -y \
    docker-engine-1.9.1-1.el7.centos \
    git \
    initscripts \
    java-1.8.0-openjdk-devel \
    jenkins \
    nodejs \
    npm \
    ruby \
    ssh \
    sudo \
    unzip \
    wget && \
  yum clean all

## Install AWS CLI
RUN cd /tmp && wget -q https://s3.amazonaws.com/aws-cli/awscli-bundle.zip && unzip awscli-bundle.zip && awscli-bundle/install -i /opt/aws

## Allow jenkins to run in-container commands as root
COPY sudoers /etc/sudoers
COPY sudoers.d/99_jenkins /etc/sudoers.d/99_jenkins

## Set the timezone
RUN ln -sf /usr/share/zoneinfo/America/Los_Angeles /etc/localtime

## Deploy the run script, can't put it in /var/lib/jenkins because that volume will be mounted from Docker
COPY run /jenkins
RUN chmod +x /jenkins

## Helper scripts
COPY parent-portal-ecs-task /opt/parent-portal-build-help/parent-portal-ecs-task
RUN chmod +x /opt/parent-portal-build-help/parent-portal-ecs-task
COPY parent-portal-ecs-task.json.erb /opt/parent-portal-build-help/parent-portal-ecs-task.json.erb

VOLUME /var/lib/jenkins
VOLUME /var/log/jenkins

## Expose Ports for web and slave agents
EXPOSE 8080
EXPOSE 50000

USER jenkins

ENTRYPOINT [ "/jenkins" ]
