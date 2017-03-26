@version:Vencore-Sprint-6.5.0-RC-1
@version:GD-Release-6.5.0-RC-1
Feature: Requirement ID = 80.1. Administrator should have the rights to create a new user-account for analyst-1 user.
         Acceptance Criteria:
         In order to grant access to an analyst-1 user to CIAP Application
         As the CIAP adminstrator
         I should have the rights to create an user account for analyst-1 user
 @Creation-Of-User-Analyst-1
  Scenario: creation of Analyst-1 user
    Given user navigates to Amazon "Home" website
    Then user lands on the "Home" page
    And  user verifies that all expected elelments are displayed on the page
    When user clicks on the "Sign in securely" button
    Then user lands on the "Login" page
    And  user verifies that all expected elelments are displayed on the page
    When user enters "John.Nguyen@yahoo.com" into the "Email" input field
    And user enters "John The Gardener" into the "Password" input field
    Then on the "Login" page, and at the "Main" section, user verifies that all input data were conrrectly captured, saved and dislayed