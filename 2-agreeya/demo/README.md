### URL 
##### http://fosterfamilies.agreeya.net/chhs

This project is about building a working prototype that would enable Families to Register and Manage their profiles so that they can receive foster placements. Apart from Registering their Profiles, prototype functionality would allow registered users to Manage Profiles, Search through Children Residential Facilities within their zipcode and locate Foster care agencies in their nearby neighbourhood. Registered users can view their private mailbox for communication emails sent or received. The prototype utilizes the publicly exposed CHHS API to retrieve information related to the Foster Care Agencies in nearby neighborhood of the user.
***********************************************************************************************************************************************************************

### CHHS Prototype Development Approach
AgreeYa as a CMMI certified organization brings over 17 years of experience of delivering complex and large projects leveraging Agile development methodology, templates and guidelines. We recognize each of the projects have a set of unique strategic objectives, business needs, operational considerations and organizational challenges. As a result, we believe in a collaborative, well-communicated and consultative approach.
In this case as well, the CHHS Prototype development was carried out following Agile Scrum methodology that provides ability to develop iteratively and incrementally. A multidisciplinary team led by experienced Product Owner/Project Manager carried out all the activities in order to successfully deliver a working CHHS prototype. The entire requirements elicitation, design and development process involved representatives from usergroups to work in collaboration with the scrum team.  
During the prototype development Joint Application Development Sessions (JAD) were conducted to understand the need/requirement, deliberated functionality workflows, design screen mockups and documented Business Requirement Document (BRD). Basis this BRD, the product backlog -Feature/User stories, tasks were  created with approved backlog priority in the Redmine tool. Daily standups, Task Boarding were done to review the progress of the work.
  
#####Technical Approach and General Architectural considerations

######Service Oriented Architecture 
The prototype is based on the Service oriented architecture (SOA) with the client-tier, business-tier and data-tier layers. The solution is built using AngularJS client-tier and Java backend with Jersey RESTful Web Services framework which is an open source, production quality, framework for developing RESTful Web Services in Java. 
######Loose Coupling 
All services are loosely coupled and autonomous to provide maximum flexibility in development, deployment and usage. Each service communicates with another through a service invocation and allow ease of communication without much concerns of service's internal implementation. Also loose coupling are implemented by layering the architecture in multiple tiers and segregating presentation, application and database layer.  

######Lightweight Data Exchange Format  
Data exchange between client-tier and business-tier (services) is through JSON objects. We believe JSON is a lightweight data interchange format and its use moves the processing of data (returned) on to the browser and hence reduces load on the server. Even though usage of JSON is recommended, we can alternatively just exchange plain XML data also.
######Audit Trail 
At the backend layer the logging and tracing is handled at the controller level to capture the details of the entire API calls.The application log table will store these details.
######Usability 
The user interfaces have dynamic structure that do not require complete page refreshes to complete tasks. The CHHS application has ability to efficiently add data i.e. user will have to follow minimum number of keystrokes to enter all required information. An AngularJs library is used for Ajax interactions at client layer and to build highly interactive rich UI web application.

######Maintenance and Portability 
The whole application’s business logic and data can be packaged and transferred from one platform to other like local deployment to hosted solution,hosted solution to localized deployment, from one hosted solution to the other with minimum interruption. The application is developed using standard web technologies to provide full accessibility from desktop/laptop and tablet browsers.

######Security  
As soon the user logs into the application a user specific session-Id is created which does not get time out until the subscriber gets inactive during configured duration. The handshake between client & API calls is enabled by sharing this session-Id in every subsequent API calls by that client.This secures client access in the application. Additionally, the application could be accessed over the http secure using ssl certificate for secure communication.


#####Sample Request Flow
The client request coming from web browser, Android Tablet/Smartphone hits the Business-tier which is composed of java backend services (REST - WS).  
The java backend services would fetch the data and response back relevant data to the presentation layer. Data exchange between client and/or business services happens using JSON objects.
The CHHS prototype development follows standards of Java code conventions and checkstyle guides as prescribed by AgreeYa Solutions Design team. This enforces greater consistency in the code that makes it easier to develop and maintain. Integrating these guidelines with IDE facilitates clean and consistent code pattern that reduces build time issues.



