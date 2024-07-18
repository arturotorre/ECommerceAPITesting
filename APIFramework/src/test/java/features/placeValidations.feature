Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is being succesfully added using AddPlaceAPI

# The double quotes let Cucumber know that those values are dynamic.
	Given Add Place Payload with "<name>" "<language>" "<address>"
# The angle brackets < > let Cucumber know to not use the value of the String in the test case but to use the value provided in the Examples section.
	
	When user calls "addPlaceAPI" with "Post" http request
	Then the API call is successfull with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	

	
	
	Examples:
		|name   | language | address |
		|Arturos| English  | Ferrara |
		|Vanes  | Spanish  | Lux     |


@DeletePlace
Scenario: Verify if Delete Place funtionality is working as expected
	
		Given DeletePlace Payload
		When user calls "deletePlaceAPI" with "Post" http request
		Then the API call is successfull with status code 200
		And "status" in response body is "OK"
		
		
		