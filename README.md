# Test Automation Framework: jsonPlaceHolder

This project contains a test automation framework developed in Kotlin, utilizing JUnit 5, Rest Assured, Allure, and Cucumber.

## Running Tests

### Manual Execution

To run tests manually:
1. Navigate to the `runner` package.
2. Run the `TestRunner` class.

### Gradle Execution

To run tests via Gradle:
1. Use the following command in terminal:
   ```sh
   ./gradlew test
   
## Generating Reports

To generate and view test reports using Allure:
1. After running tests, use the following command to generate Allure reports:
   ```sh
   allure serve build/allure-results
