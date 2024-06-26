package stepDefinitions

import context.ScenarioContext
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.qameta.allure.Allure
import io.restassured.response.Response
import service.TopTenWordsService
import utils.WordCounter

class TopTenWordsStepDefinition(
    private val scenarioContext: ScenarioContext,
    private val topTenWordsService: TopTenWordsService) {

    @Given("the getAll request is sent to the service")
    fun theGetAllRequestIsSentToTheService() {
        val response: Response? = topTenWordsService.getAllResource()
        scenarioContext.put("response", response as Response)
    }

    @And("ranking of words should be displayed")
    fun rankingOfWordsShouldBeDisplayed() {
        val response: Response = scenarioContext.get("response")
        val logMessage = StringBuilder();
        var rank = 1
        WordCounter.wordRanker(response.path("title"), response.path("body")).forEach { (word, count) ->
            logMessage.append("$rank. $word - $count\n")
            rank++
        }
        Allure.addAttachment("Word Ranking", logMessage.toString())
    }
}