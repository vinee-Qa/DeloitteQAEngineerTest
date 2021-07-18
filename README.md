# DeloitteQAEngineerTest
Author: Vineeta Chaudhary (vineeta984@gmail.com)
## Tools used
* Maven Version 3.8.1 64bit
* Java 8
* Browser Stack

## Types of Test
There are 2 types of tests in the project
1. local tests 
   These tests can be run through maven and located in the src/test folder inside the package `com.deloitte.test.maven`
   To execute these tests please navigate to the project root and run the following command
   
   `mvn clean test`
   
2. Browserstack based tests
    These tests are run remotely on browserstack and require the following parameters to be set
   1. On Windows
      
      `
      setx BROWSERSTACK_USERNAME = "<Your BrowserStack UserName>"
      setx BROWSERSTACK_ACCESS_KEY = "<Your BrowserStack Access Key>"
      `
   2. On Linux/Mac OS
      
      `
      export BROWSERSTACK_USERNAME = "<Your BrowserStack UserName>"
      export BROWSERSTACK_ACCESS_KEY = "<Your BrowserStack Access Key>"
      `  

### Browsers Covered
* Google Chrome
* Mozilla Firefox
* Microsoft Edge

### Uncovered scenerios
No test cases have been run on mobile browsers as my laptop specs do not support Android Studio