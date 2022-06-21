# VK TEST How To Use
This project contains tests for [VK likes methods](https://dev.vk.com/method/likes)

## Prerequisites
- Java 11 
- Maven 3.6

## Test run
Tests are located in the package src/test/java/tests (test classes AddDeleteLikesTest and CheckLikesTest)
- run following command in the terminal with valid credentials
```
mvn clean 
mvn -Dlogin="phoneNumber" -Dpass="password123" -Dclient_secret="valid_client_secret" test
```

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

*** 
## Demands to the task

- [X] Использовать наш существующий VK Java SDK: https://dev.vk.com/sdk/java
- [X] Понятная система логирования ошибок и результатов. (java.util.logging.Logger + Allure Report)
- [X] Один позитивный и один негативный кейс на каждый метод.
- [X] Использовать DDT подход. Достаточно использовать для одного теста на выбор. (Data Provider)
- [X] Разбить тесты на группы (TestNG). Выбор групп для тестов не принципиален. (xmlSuites)
- [X] Не хранить пользователей (ID/access_token) в коде. (properties файлы, System properties для логина  авторизации)
- [X] Прислать архив или ссылку на репозиторий.
