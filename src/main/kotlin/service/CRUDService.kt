package service

import freemarker.template.Template
import io.qameta.allure.Step
import io.restassured.response.Response
import utils.Payload

class CRUDService {

    @Step("Send create post request")
    fun postNewResource(title: String, body: String, userId: String): Response? {
        val httpClientService = HttpClientService(BASE_URL)

        val headers = mutableMapOf<String, String>()
        headers["Content-type"] = "application/json; charset=UTF-8"

        val payload = Payload()
        val template: Template? = payload.getPayloadTemplate("postRequest")

        val dataModel = mutableMapOf<String, String>()
        dataModel["title"] = title;
        dataModel["body"] = body;
        dataModel["userId"] = userId;

        val requestBody = template?.let { payload.getPayLoad(dataModel, it) }
        return httpClientService.sendPostRequest(POST_ENDPOINT, requestBody, headers)
    }

    @Step("Send get post request")
    fun getResource(id: String): Response? {
        val httpClientService = HttpClientService(BASE_URL)
        val headers = mutableMapOf<String, String>()
        headers["Content-type"] = "application/json; charset=UTF-8"

        return httpClientService.sendGetRequest(POST_ENDPOINT, headers, id)
    }

    @Step("Send update post request")
    fun updateResource(id: String, title: String, body: String, userId: String): Response? {
        val httpClientService = HttpClientService(BASE_URL)
        val headers = mutableMapOf<String, String>()
        headers["Content-type"] = "application/json; charset=UTF-8"

        val payload = Payload()
        val template: Template? = payload.getPayloadTemplate("postRequest")

        val dataModel = mutableMapOf<String, String>()
        dataModel["id"] = id;
        dataModel["title"] = title;
        dataModel["body"] = body;
        dataModel["userId"] = userId;

        val requestBody = template?.let { payload.getPayLoad(dataModel, it) }
        return httpClientService.sendPutRequest("$POST_ENDPOINT/$id", requestBody, headers);
    }

    @Step("Send delete post request")
    fun deleteResource(id: String): Response? {
        val httpClientService = HttpClientService(BASE_URL)
        val headers = mutableMapOf<String, String>()
        headers["Content-type"] = "application/json; charset=UTF-8"

        return httpClientService.sendDeleteRequest("$POST_ENDPOINT/$id", headers);
    }

    companion object {
        private  const val BASE_URL: String = "URL"
        private const val POST_ENDPOINT: String = "/posts"
    }
}