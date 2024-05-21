package stepDefinitions.common

import context.ScenarioContext
import io.cucumber.java.en.Then
import io.restassured.response.Response
import org.junit.jupiter.api.Assertions

class CommonStepDefinitions(private val scenarioContext: ScenarioContext) {

    private var response: Response? = null

    @Then("the response with status code {string} should be received")
    fun theResponseWithStatusCodeShouldBeReceived(statusCode: String) {
        response = scenarioContext.get("response")
        Assertions.assertEquals(Integer.parseInt(statusCode), response?.statusCode());
    }

}