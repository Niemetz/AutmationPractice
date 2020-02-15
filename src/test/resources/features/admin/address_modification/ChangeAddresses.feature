# Author: John Nguyen
# Author Email: john.nguyen@yahoo.com
#############################################
# Epic Requirement ID  = UPM_1 (ARM-10)  -> User Profile Management 
# Story Requirement ID = UPM_1.9 (ARM-18) -> Admin right to manage user's address.
############################################# 
# Pre Condition:
# - User's address already exists in user's profile
# Post Condition:
# - Admin can modify user's address.

# Develpment Build Version & Test Cycle Version:
# CHANGE AGAIN

@Test_Cycle:Regression_Release_1.0_RC_1.0 
@Build_Cycle:Build_Release_1.0_RC_1.0 

Feature: Admin's right to modify user's address
  As the Administrator of the Automatin Practice Shopping Website
  I would like to modify a user's address
  So that I verify that I have the admin right to modify user's address.

  @ModifyUserAddress
  Scenario: Admin mofidies a user's address.
    Given user navigates to the Automation Practice "Home" website
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
    
