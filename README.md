# executor

This framework is developed by me since 2019 for my personal preferences and end goal is develop this framework all platform compatible execution unit such as web, webservices, mobile and desktop with GUI. Currently I have been implementing mobile layer as well but I removed some mobile layers from this version as only we need web and webservices in this framework. Separate command based is used for web since we can enhance further development and error handling. Apart from that as a reporting mechanism, used allure report. Xml classes are used for being data-driven framework.

1. mvn install -Dmaven.test.skip=true  <br/>
2.Change webConfigData.xml testDataFilePath to resources folder. (E.g -D:\test-automation-framework\src\main\resources) <br/>
3.Change serviceConfigData.xml testDataFilePath to resources folder. (E.g -D:\test-automation-framework\src\main\resources)<br/>
4.After executing if you need to check report - navigate to terminal and execute "allure serve" command
