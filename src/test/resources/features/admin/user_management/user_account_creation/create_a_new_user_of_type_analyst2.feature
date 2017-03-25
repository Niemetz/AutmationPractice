@version:Vencore-Sprint-6.5.0-RC-1
@version:GD-Release-6.5.0-RC-1
@wip
Feature: Requirement ID = 80.2. Administrator should have the rights to create a new user-account for analyst-2 user.
         As the CIAP Admin user
         I should be able to create a new user of type Analyst 2
         So that the user can access to CIAP app and performs his works.
 @Creation-Of-User-Analyst-2
  Scenario: creation of Analyst-2 user
    Given user navigates to Amazon "Home" website
    Then user lands on the "Home" page
    And  user verifies that all expected elelments are displayed on the page
    When user clicks on the "Sign in securely" button
    Then user lands on the "Login" page
    And  user verifies that all expected elelments are displayed on the page
    When user enters "John.Nguyen@yahoo.com" into the "Email" input field
    And user enters "John The Gardener" into the "Password" input field
    Then on the "Login" page, and at the "Main" section, user verifies that all input data were conrrectly captured, saved and dislayed