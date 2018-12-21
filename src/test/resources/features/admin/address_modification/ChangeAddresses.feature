#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
## Author: John Nguyen
#############################################
# Epic Requirement ID  = UPM_1 (ARM-10)  -> User Profile Management 
# Story Requirement ID = UPM_1.9 (ARM-18) -> View Order History And Details 
# Acceptance Criteria :
# As a user of the Automation Practice website 
# I should be able to see what I have order via the "View Order History and Details" page 
# So I know what I have ordered.
############################################# 
# Pre Condition:
# - User can access to his/her user profile
# Post Condition
# - User can view his/her order history and details
#  I want to see this file gets pulled....
@Test_Cycle:Regression_Release_1.0_RC_1.0 
@version:Build_Release_1.0_RC_1.0 
Feature: Address modification

  @Address-Change
  Scenario: user is able to edit/modify his/her addresses
    Given user navigates to Amazon "Home" website
    Then user lands on the "Home" page
    And user verifies that all expected elelments are displayed on the page
    When user clicks on the "Sign in" button
    Then user lands on the "Login" page
    And user verifies that all expected elelments are displayed on the page
    When user enters "nguyen7744@yahoo.com" into the "Email address" input field
    And user enters "Niemetz1990!" into the "Password" input field
    And user clicks on the "Sign in" button
    Then user lands on the "My Account" page
    And user verifies that all expected elelments are displayed on the page
    When user clicks on the "MY ADDRESSES" button
    Then user lands on the "My Address" page
    And user verifies that all expected elelments are displayed on the page
    And user logs out