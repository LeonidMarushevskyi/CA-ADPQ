#WIP, not currently running or passing

Feature: Messages
  As a user I want to be able to send messages to my assigned case worker.
  As a user I want to be able to mark a message deleted so it does not show up in my message center.


  Scenario: Create New Message
    Given I am logged in with username "swansoncp5@gmail.com" and password "Test123456"
    When I click "case-worker-messenger__compose-link" class in the "case-worker-messenger" class
    Then I should see the class "case-worker-messenger__composer-content"
    And the class "case-worker-messenger__composer-send" should be disabled
    When I enter "Automation Subject" in the textarea "userMessage.subject"
    Then the class "case-worker-messenger__composer-send" should be disabled
    When I enter "Automation Message" in the textarea "userMessage.content"
    Then the class "case-worker-messenger__composer-send" should be enabled
    When I click "case-worker-messenger__composer-send" class in the "case-worker-messenger__composer-content" class
    Then the text of class "case-worker-messenger__message-sender" in the last row of repeater "message in messages" should be "Carl Swanson"
    And the text of class "case-worker-messenger__message-subject" in the last row of repeater "message in messages" should be "Automation Subject"
    And the text of class "case-worker-messenger__message-contents" in the last row of repeater "message in messages" should be "Automation Message"
    And the text of class "case-worker-messenger__message-date" in the last row of repeater "message in messages" should be the current date
