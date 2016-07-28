<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
  <div class="page-dashboard container">
    <h4 class="page-dashboard__header">My Dashboard</h4>

    <div class="page-dashboard__row row">
      <div class="col-sm-6">
        <div class="foster-home-locator"></div>
      </div>
      <div class="page-dashboard__separator"></div>
      <div class="col-sm-6">
        <div class="case-worker-messenger"></div>
      </div>
    </div>
  </div>
</sec:authorize>
