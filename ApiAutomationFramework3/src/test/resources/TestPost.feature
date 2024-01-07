
Feature: Feature to test the Get Details functionality

#Scenario 1
Scenario: Check if the admin user is able to create a user successfully
Given The user details which needs to be created
When The admin user submits the user details which will be created
Then The user should be created successfully


#Scenario 2
Scenario: Check if the admin user is able to create a user successfully with details obtained from external file
Given The user details which needs to be created are passed from external file
When The admin user submits the user details as a request & user will be created
Then The user should be created successfully as per details obtained from external file


#Scenario 3
Scenario: Check if the admin user is able to create a user successfully with details obtained from external file, request body is dynamic
Given The user details which needs to be created are passed from external file & request body is dynamic
When The admin user submits the user details(passed from external file) as a request & user will be created
Then The user should be created successfully as per details obtained from external file & the request body


#Scenario 4
Scenario: Check if the admin user is able to create a user successfully with details obtained from external file, request body as java object
Given The user details which needs to be created are passed from external file & request body as java object
When The admin user submits the user details(passed from external file and as java object) as a request & user will be created
Then The user should be created successfully as per details obtained from external file & the request body as java object


#Scenario 5
Scenario: Check if the admin user is able to register a user successfully with details obtained from external file
Given The user to be registered & request details are passed from external file
When The admin user submits the user details as a request & user will be registered
Then The user should be registered successfully


#Scenario 6
Scenario: Check if the admin user is not able to register a user with details obtained from external file
Given The user to be registered & the request details are passed from external file
When The admin user submits the user details as a request & user will not be registered
Then The user should not be registered


#Scenario 7
Scenario: Check if the user is not able to login
Given The user to be logged in & the request details are passed from external file
When The user submits the details as a request & user will not be able to login
Then The user should not be able to login





