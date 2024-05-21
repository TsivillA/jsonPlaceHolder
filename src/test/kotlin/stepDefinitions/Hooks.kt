package stepDefinitions

import context.ScenarioContext
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario

class Hooks(private val scenarioContext: ScenarioContext) {

    @Before
    fun beforeScenario(scenario: Scenario) {
        scenario.log("starting scenario: " + scenario.name)
        scenarioContext.createScenarioContext()
        scenarioContext.setScenario(scenario)
    }

    @After
    fun afterScenario(scenario: Scenario) {
        scenario.log("scenario status: " + scenario.status)
    }

}