#####Prototype Screen Workflows

######Step 1 Registration 
User enters the URL, http://fosterfamilies.agreeya.net/chhs in the web browser or smartphone

######Step 2 Registration 
User lands on the Home page from where a new user can initiate activity “creation of a Family profile” by clicking on the Family Registration link.

######Step 3 Registration 
Family profile registration is a step by step process involves criteria’s to gauge Eligibility Criteria followed by Account Details, Personal Information, Family Narratives & Preferences, License & Agency Details tabs. On successfully completion of this task user can submit their Profile details which are saved into database.  Once done, user receives an acknowledgement for completing the registration process with status message “Under review till approved”.
To facilitate user during registration process we have allowed user to partially fill the form, save and exit from the unfinished application and return anytime to continue filling the form with valid logins.

######Login
An existing user ( with login credentials)  can login and logout to the application by clicking the logion/LogOut  link on the Home page 

######Manage Profile
An existing user can click on the Manage profiles link to update their approved profiles details anytime at later stages.
######Search & API Integration
An existing user can search through the Children Residential Facilities within a given zip code area. Upon entering a zip code the application communicates with the HHS API to fetch data for all the facilities (Adoption Agency, Foster Family Agency and Foster Family Sub-Agency) within the entered zipcode and displays it in a grid on the screen.

######Locate Agency
An existing user can search through the Foster Care Agencies in their nearby neighborhood. Upon entering a radius limit of users current geo location, the application communicates with the HHS API to fetch data for the facilities with selected Facility type of Foster Family Agency within the given coordinates range. The details of same are displayed in a grid on the screen.

######Private MailBox
An existing user can view a Private mailbox.  

The Prototype testing involved execution of automated Unit test cases using JUnits. Additionally, automation system test case suite is build to test the entire application. During sprint reviews the usergroup are involved to gather feedback around usability and system functionality, accepted refinements are added as new backlog work items. User feedbacks are ultimate to successful release of the application.
The build and deployment process executed using the Jenkins Continuous integration tool. Successful builds(.war files) are deployed automatically to a target server.  An automated smoke test case is executed to confirm the successful working of the application.

*********************************************************************************************************************************************************************

### Branches

##### Master Branch 
 This branch contains the Final version of the source code, UI designs-HTML, Style Checker guide, Code Conventions reference, project management artifacts. Using the instructions in the INSTALL and DEPLOYMENT SECTION this version of code base can be re-deployed to another machine.
##### Dev Branch (dev-branch) 
 This is the main development branch which includes codebase used to build and deploy to QA server or any other server for testing or demo purposes. Commits to this branch will result in execution of automated build and deployment process. This branch should be reviewed to establish time to time code merges made by the front end and back end developers.
##### Local Branch (local-branch)
This is private repository of the developers to checkin code on a day to day basis and is used to build for the development servers.


### Development Methodology 

##### Agile-SCRUM Model :
 The development of this prototype is carried out using the Agile SCRUM model. Multi disciplinary team comprising ** Product Owner , Scrum Master,Business Analyst, Web Designer,S/W Backend Developer, Frontend Developer, S/W Tester , Configuration Manager **. The whole process is carried out using Agile software development capabilities with iterartive and incremental development through sprints.Product Backlog - User Stories and Tasks are managed through the REDMINE Project Management and Bug Tracking tool.
Two dev sprints of 4 days each in duration are executed to cover the development of work items. Prior to the first Sprint a Design phase is executed to cover activitites related to UI Design, Technical Architecture Design, DB Design, Product Backlog Planning , Sprint Scheduling , Test Planning.
 
