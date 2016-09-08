### Source
[https://github.com/cloudfoundry-samples/spring-music](https://github.com/cloudfoundry-samples/spring-music)

`git clone https://github.com/cloudfoundry-samples/spring-music.git`

### Run Locally
To run locally with Mongo
`./gradlew clean build tomcatRun -Dspring.profiles.active=mongodb -Dmongodb.url=127.0.0.1`

To kill running app locally if necessary
`kill $(ps aux | grep 'GradleDaemon' | awk '{print $2}')`

### Test Locally
```bash
curl -X GET -I --url http://localhost:8080/spring-music # headers only
curl -X GET --url http://localhost:8080/spring-music
```

### Build and Publish to GitHub
To build and publish .zip and .war
```bash
./gradlew clean build warNoStatic warCopy zipGetVersion zipStatic && \
sh ./deploy.sh
```

### Links 
[https://docs.gradle.org/current/userguide/working_with_files.html](https://docs.gradle.org/current/userguide/working_with_files.html)
[http://www.codejava.net/coding/common-conversion-patterns-for-log4js-patternlayout](http://www.codejava.net/coding/common-conversion-patterns-for-log4js-patternlayout)
[http://help.papertrailapp.com/kb/configuration/java-log4j-logging](http://help.papertrailapp.com/kb/configuration/java-log4j-logging)
