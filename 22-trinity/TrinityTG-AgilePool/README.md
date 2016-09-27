# TrinityTG-AgilePool

Trinity Technology Group, Inc. – Response to RFI-75001

https://github.com/TrinityTG/TrinityTG-AgilePool

Approach Description

At the beginning of this effort, Trinity Technology Group, Inc. (TrinityTG) assigned Jeremy Dean to the Prototype Project Lead/Product Manager role. He was responsible for overseeing all facets of this Response, including assembling the team, managing the project’s budget, and ensuring effective delivery of the response. The following outlines the approach Jeremy Dean and the TrinityTG Team employed to accomplish the requirements – and the intent of – the RFI.

The TrinityTG Team was assembled shortly after the RFI’s release. One of the first steps that we undertook was creating an hour’s budget (included) for the team since these resources were shared with other projects. The team members were assigned their roles on the project (outlined below) and began reviewing the RFI. Before starting the actual prototyping tasks, the team met with our Agile Coach to determine the processes the team will use to execute this project. A schedule for daily standups was established and an Agile project tracking tool was selected. 

An initial task involved the Business Analysts, Interaction Designers, and Content Strategist working together to create a plan to identify the Human Centered Design concepts. Based upon our initial understanding of the RFI, we reviewed the Q&A webinar and identified users of the system and (1) worked through a usability questionnaire, (2) solicited user feedback with an online survey, and (3) collated this information into requirements and their related tasks. 

Once these requirements were established and baselined, the Technical Architect worked with the Development Team to begin selecting technologies appropriate to meet the requirements. Along with selecting the technologies, the Design and Development Team began working through the prototype’s architectural design. With this design, along with the requirements, tasks, and technologies necessary, the team worked together to create a Sprint Schedule. In parallel, the DevOps Engineer worked to configure our Microsoft Azure Development, Staging, Testing, and Production environments. The Security Engineer worked to make sure our teams had the proper access to their hosting environment(s).

As the team prepared to begin the Sprint, the coding, code repository, and configuration management principles were put into place. The Development Team was given access – and that access was validated – to ensure code was maintained and the team could easily collaborate. As the Development Team completed their assigned tasks, the code was checked into the repository, peer reviewed, and then marked ready for testing. The initial Sprint’s goal was to create the Minimum Viable Product (MVP) to allow user system testing. When the first Sprint concluded, the MVP was ready for testing.

Our User Experience Team then took the MVP to the users and gave them an opportunity to test the system in its current state. The users were surveyed again to determine what worked, what didn't work, and what functionality might be missing. Their feedback was recorded as new requirements and tasks, and then incorporated into the system design. The tasks were added to the Sprint Schedule for the Development Team. 

At this point, the Development Team continued to execute the tasks assigned to each of the Sprints. The goals of the subsequent Sprints were to (1) create a fully testable version of the prototype and (2) add the visual styling of the application. Our Writer/Content Designer, partnered with our Visual Designer, reviewed the application, validated that the agreed upon style elements were in place, and ensured the look and feel worked across the application.

When the Development Team completed the next Sprint, the prototype was ready for the Testing Team. The first wave of testing was our automated testing via Visual Studio. Once completed, our Analyst worked to validate that the requirements had been met by tracing the requirements initially gathered, as well as the requirements subsequently found, to the functionality. This testing was also completed on multiple devices and operating systems for these devices. Issues that the Testing Team discovered were logged in the issue log, relayed to Development Team, and scheduled into a Sprint Cycle for resolution. When the issues were resolved, the Development Team redeployed the application for testing validation.

Once the Testing Team completed their testing cycle successfully, the prototype was turned over to the users for User Acceptance Testing (UAT). The Testing Team wanted feedback on how the application worked, if the agreed upon changes had been made, and if the Testing Team had missed any issues or found any bugs. At this point, the User Team signed off that the application met their expectations and passed UAT.

After passing UAT, the application was considered ready for deployment by the Delivery Manager. The Development Team locked down the code branch in the code repository and versioned it for a production deployment.
For more information please go to the GitHub link above and view the Documentation folder.

Assumptions:

1) One Case Worker is managing all of the case load for the Foster Families utilizing this web site.
2) Case Worker can only send and receive email and have no profile page for the purpose of this prototype.

Open Source Technology Used:

IDE: Microsoft Code and Notepad++
Codebase: C#.NET 4.6.1, Asp.NET, Bootstrap, jQuery, RivetsJS

Other Technology:

Hosting: Microsoft Azure Cloud Services
Database: Microsoft Azure SQL (PaaS) Database
Automated Testing Tool: Visual Studio 2015
Project Management Collaboration Tool: Trello
Code Repository: Team Foundation Server, GitHub

Team:

Product Manager: Jeremy Dean

Technical Architect: Arman Gharib

Delivery Manager: Chris Worley

Front End Web Developer: Steve Yerkes

Back End Web Developer: Hitesh Wadhwani

Business Analyst: Shivani Singh

Content Designer: Karina Ritchie

Usability: Sean Mahon

Visual Designer: Alyssa Rasmussen

DevOps: Armin Nasufovic

Security: Tony Murdock

Agile Coach: Hiren Vashi
