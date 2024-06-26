package service

import io.qameta.allure.Step
import io.restassured.response.Response

class TopTenWordsService {

    @Step("Send get all posts request")
    fun getAllResource(): Response? {
        val httpClientService = HttpClientService(BASE_URL)
        val headers = mutableMapOf<String, String>()
        headers["Content-type"] = "application/json; charset=UTF-8"

        return httpClientService.sendGetRequest(POST_ENDPOINT, headers)
    }
    companion object {
        private const val BASE_URL = "URL"
        private const val POST_ENDPOINT = "/posts"
    }
}