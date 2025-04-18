
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

Background:
		Given I landed on Ecommerce Page
  @Regression
  Scenario Outline: Positive test of submitting the order
    Given logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name 												| password       |  productName |
      | gayatri98@gmail.com| Gayatri@123 |   ZARA COAT 3|
     
