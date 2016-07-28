Feature: Login/Logout
  As a user, I want to sign into the site, so I can use additional site functionality

@prod
	Scenario: User can log in
    Given I am on the "login" page
    And I am logged out
    When I log in with username "swansoncp5@gmail.com" and password "Test123456"
    Then I should be on the "dashboard" page
@prod
  Scenario: User can log out
    Given I am on the "login" page
    And I am logged in with username "swansoncp5@gmail.com" and password "Test123456"
    When I log out
    Then I should see the class "site-header__link-login"
