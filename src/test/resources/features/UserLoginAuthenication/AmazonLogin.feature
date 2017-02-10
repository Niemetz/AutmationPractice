Feature: Amazon User login Authentication

  @Amazon-User-Login
  Scenario: Amazon user login authentication
    Given user navigates to Amazon "Home" website
    Then user lands on the "Home" page
    And  user verifies that all expected elelments are displayed on the page
    When user clicks on the "Sign in securely" button
    Then user lands on the "Login" page
    And  user verifies that all expected elelments are displayed on the page
    When user enters "John.Nguyen@yahoo.com" into the "Email" input field
    And user enters "John The Gardener" into the "Password" input field
    #Then user verifies that all input data were conrrectly captured, saved and dislayed
    Then on the "Login" page, and at the "Main" section, user verifies that all input data were conrrectly captured, saved and dislayed
    
    #And user lands on the "Account Main" page
    #And user clicks on the "Sign in" button
    #Then user lands on the "account main" page
    #And user verifies that all expected elements are displayed on the page
    #When user clicks on the "Your Account" link
   #Then user lands on the "Your Account" page
   # And user verifies that all expected elements are displayed on the page
   # And user clicks on the "Your Orders" link