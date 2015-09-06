Source  
https://github.com/cloudfoundry-samples/spring-music

`git clone https://github.com/cloudfoundry-samples/spring-music.git`

To run locally with Mongo  
`bash ./gradlew tomcatRun -Dspring.profiles.active=mongodb`

To kill running app locally  
`kill $(ps aux | grep 'GradleDaemon' | awk '{print $2}')`

To test  
`curl -X GET --url http://localhost:8080/spring-music/`

To build and distribute .zip and .war  
```shell
bash ./gradlew clean war copyWar zipStatic && \
bash ./deploy.sh
```

Links  
https://docs.gradle.org/current/userguide/working_with_files.html
http://www.codejava.net/coding/common-conversion-patterns-for-log4js-patternlayout
http://help.papertrailapp.com/kb/configuration/java-log4j-logging/