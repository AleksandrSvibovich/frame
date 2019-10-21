Feature: PetStore testing

  Scenario: 1. check get Available pets code
    Given Verify that for "getAvailable" method response code is 200

  Scenario: 2. check get Inventory pets code
    Given Verify that for "getInventory" method response code is 200

  Scenario: 3. add new pet to store
    Given Add pet with id 3256565 and name "doshik" and status "sold"

  Scenario: 4. get pet from store
    Given Get pet with id 3256565 and verify that name is "doshik"

  Scenario: 5. delete pet by id
    Given Delete pet by id 3256565