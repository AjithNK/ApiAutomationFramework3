
Feature: Feature to test the Get Details functionality

#Scenario 1
Scenario: Check if the admin user is able to get user details successfully
Given The users whose details need to be retrieved
When The admin user submits the users whose details need to be retrieved 
Then The users details should be retrieved successfully


#Scenario 2
Scenario: Check if the admin user is able to get user details(single user) obtained from external file successfully
Given The user(single user) whose details need to be retrieved are passed from external file
When The admin user submits the user whose details need to be retrieved given in external file
Then The user details(single user) obtained from external file should be retrieved successfully


#Scenario 3
Scenario: Check if the admin user is able to get users details(all users) obtained from external file successfully
Given The users whose details need to be retrieved are passed from external file
When The admin user submits the users whose details need to be retrieved given in external file
Then The users details(all users) obtained from external file should be retrieved successfully


#Scenario 4
Scenario: Check if admin user doesn't get the details of a user who is not present & request details passed from external file
Given The user to be searched & request details are passed from external file
When The admin user submits the user details(who is not present) given in external file
Then The user details should not be shown as the searched user is not present


#Scenario 5
Scenario: Check if the admin user is able to get resource details(list resource) & request details passed from external file
Given The resources url whose details need to be retrieved & request details are passed from external file
When The admin user submits the resources url & request details to be retrieved given in external file
Then The resource details(list resource) obtained from external file should be retrieved successfully


#Scenario 6
Scenario: Check if the admin user is able to get resource details(single resource) & request details passed from external file
Given The resource url whose details need to be retrieved & request details are passed from external file
When The admin user submits the resource url & request details to be retrieved given in external file
Then The resource details(single resource) obtained from external file should be retrieved successfully


#Scenario 7
Scenario: Check if the admin user doesnt get the details of a resource which is not present & request details passed from external file
Given The resource url which need to be retrieved & request details are passed from external file
When The admin user submits the resource url(of the resource which is not present) & request details given in external file
Then The resource details should not be shown as the searched resource is not present


#Scenario 8
Scenario: Check if the admin user is able to get users details with a delay in response obtained from external file
Given The url for users whose details need to be retrieved & request details are passed from external file
When The admin user submits the users url whose details need to be retrieved as a request
Then The users details should be retrieved with a delay in getting the response





