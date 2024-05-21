package stepDefinitions

import context.ScenarioContext
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.restassured.response.Response
import org.junit.jupiter.api.Assertions
import service.CRUDService

class CRUDStepDefinitions(
    private val crudService: CRUDService,
    private val scenarioContext: ScenarioContext
) {
    @Given("user enters details for sending post request: {string}, {string}, {string}")
    fun userEntersDetailsForSendingPostRequest(title: String?, body: String?, userId: String?) {
        scenarioContext.put("title", title as String)
        scenarioContext.put("body", body as String)
        scenarioContext.put("userId", userId as String)
        scenarioContext.getScenario()
            ?.log("Create a new resource for post entered: " + scenarioContext.get<Any>("title"))
    }

    @When("the post request is sent to the service")
    fun thePostRequestIsSentToTheService() {
        val response = crudService.postNewResource(
            scenarioContext.get("title"),
            scenarioContext.get("body"),
            scenarioContext.get("userId")
        )
        scenarioContext.put("response", response as Response)
        scenarioContext.getScenario()?.log(response.asPrettyString())
    }

    @And("successful response with post details: {string}, {string}, {string} should be received")
    fun successfulResponseWithPostDetailsShouldBeReceived(title: String?, body: String?, userId: String?) {
        val response = scenarioContext.get<Response>("response")
        Assertions.assertNotNull(response.path("id"))
        Assertions.assertEquals(title, response.path("title"))
        Assertions.assertEquals(body, response.path("body"))
        Assertions.assertEquals(userId, response.path("userId"))
    }

    @Given("user enters details for sending get request: {string}")
    fun userEntersDetailsForSendingGetRequest(id: String?) {
        scenarioContext.put("id", id as String)
        scenarioContext.getScenario()?.log("Get a resource for postId entered: " + scenarioContext.get<Any>("id"))
    }

    @When("the get request is sent to the service")
    fun theGetRequestIsSentToTheService() {
        val response = crudService.getResource(scenarioContext.get("id"))
        scenarioContext.put("response", response as Response)
        scenarioContext.getScenario()?.log(response.asPrettyString())
    }

    @And("successful response with post details should be received")
    fun successfulResponseWithPostDetailsShouldBeReceived() {
        val response = scenarioContext.get<Response>("response")
        Assertions.assertNotNull(response.path("id"))
        Assertions.assertNotNull(response.path("title"))
        Assertions.assertNotNull(response.path("body"))
        Assertions.assertNotNull(response.path("userId"))
    }

    @Given("user enters details for sending put request: {string} {string}, {string}, {string}")
    fun userEntersDetailsForSendingPutRequest(id: String?, title: String?, body: String?, userId: String?) {
        scenarioContext.put("id", id as String)
        scenarioContext.put("title", title as String)
        scenarioContext.put("body", body as String)
        scenarioContext.put("userId", userId as String)
        scenarioContext.getScenario()?.log("Update a resource for post entered: " + scenarioContext.get<Any>("id"))
    }

    @When("the put request is sent to the service")
    fun thePutRequestIsSentToTheService() {
        val response = crudService.updateResource(
            scenarioContext.get("id"),
            scenarioContext.get("title"),
            scenarioContext.get("body"),
            scenarioContext.get("userId")
        )
        scenarioContext.put("response", response as Response)
        scenarioContext.getScenario()?.log(response.asPrettyString())
    }

    @Given("user enters details for sending delete request: {string}")
    fun userEntersDetailsForSendingDeleteRequest(id: String?) {
        scenarioContext.put("id", id as String)
        scenarioContext.getScenario()?.log("Delete a resource for postId entered: " + scenarioContext.get<Any>("id"))
    }

    @When("the delete request is sent to the service")
    fun theDeleteRequestIsSentToTheService() {
        val response = crudService.deleteResource(scenarioContext.get("id"))
        scenarioContext.put("response", response as Response)
        scenarioContext.getScenario()?.log(response.asPrettyString())
    }
}