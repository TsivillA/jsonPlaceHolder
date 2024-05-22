package stepDefinitions

import context.ScenarioContext
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import io.qameta.allure.Allure
import io.qameta.allure.Description
import io.qameta.allure.Step

class Hooks(private val scenarioContext: ScenarioContext) {

    @Description("Starting scenario")
    @Before
    fun beforeScenario(scenario: Scenario) {
        Allure.addAttachment("starting scenario: ", scenario.name)
        scenarioContext.createScenarioContext()
        scenarioContext.setScenario(scenario)
    }

    @Description("finishing scenario")
    @After
    fun afterScenario(scenario: Scenario) {
        Allure.addAttachment("scenario status: ", scenario.status.toString())
    }
}