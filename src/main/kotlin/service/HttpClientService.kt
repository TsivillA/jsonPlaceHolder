package service

import io.qameta.allure.Step
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import utils.ConfigReader
import utils.When
import java.util.*

class HttpClientService(url: String) {
    private var request: RequestSpecification? = null
    private var properties: Properties? = null
    private var configReader: ConfigReader = ConfigReader()

    init {
        properties = setProperties()
        RestAssured.baseURI = configReader.getValue(url, properties)
        request = RestAssured.given().relaxedHTTPSValidation()
    }

    fun setProperties(): Properties? {
        properties = configReader.loadConfig()
        return properties
    }

    @Step("Sending post request")
    fun sendPostRequest(endpoint: String, payload: String?, headers: Map<String, String>): Response? {
        request = request?.headers(headers)
        var response: Response? = null
        try {
            if (request != null) {
                response = request!!
                    .urlEncodingEnabled(false)
                    .filter(AllureRestAssured())
                    .body(payload).log().all()
                    .When()
                    .post(endpoint)
                    .then()
                    .extract().response()
            }
        } catch (e: Exception) {
            System.err.println("Error occurred while sending post request: ${e.message}")
        }
        return response
    }

    @Step("Sending get request")
    fun sendGetRequest(endpoint: String, headers: Map<String, String>): Response? {
        request = request?.headers(headers)
        var response: Response? = null

        try {
            if (request != null) {
                response = request!!
                    .urlEncodingEnabled(false)
                    .filter(AllureRestAssured())
                    .When()
                    .get(endpoint)
                    .then()
                    .extract().response()
            }
        } catch (e: Exception) {
            System.err.println("Error occurred while sending get request: ${e.message}")
        }
        return response
    }

    @Step("Sending get request")
    fun sendGetRequest(endpoint: String, headers: Map<String, String>, queryParam: String): Response? {
        request = request?.headers(headers)
        var response: Response? = null
        try {
            if (request != null) {

                response = request!!
                    .urlEncodingEnabled(false)
                    .filter(AllureRestAssured())
                    .queryParam("id", queryParam)
                    .When()
                    .get(endpoint)
                    .then()
                    .extract().response()
            }
        } catch (e: Exception) {
            System.err.println("Error occurred while sending get request: ${e.message}")
        }
        return response
    }

    @Step("Sending put request")
    fun sendPutRequest(endpoint: String, payload: String?, headers: Map<String, String>): Response? {
        request = request?.headers(headers)
        var response: Response? = null

        try {
            if (request != null) {
                response = request!!
                    .urlEncodingEnabled(false)
                    .filter(AllureRestAssured())
                    .body(payload).log().all()
                    .When()
                    .put(endpoint)
                    .then()
                    .extract().response()
            }
        } catch (e: Exception) {
            System.err.println("Error occurred while sending put request: ${e.message}")
        }
        return response
    }

    @Step("Sending delete request")
    fun sendDeleteRequest(endpoint: String, headers: Map<String, String>): Response? {
        request = request?.headers(headers)
        var response: Response? = null

        try {
            if (request != null) {
                response = request!!
                    .urlEncodingEnabled(false)
                    .filter(AllureRestAssured())
                    .When()
                    .delete(endpoint)
                    .then()
                    .extract().response()
            }
        } catch (e: Exception) {
            System.err.println("Error occurred while sending delete request: ${e.message}")
        }
        return response
    }
}