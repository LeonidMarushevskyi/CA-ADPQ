Open Agile Development Pre-Qualified (ADPQ) Vendor Pool prototype  Application Portal
=====================================================================================

This repo is ADPQ's working prototype for ADPQ Vendor Pool Submission Requirements.

The working prototype is an application that will allow parents of foster kids 
to establish and manage their profile, and view children's residential facilities in their zip code,
and communicate with the case worker via a private inbox. The working prototype will access open data 
through the HHS API to retrieve data about foster family agency locations that are nearby

We elected to host our ADPQ- prototype on Amazon could in order to demonstrate the organization cloud
knowledge & capabilities and utilize the Amazon full-fledged computing, Network &Security, Developer and Management Tools
 
Our Prototype is running on the Amazon  EC2 instance Cloud WindowsServer-2012-R2

**ADPQ Vendor Pool Prototype host site :**
 http://ec2-52-90-174-90.compute-1.amazonaws.com:8080/home.php

**ADPQ's  portal ability include**
-   Parent & Caseworker Registration
Users mentioned in the below table can be logged into the system without registration

Role          | Fname         | Lname          | Email/User Id                      | Password
------------- | ------------- | -------------  | ------------------------------     | -------
Parent        | Charles       | Major          | Charles.Major@bmassociates.com     | 1234
Parent        | John          | Smith          | john.smith@bmassociates.com        | 1234
Parent        | Joe           | Monroe         | joe.monroe@bmassociates.com        | 1234
Parent        | Roger         | Preston        | roger.preston@bmassociates.com     | 1234
Parent        | Mary          | Singer         | mary.singer@bmassociates.com       | 1234
Case Worker   | Brian         | Taylor         | Brian.Taylor@bmassociates.com      | 1234
Case Worker   | Ronald        | Anderson       | Ronald.Anderson@bmassociates.com   | 1234
Case Worker   | Anthony       | Thomas         | Anthony.Thomas@bmassociates.com    | 1234
Case Worker   | Kevin         | Jackson        | Kevin.Jackson@bmassociates.com     | 1234


-	Search Foster Family agency location based on Zipcode
-	Update Parent & Caseworker profile information
-	Messaging functionality between Parent and Case worker
-	Chatting functionality between Parent and Caseworker


