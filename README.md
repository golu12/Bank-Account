# Bank-Account

Download the project in your IDE.

configure Java 8 in your IDE.

Run mvn install to resolve the dependency of the POM.xml

Run the SpringBatchApplication.java class of the project.

Test Cases :- 

class name : src/test/java/com/international/bank/demo/TestScenarioForBankAccount.java 

case 1st : 


After succesfully starting of spring application please install Postman tool to execute the endpoints .

- For creation of first account for creating new account based on this

1. POST http://localhost:8443/accounts/saving
{
"client":{
    "id":"1",
    "customerId":"101",
    "firstName":"shivam",
    "lastName";"kesherwani",
    "emailId":"shivam.kesherwani@",
    "address":"amsterdam",
    "branch":"amsterdam",
    "ifscCode":"ICS667483",
    "accounts":[
    {"pid":"903",
    "accountName":"CAP9384958",
    "accountType":"saving",
    "customerId":"101",
    "balance":"193883"
    }
    ]
}


- Create new account based on existing account.

2. Get http://localhost:8443/accounts/current?custId=101&initialCredit=10000


- Get all customer info for new account.

3. Get http://localhost:8443/accounts/customerInfo?custId=101 


- Added DockerFile for creation of docker image as part of ci/cd
