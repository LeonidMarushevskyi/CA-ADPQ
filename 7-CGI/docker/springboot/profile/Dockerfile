FROM centos:latest

RUN yum -y update; yum -y install java-1.8.0-openjdk-devel java-1.8.0-openjdk-headless wget unzip ; yum clean all;

# Install Gradle
RUN mkdir -p /opt; cd /opt; wget http://services.gradle.org/distributions/gradle-2.13-bin.zip; unzip gradle-2.13-bin.zip ; ln -s gradle-2.13 gradle; ln -s /opt/gradle/bin/gradle /usr/local/bin/gradle ;echo -e 'export GRADLE_HOME=/opt/gradle\nexport PATH=$PATH:$GRADLE_HOME/bin' | tee /etc/profile.d/gradle-env.sh

COPY ./ /src
WORKDIR /src/services/ProfileService

ENV JAVA_HOME=/usr/lib/jvm/jre-1.8.0-openjdk/
RUN /usr/local/bin/gradle build -Pspring.profiles.active=testing -x test

EXPOSE 8081

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=testing","-jar","/src/services/ProfileService/build/libs/CGI-CHHS-ADPQ-ProfileService-0.0.1-SNAPSHOT.jar"]
