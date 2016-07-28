Feature: Search for Results
  As a visitor, i want to view a list of Foster Homes based upon a location search

@prod
    Scenario: Search for specific foster home returns correct result
      Given I am logged in with username "swansoncp5@gmail.com" and password "Test123456"
      Then I should be on the "dashboard" page
      When I enter "95826" in the textarea "search.zip"
      And I enter "1" in the textarea "search.proximity"
      And I click the "foster-home-locator__search-input-locate" button in the "foster-home-locator__search-input"
      Then the text of class "foster-home-locator__result-name" in row "1" of repeater "location in results" should be "STANFORD YOUTH SOLUTIONS"
      And the text of class "foster-home-locator__result-street" in row "1" of repeater "location in results" should be "8912 VOLUNTEER LANE"
      And the text of class "foster-home-locator__result-city" in row "1" of repeater "location in results" should be "SACRAMENTO"
      And the text of class "foster-home-locator__result-state" in row "1" of repeater "location in results" should be "CA"
      And the text of class "foster-home-locator__result-zip" in row "1" of repeater "location in results" should be "95826"
      And the text of class "foster-home-locator__result-phone" in row "1" of repeater "location in results" should be "(916) 344-0199"
      And the text of class "foster-home-locator__result-number" in row "1" of repeater "location in results" should be "347004198"
      
@prod
    Scenario: Search for no foster home returns no results
      Given I am logged in with username "swansoncp5@gmail.com" and password "Test123456"
      Then I should be on the "dashboard" page
      When I enter "04102" in the textarea "search.zip"
      And I enter "1" in the textarea "search.proximity"
      And I click the "foster-home-locator__search-input-locate" button in the "foster-home-locator__search-input"
      Then the text of class "foster-home-locator__search-results tbody tr td" should be "No foster homes found."
