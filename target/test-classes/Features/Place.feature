Feature: Validating place API'S

@Addplace
Scenario Outline: Verify if place is bring successfullly added using add place API'S
	Given Add place Payload with "<name>" "<language>" "<Address>" "<data>"
	When user calls "AddPlaceAPI" with "post" http request
	Then API Call is success with status code is 200
	And "status" in response is "OK"
	And "scope" in response is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
Examples: 
	|name          | language |Address         | data|
	|Abhishek Verma| English  |77 shanker niwas|shirt,t-shirts|
	#|Abhishek Gupta| English  |shanker niwas   |shirt         |
	

@DeletePlace	

Scenario: Verify if deleteplace is working fine
	Given DeletePlace payload
	When 	user is calling  "DeletePlaceAPI" with "post" http request
	Then  API is success with status code is "200"
	And   "status" in the  response is "OK"		


	
	
	
