# Description
This is a e2e tests project written in Kotlin where multipe Gradle sub modules are used

## Tests execution

In order to run `sub-module-one` tests run the following command
```
./gradlew clean runTestsOne
```

In order to run `sub-module-two` tests run the following command
```
./gradlew clean runTestsTwo
```
## Allure reports
In order to run build and serve Allure reports run
```
./gradlew allureAggregateServe
```
