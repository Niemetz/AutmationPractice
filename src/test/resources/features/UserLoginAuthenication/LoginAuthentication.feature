Feature: Amazon user login
  Scenario: navigate to login page
    Given user navigates to Amazon "Home" website
    Then he lands on the "Home" page
    And he verifies that all expected elements are displayed on the page
    And he clicks on the "Sign in securely" button
    Then he lands on the "Login" page
    And he verifies that all expected elements are displayed on the page
    When he enters "nguyen7744@yahoo.com" into the "Email" input field
    And he enters "john7755" into the "Password" input field
    #And he clicks on the "Sign in" button
    #Then he lands on the "account main" page
   # And he verifies that all elements are on the page
   # When he clicks on the "Your Account" link
   # Then he lands on the "Your Account" page
   #And he selects the "View Archived Orders" link from the "Order History" categorty listing