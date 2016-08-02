# USDS Playbook Checklist for CHHS Foster Services Prototype

We adhered to the standards set forth in the [U.S. Digital Services Playbook](https://playbook.cio.gov/).

## Play 1
### Understand what people need

From the beginning, we included user interviews and feedback in creating our prototype. Early on, we interviewed biological parents of foster youth, foster parents, foster care caseworkers, and foster youth to better understand their needs and current pain points. We also shadowed many caseworkers in San Francisco county to better understand their workflow and needs. We have been working on a pilot with San Francisco’s Foster Care Department, so we have deep domain expertise when it comes to understanding the needs of foster care stakeholders. [See notes here.](Product%20documentation/User%20interviews)

We learned many things from our user research that we incorporated into our design, including:
* A mobile-friendly design
* Accessibility in Spanish
* Easy access to the foster care services search
* Email notifications for messages
* A landing page that social workers could easily edit

After hearing from our users, we came up with a list of [user stories](Product%20documentation/User%20stories/User%20Stories.pdf) and prioritized the main features necessary for this prototype. We also created a [flow diagram](Product%20documentation/User%20flow/CHHS%20Prototype%20-%20User%20Flow.pdf) of different user journeys across the app. After that, we developed some [early mock-ups](Product%20documentation/Mockups) to get quick validation and user feedback. From there, we refined our designs, and created a [clickable prototype](https://invis.io/2N7JV7I63) using InVision to make sure the user journey worked as intended. We tested this prototype with real users. From there, we started building a Minimum Viable Product, and tested out our actual app on several users before making more iterations on our app to make it very user-friendly.

## Play 2
### Address the whole experience, from start to finish

Through our [user interviews](Product%20documentation/User%20interviews), we understood people’s current pain points with interfacing with the foster care system. We also got a deeper understanding of how people accomplish their tasks online and offline now (see user interviews for more details). We understood that people would primarily use this app to locate foster care services nearby and to message their case worker. However, since this app would not be used very frequently, we decided to create a webapp instead to minimize any friction that would come from having to download a mobile app.

One of the big problems we encountered through user research is that biological parent of foster youth often have a hard time keeping track of who their social worker is and how to contact them. As a result, in addition to the online messaging feature we built, we also provide the social worker’s name and phone number to the biological parent, so if it’s easier for them to call directly offline, they are able to do so.

## Play 3
### Make it simple and intuitive

We relied on the [U.S. Web Design Standards](https://standards.usa.gov/) to guide our user interface design choices, so that the site would feel cohesive with the overall government web guidelines and feel trustworthy.

We focused on creating a very simple design, such that users would intuitively know how to accomplish what they wanted to do, without any need for tool tips or reading instructions for what to do. Our clear navigational structure and headers help orient the user, no matter where they are in our application. If users do get stuck, we have contact information in our footer for them to easily reach out for help.

We focused on making our application accessible by making it usable on laptops, tablets, mobile devices, and across many browsers. We also created a Spanish version to reach Spanish-speaking biological parents of foster youth. Our website copy is friendly and easy to understand -- we tried to eliminate jargon entirely and use familiar language instead, so that the majority of people would understand what the site was about.

Once an account has been created, users can save their profile information and return to finish it later. We also provide a clear way to sign in and log out of the service.

## Play 4
### Build the service using agile and iterative practices

We ran usability tests with real people at each iteration for feedback. We compiled and prioritized the feedback to create new features and discover bugs.

We developed an MVP within days of starting the project.

Our development team utilized best practices for agile development, including:
* Keeping the delivery team small and focused, and giving the entire project team access to the issue tracker (Asana) and version control system (GitHub)
* Using launch meetings to get us started and daily standups to communicate tasks and blockers.
* Using Slack chat for asynchronous communication about the product daily standups, features and bug backlog.
* Releasing features and improvements quickly, sometimes many in one day
* Reviewing code amongst our developers using GitHub pull requests.
* Using GitHub for version control.
* Tracking features and bug backlogs in Asana, as well as their relative priority

## Play 5
### Structure budgets and contracts to support delivery 

While this is not applicable for this prototype, we are experienced in producing contracts for agencies that clearly lay out many types of deliverables, milestones, performance metrics, and payment structures.

## Play 6
### Assign one leader and hold that person accountable

We assigned one product owner, Felicia Curcuru, to be responsible for the quality of the prototype. Felicia is the CEO of Binti and Head of Product, and has the authority to assign tasks and make decisions about features and technical implementation details. Throughout the project, Felicia prioritized different product features and scoped the prototype.

## Play 7
### Bring in experienced teams

Our team is an extremely talented group of multidisciplinary people with experience building popular, high-traffic digital services, including companies such as Google, YouTube, Facebook, Couchsurfing.com, and FundersClub. We have a high level of experience serving clients, both on technical projects as well as business consulting (the CEO was formerly a senior McKinsey consultant who managed projects for businesses, non-profits and governments in the US, Europe and Asia). We have experience designing mobile and web applications, using automated testing frameworks, using DevOps techniques and building apps with a high level of security (including HIPAA-compliance).

On our team, we have members with experience in product management, front end web development, backend web development, DevOps, visual design, user researcher, technical architecture, content writing and more. 

We give back to the community in both the human services community and technical community. Members of our team have founded a non-profit teaching web development. We also have contributed to popular open source software including Ruby on Rails and Bundler. The two co-founders are also Court Appointed Special Advocates for foster youth in San Francisco.

## Play 8
### Choose a modern technology stack

We choose technologies that are widely used and open source. A new team member can get up to speed and start developing on their first day of work. In fact, as part of our interview process, we require candidates to do a technical screen with us which requires them to contribute to our actual codebase within hours.

**1. [Ruby on Rails](http://rubyonrails.org/)**

The version of Ruby we use shipped in April 2016 and the version of Rails we use shipped March 2016. [Ruby](https://www.ruby-lang.org/en/) is a 21-year-old and actively-developed high level language. Rails is a 11-year-old opinionated, powerful, and always evolving web software framework built on Ruby. 

**2. [PostgreSQL](https://www.postgresql.org/)**

The version PostgreSQL we use shipped in January 2016. PostgreSQL is a 19-year-old, yet constantly improving SQL implementation that is superbly robust as well as featureful. It is the [officially-supported SQL service of Cloud.gov](https://docs.cloud.gov/apps/databases/), the official Infrastructure-as-a-Service platform of 18F, which first launched in October 2015. 

**3. [jQuery](https://jquery.com/)**

The version of jQuery we use shipped in January 2016. jQuery is 11 years old and remains the most popular JavaScript library in the world. [jQuery is the official cross-platform framework of the US Web Design Standards.](https://github.com/18F/web-design-standards#download)

**4. [soda-ruby](https://github.com/socrata/soda-ruby)**
    
The version we use shipped February 2016. soda-ruby is the officially-supported Ruby client of the Socrata API service used by CA HHS. It obviates the need for custom code and allows us to easily track upstream improvements in the Socrata API. It was first shipped in November 2012.

**5. [U.S. Web Design Standards](https://standards.usa.gov/)**

U.S. Web Design Standards provide a base level of look, feel, and functionality, with an eye toward a high level of usability and accessibility. It is the preferred front-end framework of 18F. The version we used is from June 2016.

## Play 9
### Deploy in a flexible hosting environment

We are hosting our application on Heroku, which allows us to spin up and down application instances as needed to meet app traffic requirements, through their web interface with a single click or with Heroku’s flexible API. Heroku offers an Enterprise option which delivers High Availability through redundancy across geographies and infrastructure as well as 30-minute technical support.

## Play 10
### Automate testing and deployments

We create automated tests that verify user-facing functionality as we code, and we run tests automatically as part of the build process. Deploying a new feature takes minutes and we aim to deploy frequently. 

## Play 11
### Manage security and privacy through reusable processes

Our app is delivered over HTTPS. We use an industry standard open source authentication library (Devise) which is responsible for safely storing users’ password hashes. All of our dependencies are managed through Bundler so we can deploy upstream security changes with minimal effort.

## Play 12
### Use data to drive decisions

Post-prototype, we like to use static analysis tools, code coverage tools, and continuous integration build data to quantify the quality of our codebase, in order to focus on improving our code where it counts the most and enforce high standards across the team.

We also use Heroku’s built-in monitoring as well as Papertrail to measure responsiveness, throughput, downtime, and errors, informing where to invest resources in our production setup.

Post-prototype, we instrument user analytics like Mixpanel to measure our users’ behavior and look for trends suggesting how we can improve our product.

## Play 13
### Default to open

As stakeholders in how our government operates, we are proud to embrace transparency and deliver our software in the open. By leveraging the battle-tested and featureful open source tools that we do, we are truly standing on the shoulder of giants. We look forward to interacting with the community and responding to feature requests, bug reports, and pull requests.
