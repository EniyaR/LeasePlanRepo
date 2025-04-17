Feature: Petstore API

  Background: 
  Given Set the pet endpoint

  Scenario: Create, Get and Delete pet
  When Send POST request with valid data
  Then Status code should be 200
  When Send GET request to get pet details
  Then Status code should be 200
  When Send DELETE request to delete pet details
  Then Status code should be 200
  
  Scenario: Get Pet by ID - Invalid
  When Send GET request for non-exists petID 201
  Then Status code should be 404
   	
  