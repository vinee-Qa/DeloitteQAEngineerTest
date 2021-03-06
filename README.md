# DeloitteQAEngineerTest
Author: Vineeta Chaudhary (vineeta984@gmail.com)
## Tools used
* Maven Version 3.8.1 64bit
* Java 8
* Browser Stack

## Types of Test
There are 2 types of tests in the project
1. local tests:
   
   These tests can be run through maven and located in the src/test folder inside the package `com.deloitte.test.maven`
   
   #### Browsers Covered
    * Google Chrome
    * Mozilla Firefox
    * Microsoft Edge
    
   #### How to execute 
   To execute these tests please navigate to the project root and run the following command:
   
   `mvn clean test`
   
2. Browserstack based tests:
   
   These tests are executed remotely on browserstack. 
   They cannot be executed through maven as the `config` parameter is being provided through xmls.
   The results can be viewed on BrowserStack Dashboard.
   
   #### Pre-requisites 
   
    To execute these test cases, two environment variables need to be set as shown below:
   
   1. On Windows
      
      `setx BROWSERSTACK_USERNAME = "<Your BrowserStack UserName>"`
      
      `setx BROWSERSTACK_ACCESS_KEY = "<Your BrowserStack Access Key>"
      `
   2. On Linux/Mac OS
      
      `export BROWSERSTACK_USERNAME = "<Your BrowserStack UserName>"`
      
      `export BROWSERSTACK_ACCESS_KEY = "<Your BrowserStack Access Key>"`
    
   #### Browsers Covered
    * Google Chrome
    * Mozilla Firefox
    * Microsoft Edge
    * Safari
    
   #### How to execute
    To execute test cases in a single web browser (currently setup as Chrome) execute the following steps:
   
    1. Set up the environment variables as highlighted in pre-requisites section.
    2. Open the project in an IDE such as IntelliJ.
    3. Navigate to `config` folder
    4. To run test cases in a single browser (defaults to Chrome), right click on `single.testng.xml` and select `run`
    5. To run tests cases in parallel on multiple browsers, right click on `parallel.testng.xml` and select `run`

### Uncovered scenerios
No test cases have been run on mobile browsers due to following reasons:
1. My laptop does not support Android Studio
2. I am not very well versed with CloudStack