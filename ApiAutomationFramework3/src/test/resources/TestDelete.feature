
Feature: Feature to test the Delete User functionality

#Scenario 1
Scenario: Check if the admin user is able to delete user details successfully
Given The user details that need to be deleted
When The admin user submits the user details to delete
Then The user details should get deleted successfully


#Scenario 2
Scenario: Check if admin user is able to delete user details obtained from external file successfully
Given The user details to be deleted are passed from external file
When The admin user submits the user details to delete given in	external file
Then The user obtained from external file should get deleted successfully





