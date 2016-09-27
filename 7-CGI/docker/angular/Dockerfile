FROM nginx

# Set timezone
RUN echo "America/Los_Angeles" > /etc/timezone \
 && dpkg-reconfigure -f noninteractive tzdata

# Install wget and install/updates certificates
RUN apt-get upgrade \
 && apt-get update \
 && apt-get install -y -q --no-install-recommends \
    ca-certificates \
    wget \
    xz-utils \
    bzip2 \
    git \
 && apt-get clean \
 && rm -r /var/lib/apt/lists/*


# Install Node 4.4.5

RUN wget https://nodejs.org/dist/v4.4.5/node-v4.4.5-linux-x64.tar.xz
RUN tar --strip-components 1 -xf node-v4.4.5-linux-x64.tar.xz -C /usr/local
RUN rm -rf ./node-v4.4.5-linux-x64.tar.xz

# Install NPM 3
RUN npm install -g npm@latest

COPY ./ /src
COPY ./docker/angular/conf.d/default.conf /etc/nginx/conf.d/default.conf

WORKDIR /src

RUN npm install --unsafe-perm; npm run build;

# PORT
EXPOSE 80
