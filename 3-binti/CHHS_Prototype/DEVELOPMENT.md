# Development

## CI

[![Build Status](https://travis-ci.org/binti-family/CHHS_Prototype.svg?branch=master)](https://travis-ci.org/binti-family/CHHS_Prototype)

## Getting Started

1. [Install Ruby](https://www.ruby-lang.org/en/documentation/installation/) (we use v2.3.1)

2. [Install Bundler](http://bundler.io/#getting-started)

```
gem install bundler
```

3. Install [PostgreSQL](https://www.postgresql.org/download/) and [PostGIS](http://postgis.net/install/)

4. Clone the app and enter its directory

```
git clone git@github.com:binti-family/CHHS_Prototype.git
cd CHHS_Prototype
```

5. Install Rails and other Ruby gems:

```
bundle install
```

6. Setup your database

```
rake db:setup
```

7. Setup your development environment variables

```
cp .env.template .env
```

8. Run the tests

```
rake
```

9. Load facilities data

There are fewer than 3000 facilities, so for your convenience I've included them (post-geocoding) in `config/data/facilities.yml`.

To load this file into your database:

```
rake load_facilities_dump
```

Alternatively, you may generate the data yourself.

First you must [register for a free API key here](https://opendata.socrata.com/login) and edit you .env, configuring CHHS_API_TOKEN and CHHS_API_SECRET

Then run:

```
rake import_facilities
rake geocode_facilities
```

10. Run the development server

```
rails s
```

You can visit the development site at [http://localhost:3000](http://localhost:3000)

## Deployment

This is a conventional Rails app. We deployed our live instance to [Heroku](https://www.heroku.com/), but nearly any cloud vendor should support this app.
