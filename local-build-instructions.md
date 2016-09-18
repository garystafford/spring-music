### Source Code
Location: <https://github.com/cloudfoundry-samples/spring-music>
```bash
git clone https://github.com/cloudfoundry-samples/spring-music.git
```

### Run Locally
One 1x to update Gradle version
```bash
./gradlew wrapper
```

To run locally with Mongo
```bash
./gradlew clean build tomcatRun -Dspring.profiles.active=mongodb -Dmongodb.url=127.0.0.1
```

To kill running app locally if necessary
```bash
kill $(ps aux | grep 'GradleDaemon' | awk '{print $2}')
```

### Test Locally
```bash
curl -X GET -I --url http://localhost:8080/spring-music # headers only
curl -X GET --url http://localhost:8080/spring-music
```

### Build and Publish to GitHub
To build and publish .zip and .war
```bash
./gradlew clean build warNoStatic warCopy zipGetVersion zipStatic \
  && sh ./deploy.sh
```

### Helpful Links
- <https://docs.gradle.org/current/userguide/working_with_files.html>
- <http://www.codejava.net/coding/common-conversion-patterns-for-log4js-patternlayout>
- <http://help.papertrailapp.com/kb/configuration/java-log4j-logging>