Approach
--------
-	Put together an Agile team and delivered prototype within one two week sprint 
-   Sprint Backlogs  (https://github.com/srinivasbmassociates/ADPQ-Repo/blob/master/projectmanagement/ADPQ%20Sprint-backlog-template.xlsx)
-   Sprint plan (https://github.com/srinivasbmassociates/ADPQ-Repo/blob/master/projectmanagement/Agile-project-plan-template.xlsx)
-	Setup a continuous testing & delivery server in minutes with AWS CodePipeline deploying the ADPQ on EC2 instance of  Amazon Cloud- PAAS
-	Conducted user feedback sessions and learned that user utility of the application was limited by
-	Set up automated tests and monitoring tools to maintain the production application.


Pool Three Requirements
------------------------

**Assigned one leader, gave that person authority and responsibility and held that person accountable for the quality of the prototype submitted:**
An internal team meeting was convened to determine staffing; Siddharth was assigned Team Leader based on skills and availability:  (https://github.com/srinivasbmassociates/ADPQ-Repo/blob/master/TEAM.md)

**assembled a multidisciplinary and collaborative team including a minimum of 5 labor categories from the Development Pool labor categories to design and develop the prototype:**
Siddharth organized a team based on skills and availability. 
We used the following labor categories:
-	Product Manager
-	Technical Architect
-	Agile Coach
-	Visual Designer
-	Backend Web Developer
-	Front End Web Developer
-	Usability Tester
-	User Researcher


**understand what people need, by including people in the prototype development and design process:**
Involved business team & NON-IT staff both not directly involved in prototype development, throughout the project, as subject matter experts in end-user open data use cases, and as non-engineer QA testers (https://github.com/srinivasbmassociates/ADPQ-Repo/blob/master/projectmanagement/Feedback.xlsx)

**used at least three "human-centered design" techniques or tools**
We followed the human-centric design process of Discovery, Ideation and Prototyping (or Implementing). We used the target audience such as Parents and Case workers to understand their preferences or dislikes of existing sites where they can lookup nearby foster homes and ease of communication needs for the Case workers with the parents. We used the same group who were in the discovery phase to participate where needed in the other two phases as well to continually improve upon the initial design to cater to the target audience/users. 
Team used:

-  Inspiration

Initially we framed the Required  UI Design through multiple iteration with team by going through
Child welfare site , California Govt agency site , U.S. Digital Services  
Playbook, defining the audience for the sites by conducting group discussion with target crowd
involving parents , caseworkers  etc...
Referenced Sites
(https://www.childwelfare.gov/) 
(http://www.casaforchildren.org/) 
(http://www.acf.hhs.gov/) 
(https://chhs.data.ca.gov/) 

-  Ideation

Here team Brainstormed by mashing up the above ideas, running over Design Principles and  documented  on front UI and Backend to arrive on basic Prototype including the technology stack  selection.

-  Implementation

Here we planned on the required resources and time and followed the Agile process with two  
week sprint to develop & deliver the prototype by Iterating , prototyping   &  Testing with external users, incorporating user feedback throughout process 
(https://github.com/srinivasbmassociates/ADPQ-Repo/blob/master/projectmanagement/Feedback.xlsx)

Human-centered design"  techniques references used (http://www.designkit.org/methods#filter)


**created or used a design style guide and/or a pattern library:**
Used Bootstrap as design style guide and pattern library: (http://getbootstrap.com/)  

**performed usability tests with people:**
Demonstrated work in progress prototype to non-engineers and collected feedback
(https://github.com/srinivasbmassociates/ADPQ-Repo/blob/master/projectmanagement/Feedback.xlsx)

**used an iterative approach, where feedback informed subsequent work or versions of the prototype**
Team talked through proposed approach  with business team & NON-IT staff, and used this feedback to establish initial technical and conceptual approach
 After initial deployment, the team solicited additional feedback on requirement tracking, resulting in specific issues we then addressed 
a)Use of AWS Code pipeline for continuous integration  which has an additional test cases running and validating build  and then deploying instead of AWS Code Deploy directly deploys the build  (https://github.com/srinivasbmassociates/ADPQ-Repo/blob/master/projectmanagement/Resource/Codepipeline.png)

**created a prototype that works on multiple devices, and presents a responsive design**
ADPQ's leverages bootstrap (http://getbootstrap.com/), so is responsive out of the box (note: in the interests of time, this ADPQ prototype was optimized only for IE,Firefox & Chrome browsers)

**used at least five modern and open-source technologies, regardless of architectural layer (frontend, backend, etc.)**
-	NodeJS: (https://nodejs.org/) 
-	Gulp:( http://gulpjs.com/)
-	PHP: (http://php.net/)
-	 MongoDB: (https://www.mongodb.com/)
-	JQuery: (https://jquery.com/)
-	Bootstrap: (http://getbootstrap.com/)  
-	PHP codecoverage (http://phpcoverage.sourceforge.net)

**deployed the prototype on an Infrastructure as a Service (IaaS) or Platform as a Service (PaaS) provider, and indicated which provider they used**
The prototype is deployed on PaaS - Amazon Cloud Infrastructure 
 PaaS  is  (http://aws.amazon.com/)
 **(http://ec2-52-90-174-90.compute-1.amazonaws.com:8080/home.php) **

** wrote unit tests for their code **
ADPQ uses 
PHP Development unit tests  (https://github.com/srinivasbmassociates/ADPQ-Repo/tree/master/test/app) Functional Test cases  in Selenium (https://github.com/srinivasbmassociates/ADPQ-Repo/tree/master/QAtestcases)


**set up or used a continuous integration system to automate the running of tests and continuously deployed their code to their IaaS or PaaS provider**
The site was setup using AWS  continuous integration  & "Continuous Delivery" mode  using AWS CodePipeline is a continuous delivery service (https://github.com/srinivasbmassociates/ADPQ-Repo/blob/master/projectmanagement/Resource/Codepipeline.png)

**set up or used configuration management**
ADPQ  manages all configuration for sites including  Node.js, MongoDB,  PHP, and the file system
(https://github.com/srinivasbmassociates/ADPQ-Repo/blob/master/projectmanagement/Resource/Codepipeline.png)

**set up or used continuous monitoring**
AWS monitoring
(https://github.com/srinivasbmassociates/ADPQ-Repo/blob/master/projectmanagement/Resource/Cloud%20Monitoring%20of%20EC2%20windows%20instance.png)

**deploy their software in a container (i.e., utilized operating-system-level virtualization)**
In the interest of open source technologies being used, the main prototype site itself -ADPQ  is not running on containers  but on the Node.js application server
It could also be readily be deployed to AWS PaaS 

**provided sufficient documentation to install and run their prototype on another machine**
ADPQ  can be installed locally on system: WindowsServer-2012-R2  
, PHP,MongoDb,Nodejs,Gulp  our tool for ADPQ sites 
(https://github.com/srinivasbmassociates/ADPQ-Repo/blob/master/projectmanagement/Deployment%20steps.doc)

**openly licensed and free of charge:**
Yes, our source code is openly licensed and free of charge

