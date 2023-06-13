# Quality Assurance | Challenge - API



## Description
This project is an automation project using the public API http://dummy.restapiexample.com and shoul implement the 
followig test cases using BDD:  
* Create an employee  
* Validate the employee was created correctly   
* Delete the user

## How to run tests
You could execute the tests, with one of the folowing ways:
* Executing command "mvn test"
* Executing TestRunner class

## Dependencies 
This project was implemented using Java 18.
Additionally is used:
* java (v18)
* cucumber-java (v6.10.3)
* cucumber-junit (v6.10.3)
* rest-assured (v4.4.0) 
* jackson-databind (v2.12.7.1)

## Difficulties
This public API have a rate limit defined, and i can't solve the bug of receive an 429 in the second request.  
This fix will be available on the next release.

## Notes 
A CI was implemented in GitHub Actions but is not working well, will be fixed in the next release.  
Another functionality that is in the roadmap for the next release is the implementation of Dependabot, to assure that 
dependencies are updated.

(C) Edgar Moreira 2023-06-13