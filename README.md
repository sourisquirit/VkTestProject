# VK TEST How To Use
This project contains tests for [VK likes methods](https://dev.vk.com/method/likes)

## Prerequisites
- Java 11 
- Maven 3.6

## Test run
Tests are located in the package src/test/java/tests (test classes AddDeleteLikesTest and CheckLikesTest)
- to start all tests run following command in the terminal with valid credentials: 
```
mvn clean 
mvn -Dlogin="phoneNumber" -Dpass="password123" -Dclient_secret="valid_client_secret" test
```
- to run a predefined suite of tests chose one of xml files in package src/test/java/xmlSuites. You can start it from xml file or 
uncomment the necessary file in pom.xml (configuration of maven surefire plugin).

## Reporting
[Allure](https://docs.qameta.io/allure/) is used for reporting in the framework 
- to install Allure locally on Windows using Scoop run. For other cases use [documentation](https://docs.qameta.io/allure/#_get_started)
```
scoop install allure
```
- to open Allure Report run following command in the terminal: 
```
allure serve PATH-TO-ALLURE-REPORT-FOLDER
```
After that the report will be opened automatically. You can also open a generated report from terminal using command:
```
allure open PATH-TO-ALLURE-REPORT-FOLDER
```
