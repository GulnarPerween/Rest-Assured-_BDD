Feature: Validating the  place API
@AddPlace @Regression
  Scenario Outline: verify if place is being successfully added using the AddPlace API
    Given Add place payload with <accuracy>,"<name>","<phone_number>","<address>","<language>","<website>"
    When user calls the "AddPlaceAPI" with "POST" http request
    Then the API call got sucess with status code 200
    And "status" in reponse body is equal to "OK"
    And "scope" in reponse body is equal to "APP"
    And  verify the PlaceID  for Get Request using the "<name>","<language>" using the "getPlaceAPI"

    Examples: 
      | accuracy | name             | phone_number       | address                   | language  | website            |
      |       50 | Frontline house   | (+91) 983 893 3937 | 29, side layout, cohen 09 | French-IN | http://google.com |
 #    |       25| circuithouse      |           11111111 | BANGALORE,561245          | HINDI-IN  | http://google.com  |
@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When user calls the "deletePlaceAPI" with "DELETE" http request
	Then the API call got sucess with status code 200
	And "status" in reponse body is equal to "OK"
	 