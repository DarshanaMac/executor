This framework is developed by me for my personal preferences. I am using this framework to implement given assignment. Separate command based use for web platform and web services i used restassured. Here i implemented reporting mechanisum. As a reporting technique i used allure report.

1. mvn install -Dmaven.test.skip=true <br/>
2.Change webConfigData.xml testDataFilePath to resources folder. (E.g -D:\test-automation-framework\src\main\resources)<br/>
3.Change serviceConfigData.xml testDataFilePath to resources folder. (E.g -D:\test-automation-framework\src\main\resources)<br/>
4.After executing if you need to check report - navigate to terminal and execute "allure serve" command
