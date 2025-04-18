Feature: PetStore Orders API

Background:
     Given Set the pet endpoint

Scenario: Get Pet Inventory
     When Send GET request to retrieve pet inventory
     Then Status code should be 200

Scenario: Get Pet Inventory- Invalid
     When Send invalid GET request to retrieve pet inventory
     Then Status code should be 404
     
 Scenario: Place an Order for a pet
      When Send POST request to place an order
      Then Status code should be 200
      
Scenario: Place an invalid order for a pet
     When Send invalid POST request to place an order
     Then Status code should be 400