During this phase, **Joint Application Development(JAD)** sessions are executed with the usergroup to participate in the design process. Usergroups are involved to understand their need for this application so as to establish the underlying requirements of this prototype. Discussions during the requirement elicitation exercise are documented in a **Business Requirement Document (BRD)** with relevant use cases to depict the activity flow for the application users.
Review sessions are conducted with usergroup to review the accuracy of BRD and freeze the requirements with usergroup and Product Owner approvals.As part of these sessions UI designs are reviewed and confirmed for implementation. Daily Standup meetings with entire team to check progress and bottlenecks is conducted , MoM's for each of these standups is captured and shared with all attendees.
To Demonstrate that usergroups are involved in the design and development process , please check following references from the **GITHUB REPOSITORY** 

a.	**JAD sessions** conducted with usergroup to know what is needed , goals of project and create prioritized list of requirements ( Field Backlog Priority) in the Redmine tool.  
**Pl refer** files **JAD-Session0526 Usergroup.jpg** and **JAD-Session0526.jpg** in **GITHUB > demo > project-mgmt > docs.**  
Refer **CHHS Prototype Development - Story PBI with Priority.pdf** for Prioritization of user stories based in the **GITHUB > demo > project-mgmt > docs >Backlog.**  
The above information is demonstration of adherence to US Digital Services Play book play WHAT PEOPLE NEED.
					
b.	**BRD Review** - Usergroup suggested to mandatorily add Requirement to Partially Fill the Family Registration Form with SAVE AND EXIT option . The functionality was added to the BRD.  

Refer Sec 3.1 for addition of this requirement in **GITHUB > demo > project-mgmt > docs > BRD BRD_CHHS-Prototype V1.0.doc**.  

This also demonstrates adherence to US Digital Services Play book play SIMPLE & INTUITIVE where user can save details during Family Registration partially and exit to return later to complete the registration process. Also the UI design shows screen navigation using breadcrumbs on every screen informing users of where they are in the website . Registration Process shows to the user how many steps of total steps are done thus making UI design simple and intuitive for the users.

c.	Sprint 1 Review - UI Refinements suggested by usegroup.A new Story created and added to 
Backlog in the RedMine tool.  
Refer Sprint Review document  at **GITHUB > demo > project-mgmt > docs > SprintReviews** mentioning usergroup feedback for UI Design refinements.  
Pl refer **GITHUB > demo > project-mgmt > docs > Bocklog >CHHS Prototype Development - Story PBI with Priority.pdf** for REDMINE Backlog ScreenShot with Story # 167 "UI Design- Refinements" added in the backlog . 
This demonstrates that approach followed taking feedback from usergroup during Sprint Reviews leading to subsequent work item in the backlog.  
Additionally, this supports performing usability tests with usergroup to ascertain effective and easy use of applications.  

d.	The website also has a mechanism for allowing users to Report any Technical issues they encounter during their interaction on the website. This demonstrates adherence to US Digital Services Play book play **DEFAULT TO OPEN** .
Pl refer footer on the website on each page with a link **"REPORT TECH ISSUES"**

e.	Use of Agile scrum model for development process in sprints demonstrates adherence to 
Agile and iterative development process.Collaboratively working with usergroup to gather feedback , Task boarding , review artifacts , code reviews , sprint reviews , MoM's ,mockups demonstrates adherence to US Digital Services Play book play USE OF **AGILE & ITERATIVE PROCESS**.  
Refer artifacts in the **GITHUB > demo > project-mgmt > docs** folder.

f.	Setup a multidisciplinary team with a identified leader as **Product Owner** and single point of authority for the team for approvals and decision, direction for the team .  
Refer the team members and roles of the scrum team at **GITHUB > demo > project-mgmt > docs > Backlog >Backlog Details.jpg**
                                 
### Development Technology Stack 

#####  Front End Technologies
				a. Responsive UI Designs - Bootstrap-HTML5 Ver 3.3.6  OPEN SOURCE 
				b. UI Development - AngularJS Ver 1.5	OPEN SOURCE
