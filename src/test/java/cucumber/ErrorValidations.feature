
@tag
Feature: Error validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
        | name 												| password       |  
      	| gayatri98@gmail.com| Gayatri@       |   
     
