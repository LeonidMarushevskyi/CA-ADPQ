Prototype URL: [https://parentportal.tabordasolutions.net](https://parentportal.tabordasolutions.net)

[User Guide](https://github.com/taborda-cws-prototype/cws-parent-portal/wiki/Parent-Portal-Prototype-User-Guide)

## Taborda - ADPQ Prototype
# Technical Approach *(2)*

Our team utilized an “*outside-in*” product development approach to create our ADPQ prototype. “*Outside-in*” focuses on the end user’s needs and experience to drive features, along with supporting application program interfaces (API). Focusing on the end user means that their needs are implemented pervasively throughout the application. This practice promotes user satisfaction and adoption through continuous interaction/feedback.  Our team utilized the following approach for this prototype:

* Conducted user research to understand needs and trends within related industry. Our User Researcher conducted user interviews with a current CPS worker, along with people acting as the identified personas (parent/guardian) to create the initial application concept (2c).
* Created initial UI documented as wireframes (Balsamiq) which were presented to both CPS workers and staff members representing the personas associated with the system. Feedback was incorporated into an updated UI design (InVision) (2g).
* Identified user stories to form initial product backlog. 
* Developed fully interactive prototype web/mobile application. The initial prototype was presented to the representative user base from the user research phase. The findings from users interacting with the application, such as usability improvements, were incorporated into the final design (2g).
* Developed fully functioning prototype including back end API for data persistence as a series of “*vertical slices*” allowing testing throughout the effort.

The “*outside-in*” product development approach [incorporates the application development aspects of the U.S. Digital Services Playbook ](https://github.com/taborda-cws-prototype/cws-parent-portal/wiki/Adherence-to-U.S.-Digital-Services-Playbook). 


## Architecture

Our architecture is based on JavaScript (Angular.js) with a Java API (Dropwizard) and PostgreSQL database.

[Application Architecture](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/Application-Architecture)

# Adherence to U.S. Digital Services Playbook *(2)*

We followed all of the applicable Playbook plays. [Details here](https://github.com/taborda-cws-prototype/cws-parent-portal/wiki/Adherence-to-U.S.-Digital-Services-Playbook).

# Taborda Prototype Team
## Team Leader *(2a)*
Taborda assigned Brendan McGuire as the one leader with authority, responsibility, and accountability for prototype quality.

## Product Development Team *(2b)*
Multi-disciplinary and collaborative team includes eight (8) labor categories:

1. Product Owner – Brendan McGuire, CSM
2. Technical Architect – Rich Bach, CSM
3. Interaction Designer/User Researcher/Usability Tester – Lacy Carter, CSPO
4. Visual Designer – Husam Alrubaye
5. Front End Web Developer – Justin Castillo, Husam Alrubaye
6. Back End Web Developer – Tom Parker
7. DevOps Engineer – Chris O'Meara, CSM
8. Agile Coach – Gregg Hill, CSM

# Understanding of User Needs*(2c)*
We employed Human-Centered Design (HCD) to elicit user requirements to form the Product Backlog. The section below summarizes our HCD process for the prototype. [Details here](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/Human-Centered-Design-Process)

## Human-Centered Design *(2d)*

[Our HCD process](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/Human-Centered-Design-Process) leveraged the following techniques:

### Inspiration

* Developed **Design Challenge**: "*How might we implement a modern, convenient app for parents to communicate with case workers and easily access resources?*"
* Conducted 1:1 and Group Interviews with real case worker and several non-participating staff
* Conducted user research to identify target audiences
* Developed personas
* Brainstormed, starting with "_Diverge_" step to get all ideas (no concern for viability/feasibility), then "_Converged_" to reduce ideas list to only viable/feasible.

### Ideation
* Synthesized insights from users (wants):
	* Simple / Intuitive
	* Trustworthy
	* Case worker / children photos
	* Print time-stamped case worker conversations  
* Created [user stories](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/tree/master/stories)
	* [Create Profile](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/User-Story:-Create-Profile)
	* [Log In / Register](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/User-Story:-Log-in-or-Register)
	* [Update/View Profile](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/User-Story:-View-or-Update-Profile)
	* [View Foster Family Agencies in Zip](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/User-Story:-View-Foster-Agencies-in-Zip)
	* [Send/Reply to Message](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/User-Story:-Send-or-Reply-to-Message)
	* [Receive Messages](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/User-Story:-Receive-Messages)
* Brainstormed designs during internal & user [whiteboard sessions](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/tree/master/whiteboard) *(2c)*
* Updated user stories elaborations, including acceptance criteria *(2g)*

### Implementation
* Developed [Balsamiq low-fi wireframes](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/tree/master/wireframes) - using whiteboard designs & feedback from internal / user testing *(2g)*
* Conducted user tests and gathered feedback *(2f)*
* Developed [PhotoShop/InVision high-fi web mockup prototype](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/tree/master/hi-fi-mockups) - using wireframes / user testing feedback *(2g)*
* Conducted mockup prototype user tests; gathered feedback *(2f)*
* Developed live [Design Style Guide (DSG)](https://parentportal.tabordasolutions.net/guidelines.html) using bootstrap/SASS/CSS/HTML. DSG elements leveraged HTML mockup / user testing feedback. *(2g)*
* Conducted responsive design testing on desktop (Mac, PC) and mobile devices (iOS, Android) *(2h)*
* Updated DSG Design elements as prototype was elaborated – keeping DSG and prototype pages consistent and synchronized *(2e)*
* Developed prototype HTML using HTML mockup, user testing feedback, and DSG
* Iterated on pages, incorporating user testing feedback between iterations, finally reaching minimal viable product (MVP) *(2g)*

## Prototype Development
### Modern Open Source Technologies *(2i)*
#### All Open Source Solution Components with Build Dates
* Front end
	* Bootstrap (5/2016)
	* Javascript
	* Angular.js (5/2016)
	* jQuery (5/2016)
	* SASS (5/2016)
	* Font-Awesome (5/2016)
	* JSON Web Token (5/2015)
	* WAVE (3/2016)
	* Web Server – NGINX
* Back end
	* Java (6/2016)
	* Dropwizard (5/2016)
	* Jetty – Web Server (6/2016)
	* Jersey – RESTful web services (6/2016)
	* Jackson – JSON Processor (5/2016)
	* Hibernate ORM (4/2016)
* Database – PostgreSQL
	
#### Automated Unit Testing *(2k)*
* Front end
	* Protractor (6/2016)
	* Jasmine (3/2016)
* Back end
	* Junit 4 (6/2016)
	* Mockito (6/2016)

#### Continuous Integration *(2i, 2l, 2m)*
Implemented Jenkins inside Docker container on AWS for automated testing as code is checked in to GitHub.

* Configuration Management - GitHub
* Continuous Integration Automation - Jenkins [CI Screenshots] (https://github.com/taborda-cws-prototype/cws-parent-portal/wiki/Continuous-Integration) (6/2016)
* Build
	* Front end – Gulp (6/2016)
	* Back end – Gradle (6/2016)
- Deploy
  - Server – Docker (5/2016)
  - Developer – Docker Composer (5/2016)

#### Prototype Deployment Environment *(2j)*
The prototype deployed to AWS (FedRAMP-certified IaaS). 

[Infrastructure Architecture](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/Infrastructure-Architecture)


#### Configuration Management *(2n)*
The GitHub Repository is organized as:

* [Taborda CWS Prototype](https://github.com/taborda-cws-prototype/cws-parent-portal)

Additional repositories were utilized to support CI pipelines and are available below:

[GitHub Repositories](https://github.com/taborda-cws-prototype/cws-parent-portal/wiki/GitHub-Repositories)

### Continuous Monitoring *(2o)*
Implemented the following monitoring:

* NodePing validates availability every 3 minutes and alerts the Taborda operations team of service availability disruption.
* Datadog continuously monitors system resources and alerts the Taborda operations team of potential issues.
* PagerDuty delivers alerts to the Taborda operations team according to a 24x7 duty schedule.

### Containerization *(2p)*
Deployment in Docker container.

### Deployment Documentation *(2q)*
[Deployment Docs](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/Deployment-Documentation)

### Prototype / Underlying Platform Licensing *(2r)*
The prototype and underlying platforms are open licensed and free of charge.

### [Notes and Assumptions](https://github.com/taborda-cws-prototype/cws-parent-portal-documentation/wiki/Notes-and-Assumptions)
