
Feature: Feature to test the Update User functionality

#Scenario 1
Scenario: Check if the admin user is able to update user details successfully
Given The user details that need to be updated
When The admin user submits the user details
Then The user details should get updated successfully


#Scenario 2
Scenario: Check if admin user is able to update user details obtained from external file successfully
Given The user details to be updated are passed from external file
When The admin user submits the user details given in	external file
Then The user details obtained from external file should get updated successfully


#Scenario 3
Scenario: Check if admin user is able to sent the dynamic request & update user details obtained from external file successfully
Given The user details to be updated are passed from external file & request body is dynamic
When The admin user submits the dynamic request body & user details given in external file
Then The request should be sent & user details obtained from external file should get updated successfully


#Scenario 4
Scenario: Check if admin user is able to sent the request & update user details obtained from external file & java object successfully
Given The user details to be updated are passed from external file & request body as java object
When The admin user submits the request with user details given in external file & request body as java object
Then The request should be sent & user details obtained from external file, java object should get updated successfully





