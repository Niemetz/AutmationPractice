Feature: see if I can parse the table

  Scenario: 
    Given user is on the "Indicator" page
    And user fills out the form with the followng data:
    
      | Action    | Input             | Target Element   |
      | ********* | ***************** | **************** |
      | enter     | aslak@email.com   | user name        |
      | click     | mouse click       | Save             |
      | select    | indicator link    | table Y          |
      | set       | Yes               | Release to Public|
      | select    | Homeland Security | Organization     |
      
      And user clicks on the "Custom Marking" button 
      And user land on the "ISA Markings" for "Title" field
