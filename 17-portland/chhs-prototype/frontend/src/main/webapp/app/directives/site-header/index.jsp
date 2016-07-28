<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="site-header__navigation navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="site-header__navbar-header navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#topNav">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="site-header__logo-link navbar-brand" href="#/home">
        <img class="site-header__logo" alt="CWDS" src="app/assets/site-header/logo.png">
      </a>
    </div>
    <div class="site-header__collapse collapse navbar-collapse" id="topNav">
      <ul class="site_header__links nav navbar-nav navbar-right">
        <sec:authorize access="isAnonymous()">
          <li class="site-header__link-item"><a href="#/register" class="site-header__link site-header__link-register">Create Account</a></li>
          <li class="site-header__link-item"><a href="#/login" class="site-header__link site-header__link-login">Sign In</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
        <li class="site-header__link-item"><a href="#/dashboard" class="site-header__link site-header__link-foster">Foster Home Finder</a></li>
        <li class="site-header__link-item"><a href="#/dashboard" class="site-header__link site-header__messages">My Messages <span
          class="site-header__message-count badge" ng-if="messageCount != null">{{ messageCount }}</span></a></li>


        <li class="site-header__link-item dropdown">
          <a href="" class="site-header__link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
             aria-expanded="false">{{ user }} <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#/account" class="site-header__link site-header__dropdown-link site_header__link-account">My Account</a></li>
            <li class=""><a href="/logout" class="site-header__link site-header__dropdown-link site_header__link-logout">Logout</a></li>
          </ul>
        </li>
        </sec:authorize>
        <li class="site-header__link-item"><a href="#/support" class="site-header__link site_header__link-help">Help</a></li>
      </ul>
    </div>
  </div>
</nav>
