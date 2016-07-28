Feature: Terms and Conditions
  As a registrant, I want to view the terms and conditions of the site

@prod
  Scenario: T's and C's appear in modal when registering
    Given I am logged out
    And I am on the "register" page
    When I click "register__terms-link" class in the "register" class
    And I delay the test by "500" milliseconds
    Then I should see the class "terms-conditions .modal-dialog"
