
Feature: Feature to test the Partial Update of User details functionality

#Scenario 1
Scenario: Check if the admin user is able to do partial update of user details successfully
Given The user details that need to be partially updated
When The admin user submits only the required details which needs to be updated
Then only the required user details should get updated successfully


#Scenario 2
Scenario: Check if admin user is able do partial update of user details obtained from external file successfully
Given The user details that need to be partially updated are passed from external file
When The admin user submits only the required details given in external file to be updated 
Then only the required user details obtained from external file should get updated successfully


#Scenario 3
Scenario: Check if admin user is able to sent the dynamic request & do partial update of user details obtained from external file successfully
Given The user details that need to be partially updated are passed from external file & request body is dynamic
When The admin user submits the dynamic request body & only the required details given in	external file
Then The request should be sent & only the required user details obtained from external file should get updated successfully


#Scenario 4
Scenario: Check if admin user is able to sent the request & do partial update of user details obtained from external file & java object successfully
Given The user details that need to be partially updated are passed from external file & request body as java object
When The admin user submits the dynamic request body & only the required details given in	external file & request body as java object
Then The request should be sent & only the required user details obtained from external file,java object should get updated successfully





