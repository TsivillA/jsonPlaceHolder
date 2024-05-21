package context

import io.cucumber.java.Scenario

class ScenarioContext {

    private var scenarioContext: HashMap<String, Any>? = null

    private var scenario: Scenario? = null

    /**
     * Creates a new scenario context as a Hashmap
     */

    fun createScenarioContext() {scenarioContext = HashMap() }

    /**
     * Adds a key-value pair to the scenario context
     *
     * @param key The key to be added
     * @param value The value associated with the key
     */
    fun put(key: String, value: Any) {scenarioContext?.put(key, value)}


    /**
     * Retrieves a value from the scenario context based on the provided key
     *
     * @param key The key to retrieve the value.
     * @param <T> The generic type of the value
     * @return The value associated with the Key
     */
    fun <T> get(key: String?): T { return scenarioContext?.get(key) as T }

    /**
     * Sets the current scenario for the class
     *
     * @param scenario Cucumber Scenario object
     */
    fun setScenario(scenario: Scenario) { this.scenario = scenario }

    /**
     * Gets the current Cucumber Scenario object
     *
     * @return Cucumber Scenario object
     */
    fun getScenario(): Scenario? { return scenario}
}