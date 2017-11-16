# Author: John Nguyen
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
# 

@Test_Cycle:Regression_Release_1.0_RC_1.0 
@version:Build_Release_1.0_RC_1.0
Feature: View order history and details

  @History-And-Details
  Scenario: user is able to view his/her order history and details
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
    When user clicks on the "ORDER HISTORY AND DETAILS" button
    Then user lands on the "ORDER HISTORY" page
    And user verifies that the value of the "Order Reference" field is "AFYYDFIPQ"
    And user verifies that the value of the "Date" field is "10/03/2017"
    And user verifies that the value of the "Status" field is "On backorder"