## Foster Care

**A Ruby on Rails application that demo about foster care management.**<br/>


###1. Use the demo:
**Public URL:** [https://bitoptech.org/](https://bitoptech.org/)


This program has 4 user roles for users:

- Parent
- Children
- Facility administrator
- Case Worker

**Demo login users are**

- Parents:
```
  email: 'parent_<number>@hanoian.com'
      with number from 1 to 3. For example 'parent_1@hanoian.com'

  password: 'development'
```

- Childrens:
```
  email: 'child_<number>@hanoian.com'
      with number from 1 to 200. For example 'children_5@hanoian.com'

  password: 'development'
```

- Facility administrators:
```
  email: 'facility_admin_<number>@hanoian.com'
      with number from 1 to 20. For example 'facility_admin_10@hanoian.com'

  password: 'development'
```

- Case workers:
```
  email: 'case_worker_<number>@hanoian.com'
      with number from 1 to 40. For example 'case_worker_15@hanoian.com'

  password: 'development'
```

**Messsaging functionality**

When you login as any user as specified above, you can send messages and receive messages from different users in the system.</br>
This is an internal messaging system. Recipients are specified using email addresses.

- Send to one user: Use single email address, for example `child_10@hanoian.com`
- Send to many users: Use multiple emails, separated by comma, for example `child_5@hanoian.com, parent_2@hanoian.com, case_worker_3@hanoian.com`

###2. Application stack

- Ruby 2.3.0
- Rails 4.2.6
- Database: PostgreSQL 9.4
- UI using jQuery and Bootstrap

###3. Development on your local machine

On your local machine, you must have the following software installed:

- [RVM](https://rvm.io/rvm/install)
- Ruby 2.3.0 installed via RVM `rvm install 2.3.0 -- --enable-shared`
- PostgreSQL 9.4 with postgresql development library. 
- javascript server side runtime, ideally [NodeJS](https://nodejs.org/en/download/).


**You should fork this project [git@github.com:linhchauatl/foster_care.git](https://github.com/linhchauatl/foster_care) to your own github account.**

After you fork the project, on your local machine, run the following commands:

```
git clone git@github.com:<YOUR_GITHUB_USER>/foster_care.git
cd foster_care
```

Then you must modify the file **config/database.yml** to match with `username` and `password` of PostgreSQL on your machine. If you use the default PostgreSQL config when you install it, you don't need to modify **database.yml**

Run the following commands:

```
gem install bundler
bundle
rake db:create db:migrate
```

Load sample data from the file **db/sample_postgresql_data.sql**:

```
pg_restore -U postgres -W  -d <DATABASE NAME> 'db/sample_postgresql_data'
```

Now you can write code and test on your local machine.

###4. Run development environment from a docker container

**If you don't want to setup Ruby 2.3.0 and PostgreSQL locally on your machine, you can write code and test Foster Care from a docker container.**

I would assume that you are familiar with running docker on Mac and Linux.

* Clone foster_care code to local machine:

    ```
    git clone https://github.com/linhchauatl/foster_care.git
    cd foster_care
    ```

* One time run: Build the container

    From terminal, go to the directory where you just clone the code of **foster_care**, and type:

    ```
    docker build -t foster_care_ubuntu:latest .
    ``` 

Afterward, the docker container is ready to be used.

**Steps to run the foster_care Rails app in the container**

- Each time you want to run the container: On your local machine, go to the directory **foster_care**, use this command:

    ```
    bin/run_container.sh
    ```

    You can look in the file **bin/run_container.sh** to see the details about how to run container.


- Connect to the running container: From the local machine's terminal, run: `bin/ssh_to_container.sh`

- First time you SSH to the container, run `rake db:create db:migrate`, then use `pg_store` to load sample data, similar to that step in **3. Development on your local machine**.

- Run rails server inside the container: From the container command line, run: `bin/run_rails.sh`

Afterward, if you run docker on Linux, you can access the foster_care via http://localhost:3000.

If you run docker on Mac, on local Mac, open another terminal, run the command `docker-machine ls` to see the IP address of your docker machine. You will see something similar to:

```
NAME      ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER    ERRORS
default   *        virtualbox   Running   tcp://192.168.99.100:2376           v1.10.1
```

So your foster_care will be accessible via http://192.168.99.100:3000.


**Your `foster_care` directory on local machine is mounted to the container, so when you write code on the local machine, the code is available immediately to the Rails app running inside the container.**


###5. Deployment

This application is deployed using capistrano to a server (or servers) that has the following software installed:

- [RVM](https://rvm.io/rvm/install)
- Ruby 2.3.0 installed via RVM `rvm install 2.3.0 -- --enable-shared`
- PostgreSQL 9.4 with postgresql development library. 
  ```
  CentOS dev library: postgresql-devel.x86_64
  Ubuntu dev library: postgresql-server-dev-9.4
  ```

- Inside PostgreSQL, you must create a database that match the name specified in your **database.yml**. For example, for staging environment, it is `foster_care_staging`
- javascript server side runtime, ideally [NodeJS](https://nodejs.org/en/download/).
- [nginx](http://nginx.org/en/download.html) webserver that configured to talk with [puma](https://github.com/puma/puma) of `foster_care`.
- SSH login with public key authorization configured to allow one of your private keys on your local machine to login. 
- Generate public/private keypair on target server, and add public key to your github account. [Information about keys generation](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/)

- Once you setup the server(s), on your local machine, modify the file **config/deploy/deploy_config.yml** to match with your server configurations and point to correct local private key on your development machine.



Then on your local machine, you can use command `cap staging deploy` to deploy to your staging server(s).

Deploy to production server(s): `cap production deploy`

###5. Prototype Features / Assumptions
```

a.	By default, only licensed facilities will be displayed and allowed to be searched for. 
To see all facilities, clear “Filter By” field. 
b.	Filter By Field: Filter by is our ‘google search’ 
•	You can type 'LICENSED' to display all the licensed facilities. 
•	Or you can type 'LOS ANGELES' to get all the facilities with all statuses in the city of LOS ANGELES.
•	Or you can type 'LONG BEACH' to display all licensed facilities in LONG BEACH.
•	Or you can type 'LOS ANGE 90046' to displayed all the licensed in Los Angeles with the zip code 90046
•	Or you can type 'CLOS 9210' to display all the closed facilities within zipcodes 9210x
c.	Since this data is Open Data, we allowed the data to be displayed even when the user is not logged in.
d.	Not all the fields provided in the Open Data ‘foster family agency locations’ are displayed in the prototype 
due to screen size limitations.
```

###6. ADPQ Technical Requirements
a.	Assigned one leader and gave that person authority and responsibility and held that person accountable for the quality of the prototype submitted 
```
Mr. Niranjan Hiras was the Product Owner for this Foster Care Prototype Project. He was ultimately responsible 
for the quality and features of the product  and had absolute authority in what goes into the product. 
All stakeholders agreed Mr. Hiras has the authority to assign tasks and make  decisions about features and 
technical implementation details.
```
b.	Assembled a multidisciplinary and collaborative team that includes, at a minimum, five of the labor categories as identified in Attachment C - ADPQ Vendor Pool Labor Category Descriptions 

```
We assembled a multi-disciplinary team for creation of this Prototype. Here are our team members, 
their roles and the Radian Solutions’ CMAS category.

1- Product Manager - Niranjan H. (CMAS Category: Project Manager)
2 - Technical Architect - Linh C. (CMAS Category: Technical Architect)
3 - Interaction Designer/User Researcher/Usability Tester - Siva N.	(CMAS Category: Senior Systems Analyst)
11 - Agile Coach - Prashanth S.(CMAS Category: Project Manager)
12 - Business Analyst - Ahmed S. (CMAS Category: Senior Systems Analyst)
```

c.	Understood what people needed (see Note #1), by including people in the prototype development and design process 

```
Radian Solutions included the people who are not part of the agile development team mentioned above. 
We presented the information provided in the RFI and asked them to list out what they would like to see. 
These ‘people’ are non-technical who perform roles such as Office Manager, recruiter, or HR analyst. 
Based on the feedback we designed the sprints, features, business requirements, and user stories.
```

d.	Used at least three “human-centered design” techniques or tools 

```
•	Desireability: 
o	Understand what people need: We defined early on the purpose of this prototype. 
o	Make it Simple and Intutive: Our design is simple and intuitive for anyone to use.
•	Feasibility:
o	Bring in Experienced team: All our team members have over 10 years of experience and are 
experts in their roles. They have worked on multiple projects and have successfully delivered 
their services. 
o	Choose a modern technology stack: We chose the technology stack of Ruby on Rails, 
PostgreSQL, JQuery, Bootstrap, JavaScript and NodeJS.
o	Deploy in a Flexible hosting environment: We chose to host the prototype on Amazon AWS 
hosting environment.
•	Viability:
o	Assign one leader and hold that person responsible:  We assigned the ownership to one product owner 
and held him responsible for the final product.
```

e.	Created or used a design style guide and/or a pattern library 

```
We used Bootstrap to style the Look and Feel of the site. We followed Twitter Bootstrap pattern.
```

f.	Performed usability tests with people 

```
We engaged our non-technical ‘people’ to perform the usability tests. The Office manager, recruiter, 
and HR analyst were involved in testing the prototype.
```

g.	Used an iterative approach, where feedback informed subsequent work or versions of the prototype 

```
We used 3 sprints, each one lasting for one week. 
•	Sprint 1: Allow parents of foster kids to establish and manage their profile.
•	Sprint 2: Search for a facility with in the zipcode.
•	Sprint 3: Communicate with the case worker via a private inbox.
```

h.	Created a prototype that works on multiple devices, and presents a responsive design 

```
Our prototype was tested on three devices: 
•	Windows Computer, 
•	iPhone (safari) and 
•	Android tablet (Chrome)

On the computer, we tested using 
•	Google Chrome, 
•	Internet Explorer and 
•	Mozilla FireFox.
```

i.	Used at least five modern (see Note #2) and open-source technologies, regardless of architectural layer (frontend, backend, etc.) 

```
The following languages were used to create the prototype: 
•	Ruby 2.3.0
•	Rails 4.2.6
•	PostgreSQL 9.4
•	JQuery
•	BootStrap
•	JavaScript
•	NodeJS
•	RVM
•	RSpec

```

j.	Deployed the prototype on an Infrastructure as a Service (Iaas) or Platform as Service (Paas) provider, and indicated which provider they used. 

```
•	The prototype is deployed on Amazon AWS 

```

k.	Developed automated unit tests for their code

```
•	We used RSpec to do the unit testing.

```

l.	Setup or used a continuous integration system to automate the running of tests and continuously deployed their code to their IaaS or PaaS provider. 

```
•	We have automation deployment to PaaS, i.e. auto deployment program using Capistrano to deploy to AWS. 
We were considering implementing Jenkins continuous deployment but we did not have enough time to implement it.
```

m.	Setup or used configuration management 
```
•	We run different environments, namely, "development", "test", "staging", "production" with different 
managed configurations.
```

n.	Setup or used continuous monitoring 

```
•	We could have setup with AWS monitoring, using AWS ELB (Elastic Load Balancer) but we don’t have time 
for this setup.
```

o.	Deployed their software in a container (i.e., utilized operating-system-level virtualization) 

```
•	We have a lot of applications that are deployed to docker containers. This being a prototype we used the same 
environment. On a full-fledged agile project, we should be able to deploy the software in a container and utilize 
operating system level virtualization.
```

p.	Provided sufficient documentation to install and run their prototype on another machine 

```
•	Provided documentation in Readme.MD file on how to run the prototype on another machine
```

q. Prototype and underlying platforms used to create and run the prototype are openly licensed and free of charge 

```
•	All technologies used are Open Source and free of charge
```
