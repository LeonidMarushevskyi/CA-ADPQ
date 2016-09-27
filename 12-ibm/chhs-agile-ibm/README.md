# Readme for CCHS RFI #75001

> The Git repository: <http://github.com/ibmbluemixgarage/chhs-agile-ibm>.

> The wiki: <https://github.com/ibmbluemixgarage/chhs-agile-ibm/wiki>.

> The deployed prototype: <http://chhs-agile-ibm.mybluemix.net/#/>. 

IBM used our Bluemix Garage agile development methodology to build this prototype in accordance with the guidelines of the RFI. See: <https://www.ibm.com/devops/method>. 

1. **_Assigned one leader and gave that person authority and responsibility and held that person
accountable for the quality of the prototype submitted._**

	IBM assigned a single person as the product manager for this prototype development effort.

2. **_Assembled a multidisciplinary and collaborative team that includes, at a minimum, five of the labor categories as identified in Attachment C._**

	Our team consisted of 6 core members: a product manager, a technical architect, a front-end developer, a back-end developer, and two interaction designers.

3. **_Understood what people needed by including people in the prototype development and design process._**

	IBM used our Health and Human Services Subject Matter Experts to act as our users for this effort.

4. **_Used at least three “human-centered design” techniques or tools._**

	We developed a use case and then employed Mural (<https://mural.co/> - for personas, empathy maps) and Moqup (<https://moqups.com/>- for wireframes) to guide design and development activities.

5. **_Created or used a design style guide and/or a pattern library._**

	We used Google's Materials design language (<https://design.google.com/resources/>) as our style guide. 

6. **_Performed usability tests with people._**
	
	We performed usability tests with users (SMEs) to refine the design and functionality of the prototype.

7. **_Used an iterative approach, where feedback informed subsequent work or versions of the prototype._**

	The prototype was developed using an XP (eXtreme Programming) based Agile methodology. Specifically, the prototype used the following:
  *	One week iterations.
  *	Small user stories pulled from continuous backlog.
  *	Playbacks with product manager and designer.
  *	Pair programming.
  *	Test-driven development.
  *	Continuous integration/continuous delivery.

8. **_Created a prototype that works on multiple devices, and presents a responsive design._**

	The prototype was developed using responsive design techniques and is capable of working on desktop, laptop, and mobile browsers. It was developed using AngularJS and Angular Material which provides a set of reusable, well-tested, and accessible UI components based on Google’s Material Design Specification (a specification for a unified system of visual, motion, and interaction design that adapts across different devices and different screen sizes).
	
9. **_Used at least five modern and open-source technologies, regardless of architectural layer._**

	The prototype is developed using Node.js, AngularJS, Angular Material, HTML5, CSS3, Github, and Jasmine open-source technologies.

10. **_Deployed the prototype on an IaaS or PaaS provider, and indicated which provider they used._**

	The prototype is deployed on IBM’s Bluemix Platform, an open-standards, cloud-based platform for building, testing, running, and managing applications. On Bluemix, mobile and web developers can easily assemble existing services from IBM or third-party providers that run on 3 key open compute technologies: Cloud Foundry, Docker, and OpenStack. Bluemix facilitates and accelerates the provisioning of cloud infrastructure (storage, network, clustering, virtualization, etc.) so development teams can focus of what really matters: delivering value to their organizations.

11. **_Developed automated unit tests for their code._**

	We used the Jasmine behavior-driven development framework with the Karma test runner (for automated unit testing).

12. **_Setup or used a continuous integration system to automate the running of tests and continuously deployed their code to their IaaS or PaaS provider._**

	IBM Continuous Delivery Pipeline for Bluemix automates builds and deployment to support continuous integration and delivery. The DevOps pipeline is set up such that code changes delivered to the Github repositories are automatically built, tested and deployed to the Bluemix Cloud Foundry runtime. See: <https://console.ng.bluemix.net/docs/services/DeliveryPipeline/index.html>

13. **_Setup or used configuration management._**

	The Bluemix PaaS uses the configuration management system built into Cloud Foundry that automatically provide and provision runtime containers and middleware services such as the Node.js servers and modules required by the prototype application. Node.js packages required by the application are defined in package.json and deployment properties such as application name, route, memory requirements, etc. are defined in the manifest.yml file.

14. **_Setup or used continuous monitoring._**

	The Bluemix platform continuously monitors the health of the application and restarts it if required. It provides tools to view/stream application logs and provides services such as Monitoring and Analytics and Alert Notification, Auto-scaling for handling spikes in traffic, and the use of third party services for external monitoring. These have not been set up for the prototype but are recommended for production deployments. 
See: <https://www.ibm.com/devops/method/content/manage/practice_automated_monitoring/>

15. **_Deployed their software in a container (i.e., utilized operating-system-level virtualization)._**

	Bluemix automatically deploys applications by creating a “droplet” (Cloud Foundry unit of execution) for the application, selecting a Droplet Execution Agent (DEA) to run the application and starting the application on the selected DEA. The DEA provides a container environment for the application to run in isolation from other applications running on the platform.  Cloud Foundry run-times provide an abstraction layer such that developers do not have to worry about infrastructure, operating system, virtualization, and application servers. Developers can focus on building their applications. 

16. **_Provided sufficient documentation to install and run their prototype on another machine._**

	To install and run the prototype on another machine:
  1.	Download node.js from <https://nodejs.org/>. Select "v4.4.5 LTS".
  2.	Update npm if required (on Mac run “sudo npm install npm -g”).
  3.	Download the application from Github ("git clone https://github.com/ibmbluemixgarage/chhs-agile-ibm.git").
  4.	Open a terminal window and change to the root folder of the application.
  5.	Run “npm install” in the root folder of the application.
  6.	Run “npm start”.
  7.	Open a browser. Use URL displayed upon application start up.

17. **_Prototype and underlying platforms used to create and run the prototype are openly licensed and free of charge._**

	All the technologies used to develop the prototype application are open-source technologies. While the prototype application was developed on IBM’s Bluemix platform, the app can be deployed on non-Bluemix environments including desktops/laptops, VMs and Docker containers in other cloud environments. The app is freely available under the Apache v2.0 license. 