#####  BackEnd Technologies & Tools
				a. Java JDK ver 1.8 OPEN SOURCE
				b. Application Server - Tomacat Ver 7.0.69 OPEN SOURCE
				c. Spring Framework IOC Release 4.2.6  OPEN SOURCE
				d. Web Services - Jersey RESTful Web Services framework REST JAX-RS Jersey Ver 1.19 OPEN SOURCE
				e. ORM Tool - Hibernate Ver 5.0.3 FINAL OPEN SOURCE
				f. Build Tool - Maven Ver.3 OPEN SOURCE
				g. JUnits Ver. 4.12 OPEN SOURCE
				h. Continous Integration - Build Automation & Deployment - Jenkins Ver 1.65 OPEN SOURCE
				i. IDE - Eclipse MARS OPEN SOURCE
#####  Platform
				a. OS - Ubuntu Ver.14.04.1 OPEN SOURCE
#####  Database
				a. MYSQL Ver.5.7 OPEN SOURCE
#####  Source Control
				a. GITHUB  https://github.com/agreeya-demorepository/demo.git

#####  Product Backlog & Defect Management Tool
				a. REDMINE Ver 3.2.3  OPEN SOURCE


### Responsive Design 
 UI is designed using the Bootstrap front end framework. UI designs are responsive in nature and hence compatible with multiple mobile devices and platforms like smartphones, tablets on  Android, iOS. The UI designs are created in a consultative and interactive approach with usergroup with due consideration to aspects like ease of use,intuitiveness,application workflow etc.  
This demonstrates the screens to be accessible from multiple devices.


### Style Guide & Coding Conventions
As part of coding conventions and stylechecks we have used a set of standard guidelines so as to have consistency in coding approach during the course of development of this prototype.
File **stylecheck.xml** provides the list of stylechecks used and PMD integrated with IDE Eclipse is used for following coding conventions and standards for development of Java backend.This approach reduces redundancy and extends code reviews beyond semantics validations . 
This demonstrates adherence to the use of a Style Guide during the prototype development process.

### Unit & Automation Testing
JUnit based units test cases are developed as part of the code .These are executed by the developer as part of the development activity . JUnits are exeucted automatically during the Build process also.
There are two ways in which System Testing for the application is done. Manual test case execution and automation test case suite is created to run the test case automatically using a script. The results of the automation test cases are published in a file.

Pl refer the GITHUB folder **GITHUB > demo > project-mgmt > design > Testing> Automation Test**
Pl refer GITHUB folder for Automation test cases scripts at **GITHUB > demo > project-mgmt > design > Testing> Automation Test**
This demonstrates the adherence to the US Digital Services playbook play **AUTOMATION TESTING AND DEPLOYMENTS**


### Configuration Management
GITHUB is the source code and documents repository used during the development of the CHHS prototype. It automatically maintains version history of each and every document or file that is checked in by the collaborators. The source code is structured in 3 different branches local-branch, dev-branch, master.
Team members identified as collaborators can only check in code or a document to this repository.
Versions of deployable .war files are kept at GITHUB repository **GITHUB > demo > Builds** with Build logs.

### Continous Integration & Deployment
During the course of development of this prototype , we have followed automated build and deployment process using the Jenkins Continous Integration tool.
Developers work in their local branch(local-branch) on GITHUB and whenever they are done with their workitems, they can commit their changes to Dev branch ( Dev-branch). Jenkins CI tool monitors the changes done on the GITHUB repository and perfoms following steps in a sequence			
1.	Validates the Checkstyle per checkstyle guide. Checktyle.xml contains some of the 
checkstyles we used during development of backend java code  
2.    Builds and runs the Junit based Unit and Integrations test Cases  
3.   On successfull build, assigns a build ID to the version of the code that is compiled  
4.   A notification for successful build is sent to the team.  
5.  Automatically deploys the application ( .war file) in a different server using the Deploy to Container plugin of the Jenkins tool  
6. If the Build fails due to violation of checkstyle, Unit Test failure , compilation error due to some dependencies a notification of build failure is sent to the team , team needs to fix the the issue and initiate rebuild.  
The build process is internally managed by MAVEN with its entire configuration done in the **POM.XML** file.
This demonstrates during the prototype development process the use of a setup that provides continuous integration framework
  
