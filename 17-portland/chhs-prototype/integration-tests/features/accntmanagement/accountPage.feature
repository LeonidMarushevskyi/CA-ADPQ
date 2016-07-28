Feature: My Account
	As a user, I want to be able to update my profile so I can keep it up to date

@prod
	Scenario: Page Elements Appear
    Given I am logged in with username "swansoncp5@gmail.com" and password "Test123456"
    When I click "dropdown-toggle" class in the "dropdown" class
    And I click "site_header__link-account" class in the "dropdown-menu" class
    Then I should be on the "account" page
    And the model "update.account.email" should be disabled
    And the value of model "update.account.email" should be "swansoncp5@gmail.com"
    And the value of model "update.account.firstName" should be "Carl"
    And the value of model "update.account.lastName" should be "Swanson"
    And the value of model "update.account.address.street" should be "19 Crescent St"
    And the value of model "update.account.address.city" should be "Portland"
    And the value of model "update.account.address.state" should be "ME"
    And the value of model "update.account.address.postalCode" should be "04102"
    And I should see the class "form__required-asterisk"
    And I should see the class "form__btn-cancel"
    And I should see the class "form__btn-create"

@prod
	Scenario: Cancel Editing Account Info
    Given I am logged in with username "swansoncp5@gmail.com" and password "Test123456"
    When I click "dropdown-toggle" class in the "dropdown" class
    And I click "site_header__link-account" class in the "dropdown-menu" class
    Then I should be on the "account" page
    When I clear the field "update.account.firstName"
    And I enter "EDITED" in the textarea "update.account.firstName"
    And I clear the field "update.account.lastName"
    And I enter "EDITED" in the textarea "update.account.lastName"
    And I clear the field "update.account.address.street"
    And I enter "EDITED" in the textarea "update.account.address.street"
    And I clear the field "update.account.address.city"
    And I enter "EDITED" in the textarea "update.account.address.city"
    And I click on "CA" in the dropdown "update.account.address.state"
    And I clear the field "update.account.address.postalCode"
    And I enter "EDITED" in the textarea "update.account.address.postalCode"
    And I click "form__btn-cancel" class in the "manage-account" class
    Then I should be on the "account" page
    And the value of model "update.account.firstName" should be "Carl"
    And the value of model "update.account.lastName" should be "Swanson"
    And the value of model "update.account.address.street" should be "19 Crescent St"
    And the value of model "update.account.address.city" should be "Portland"
    And the value of model "update.account.address.state" should be "ME"
    And the value of model "update.account.address.postalCode" should be "04102"

@prod
  Scenario: Submitting Editing Account Info
    Given I am logged in with username "swansoncp5@gmail.com" and password "Test123456"
    When I click "dropdown-toggle" class in the "dropdown" class
    And I click "site_header__link-account" class in the "dropdown-menu" class
    Then I should be on the "account" page
    When I clear the field "update.account.firstName"
    And I enter "EDITED" in the textarea "update.account.firstName"
    And I clear the field "update.account.lastName"
    And I enter "EDITED" in the textarea "update.account.lastName"
    And I clear the field "update.account.address.street"
    And I enter "EDITED" in the textarea "update.account.address.street"
    And I clear the field "update.account.address.city"
    And I enter "EDITED" in the textarea "update.account.address.city"
    And I click on "CA" in the dropdown "update.account.address.state"
    And I clear the field "update.account.address.postalCode"
    And I enter "EDITED" in the textarea "update.account.address.postalCode"
    And I click "form__btn-create" class in the "manage-account" class
    Then I should see the class "manage-account__success"
    When I click "manage-acount__success_btn-ok" class in the "manage-account__success" class
    Then I should see the class "manage-account__summary"
    And the value of model "update.account.firstName" should be "EDITED"
    And the value of model "update.account.lastName" should be "EDITED"
    And the value of model "update.account.address.street" should be "EDITED"
    And the value of model "update.account.address.city" should be "EDITED"
    And the value of model "update.account.address.state" should be "CA"
    And the value of model "update.account.address.postalCode" should be "EDITED"
    When I clear the field "update.account.firstName"
    And I enter "Carl" in the textarea "update.account.firstName"
    And I clear the field "update.account.lastName"
    And I enter "Swanson" in the textarea "update.account.lastName"
    And I clear the field "update.account.address.street"
    And I enter "19 Crescent St" in the textarea "update.account.address.street"
    And I clear the field "update.account.address.city"
    And I enter "Portland" in the textarea "update.account.address.city"
    And I click on "ME" in the dropdown "update.account.address.state"
    And I clear the field "update.account.address.postalCode"
    And I enter "04102" in the textarea "update.account.address.postalCode"
    And I click "form__btn-create" class in the "manage-account" class
    Then I should see the class "manage-account__success"
