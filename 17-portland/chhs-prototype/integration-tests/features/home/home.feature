Feature: Homepage
	As a logged in and logged out user
	I want the navigation tools and home page elements to be appropriate to my status

@prod
	Scenario: Verifying Logged Out Home Page
		Given I am on the "home" page
		And I am logged out
		Then I should see the class "site-header__link-login"
		And I should see the class "site-header__link-register"
		And I should see the class "site-account-cta"
		And the class "site-header__link-foster" should not exist
		And the class "site-header__messages" should not exist
		And the class "site-header__link-item.dropdown" should not exist
		And I should see the class "site_header__link-help"

@prod
	Scenario: Verifying Logged In Home Page
		Given I am logged in with username "swansoncp5@gmail.com" and password "Test123456"
		And I click "site-header__logo" class in the "site-header__navbar-header" class
		Then the class "site-header__link-login" should not exist
		And the class "site-header__link-register" should not exist
		And the class "site-account-cta" should not exist
		And I should see the class "site-header__link-foster"
		And I should see the class "site-header__messages"
		And I should see the class "site-header__link-item.dropdown"
		And I should see the class "site_header__link-help"