### User Acceptance Testing (UAT)
Post the system test , a User Acceptance Test with usergroup was conducted to validate usability and system functionality. 
UAT defects reported were logged in the REDMINE tool. Upon successful fix and re-test the bugs were marked as closed.
UAT Test Cases and results were published and available at **GITHUB > demo > project-mgmt > design > Testing> UAT**

### INSTALL and DEPLOYMENT INSTRUCTIONS

######Prerequisite  
1. Java Ver 1.8 is installed at the server where the application is being deployed and the server is installed with web server Tomcat 7.0.69 at <TOMCAT_HOME> directory
2. MYSQL Ver 5.6.30+ is installed on the server.
3. Execute the Database creation script **GITHUB > demo > project-mgmt > design > database >fosterfamilies_DBScript.sql**

######Steps to Deploy Application :

1. Stop the Tomcat server if already running on the target server
	- Use ps –ef | grep tomcat to check if tomcat is running

2. Copy chhs.war file in the MASTER Branch from the location **GITHUB > demo > builds**  to **<TOMCAT_HOME>/webapps/** directory

3. Start Tomcat server
	- Goto **<TOMCAT_HOME>/bin** directory and run startup.bat or startup.sh (for linux)
	- Check logs using (tail –f “<TOMCAT_HOME>/logs/catalina.out”  if you get DB error:
		- Stop the Tomcat server
		- Goto <TOMCAT_HOME>/webapps/chhs/WEB-INF/classes
		- Edit chhs_config.properties file and update db,url,db.user,db.password and save file
		- Restart the Tomcat server using Goto <TOMCAT_HOME>/bin director and use startup.bat or startup.sh (for linux)

4. Please update the URL of the target server in the file for UI **config.js**  located at **GITHUB > demo > src > main > webapp > app**.  
Update Variable Name **apiHostUrl** with target server URL. eg **http://fosterfamilies.agreeya.net/**
 
###APPENDIX


| Folder Name                              | Description                                                              |
| -----------------------------------------| -------------------------------------------------------------------------|
| Demo Repository                          | GITHUB Demo Repository used for the Prototype Development                |
| demo/html/                               | Contains the final set of HTML files with relevant images, JS , CSS files|
|                                          | Also contains zip files of earlier versions of HTML                      |
| demo/project-mgmt/docs/                  | Contains the Requirements Elicitation docs like JAD sessions images,     |
|                                          | MoM's, Task Boarding, Project Plan, TDS, Project Init. Checklist         |
| demo/project-mgmt/docs/Backlog           | Contains the Product Backlog User Stories , Tasks snapshots and pdf      |                                
|                                          | files of the Redmine Tool used for the Backlog Mangement                 |
| demo/project-mgmt/docs/Daily-Scrum       | Contains Daily Team Standup Meeting Minutes                              |                                
| demo/project-mgmt/docs/Sprint Reviews/   | Contains Sprint Reviews conducted with usergroups                        |
| demo/project-mgmt/design/ui/             | Contains mockups, screen designs,Click-Through used during               |
|                                          | Requirement elicitation                                                  |
| demo/project-mgmt/design/database/       | Contains DB Scripts, ER Dig                                              |
| demo/project-mgmt/design/Testing/        | Contains Manual Test Cases,Automation Test scripts,Test results,Test Plan|
|                                          | UAT Test Cases , UAT feedback                                            |
| demo/builds/                             | Contains Build scripts, .war file, Builds Console logs                   |
| demo/logs/                               | Contains Application logs etc.                                           |
| demo/src/main/webapp/                    | Contains Application Source code for Java,UI, web.xml etc.               |