<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="page-home container">

  <div class="site-banner"></div>

  <sec:authorize access="isAnonymous()">
    <div class="site-account-cta"></div>
  </sec:authorize>

  <section class="page-home__summary row">
    <div class="page-home__summary-section col-md-4">
      <div class="page-home__summary-content">
        <img class="page_home_summary-img" src="app/assets/home/locator.png">
        <h4 class="page-home__summary-header"><a href="#/dashboard">Foster Facility Finder</a></h4>
        <p>
          The Foster Facility Locator tool allows foster parents to locate foster family agency locations in the area with a simple zip-code search.
        </p>
        <p>
          To use this free service, you must set up a profile. It's easy, click below to quickly create an account, or if you already have an account, simply sign in.
        </p>
      </div>
    </div>
    <div class="page-home__summary-section col-md-4">
      <div class="page-home__summary-content">
        <img class="page_home_summary-img" src="app/assets/home/message_caseworkers.png">
        <h4 class="page-home__summary-header"><a href="#/dashboard">Message Caseworkers</a></h4>
        <p>
          Our free service allows foster parents to message caseworkers with questions about foster facilities and other matters.
        </p>
        <p>
          To access messaging, you must set up a profile. It's easy, click below to quickly create an account, or if you already have an account, simply sign in.
        </p>
      </div>
    </div>
    <div class="page-home__summary-section col-md-4">
      <div class="page-home__summary-content">
        <img class="page_home_summary-img" src="app/assets/home/contact.png">
        <h4 class="page-home__summary-header"><a href="#/support">About/Contact CWDS</a></h4>
        <p>
          Child Welfare Digital Services provides services to foster parents in the state of California.
        </p>
        <div>
          <strong>CWDS</strong>
          <div>Address, City, ST 94445</div>
          <div>(800) 555-1212</div>
          <div>email@2email.com</div>
        </div>
      </div>
    </div>
  </section>
</div>
