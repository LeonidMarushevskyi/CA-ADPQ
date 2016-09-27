FROM ubuntu:latest
RUN apt-get update
RUN apt-get install -y build-essential curl wget git vim postgresql postgresql-contrib postgresql-server-dev-9.3

RUN gpg --keyserver hkp://keys.gnupg.net --recv-keys 409B6B1796C275462A1703113804BB82D39DC0E3
RUN \curl -sSL https://get.rvm.io | bash -s stable
RUN echo "source /etc/profile.d/rvm.sh" >> /root/.bash_profile
RUN /bin/bash -l -c "rvm requirements"
RUN /bin/bash -l -c "rvm install 2.3.0 -- --enable-shared"
RUN echo "gem: --no-document" >> $HOME/.gemrc
COPY ./Gemfile $HOME/
COPY ./Gemfile.lock $HOME/
RUN /bin/bash -l -c "rvm use 2.3.0@foster_care --create && gem install bundler && cd $HOME && bundle"

RUN wget https://nodejs.org/dist/v4.4.5/node-v4.4.5-linux-x64.tar.xz
RUN tar -xvf node-v4.4.5-linux-x64.tar.xz
RUN rm node-v4.4.5-linux-x64.tar.xz
RUN echo "export PATH=\$PATH:.:$HOME/bin:/node-v4.4.5-linux-x64/bin" >> $HOME/.bash_profile
RUN echo "cd /foster_care" >> $HOME/.bash_profile

RUN /etc/init.d/postgresql start & \
    sleep 5s &&\
    pg_dropcluster --stop 9.3 main &&\
    pg_createcluster --start -e UTF-8 9.3 main &&\
    /etc/init.d/postgresql start & \
    sleep 10s &&\
    sudo -u postgres psql -c "ALTER USER postgres WITH PASSWORD 'postgres';"

COPY pg_hba.conf /etc/postgresql/9.3/main/
RUN sudo chmod o+r /etc/postgresql/9.3/main/pg_hba.conf
RUN echo "listen_addresses='*'" >> /etc/postgresql/9.3/main/postgresql.conf


EXPOSE 5432
    
RUN mkdir /projects
RUN mkdir $HOME/bin

EXPOSE 3000

ENTRYPOINT /bin/bash -l -c  "/etc/init.d/postgresql restart  && /bin/bash"