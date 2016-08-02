# CHHS Foster Care Services Prototype

## Links
* **Landing Page:** [chhs-prototype.com](http://chhs-prototype.com/)
* **Prototype:** [chhs-prototype.binti.com](https://chhs-prototype.binti.com/)

## Instructions to test
* **As a parent:** Start at the landing page, [chhs-prototype.com](http://chhs-prototype.com/)
* **As a social worker**: To login as a social worker and reply to messages from parents, [login here](https://chhs-prototype.binti.com/users/sign_in) and use username: social@binti.com and password: binti456

### a. Assigned one leader
Felicia Curcuru, Binti’s CEO and head of product management, is responsible for the prototype’s quality.

### b. Assembled a multidisciplinary and collaborative team
Our team includes 8 labor categories: Product Manager, Front End Web Developer, Backend Web Developer, DevOps Engineer, Visual Designer, User Researcher, Technical Architect, Writer.

### c. Understood what people needed
We conducted user interviews with [basic mock-ups](Product%20documentation/Mockups), a [clickable prototype](https://invis.io/2N7JV7I63), and a live prototype. We iterated on the design and functionality at each step:
* We interviewed biological parents of foster youth, foster parents, foster care caseworkers, and foster youth. While the tool is primarily meant for biological parents, other groups also expressed interest, so we considered them in our design.
* To reduce friction, we decided to not hide the search functionality behind login, since multiple groups of users wanted to use this search and the data is publicly available. We only require login for biological parents to message their caseworker.
* Several of the groups we interviewed preferred to use their mobile devices, so we decided to make the application is mobile-friendly.
* Many biological parents of foster youth in California speak Spanish as their primary language, so the app is available in Spanish.
* We decided to send an email notification for messages received, as It’s important for users to receive this each time they receive a message in order to alert them to login and reply. 
* Most people were interested in the map search and messages, but not profiles. Therefore, we focused our efforts on those two and kept the profile basic.
* The RFI mentioned foster family agencies and residential treatment facilities. The Q&A specified agencies. We learned that stakeholders were interested in both, so we built both.
* We tested search results in both urban and rural zip codes and decided to show the 10 closest matches, sorted by distance, which gave enough results for rural users. If we had shown results within 5 miles, rural users may not have had many results.
* We host the facilities index ourselves rather than query the Socrata API, so that we can geocode some of the facilities missing latitude/longitude in the Socrata API.
* Social workers we interviewed wanted to be able to edit the website, so, we also built a WordPress landing page that can be easily edited by non-technical users.

### d. Used at least three “human-centered design” techniques or tools

1. Shadowed each type of prospective user 
2. Interviewed prospective users 
3. Defined the audience & created user stories 
4. Tested prototypes with people 
5. Iterated based on feedback

### e. Used a design style guide
Our design is based on the [U.S. Web Design Standards](https://standards.usa.gov/). We deviated slightly from the color palette for background and footer colors, after hearing feedback that a grey background and footer made the site seem antiquated.

### f. Performed usability tests
We performed usability tests with mockups of the site, and then a clickable prototype with people for feedback before we started coding. Then we did further usability tests with the working prototype. You can see the decisions these tests helped us make in Section C above.

See [detailed user interview notes.](Product%20documentation/User%20interviews)

### g. Used an iterative approach

We used an iterative approach, building a basic version, getting feedback, and iterating. We used [static mockups](Product%20documentation/Mockups), [clickable mockups](https://invis.io/2N7JV7I63), and live versions of the prototype to get feedback and iterate. See section C above for the decisions we made during these iterations.

### h. Created a responsive prototype that works on multiple devices
We built a responsive app and tested it across browsers and devices. We adhered to the U.S. Digital Services Playbook for responsive design and browser compatibility.

### i. Used at least five modern and open-source technologies
* Ruby, version April 2016
* Rails, version March 2016
* PostgreSQL, version January 2016
* jQuery, version January 2016
* Soda-ruby, version February 2016
* U.S. Web Design Standards, version June 2016

### j. Deployed the prototype on a Paas provider
We deployed the prototype on Heroku, a PaaS provider. Heroku integrates with GitHub, allowing for easy automated deployment. Our prototype can easily operate on a substitute IaaS or PaaS  like Amazon Web Services, Cloud.gov, or Google Cloud Services or substitute PaaS like Microsoft Azure.

### k. Developed automated unit tests
We developed unit tests as we built our code to save time, ensure that our code is reliable and has less errors. 

### l. Setup or used a continuous integration system
We connected our GitHub repo to [Travis CI](https://travis-ci.org/), a full-service continuous integration platform. 

### m. Used configuration management
In our development environment, we use [dotenv](https://github.com/bkeepers/dotenv), a local configuration management tool [version March 2016]. In production, we manage configuration using Heroku’s official environment-based tooling, conforming to [The Twelve-Factor App](http://12factor.net/) manifesto.

### n. Used continuous monitoring
We use [Papertrail](https://papertrailapp.com/), a cloud vendor that provides hosted log management for continuous monitoring. It aggregates our logs, indexes them for ready search, and notifies us of errors. 

### o. Deployed software in a container
Heroku uses Linux containers (LXC), but our app will run in any configuration of physical servers, virtual machines, and containers. We follow the simplest possible conventions so our clients are not locked into one particular way of deploying.

### p. Provided sufficient documentation to install and run their prototype on another machine

We have provided [documentation directly in GitHub.](https://github.com/binti-family/CHHS_Prototype/blob/master/DEVELOPMENT.md)

### q. Open licensed and free platforms used 

The prototype, Rails, and Rails plugins are MIT-licensed and Ruby is 2-clause BSD licensed. These are free, permissive licenses.

We also complied with the U.S. Digital Services Playbook. [Please read our detailed notes on each play here.](USDS_PLAYBOOK_CHECKLIST.md)
