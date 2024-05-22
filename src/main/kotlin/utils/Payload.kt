package utils

import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateException
import org.junit.jupiter.api.Assertions
import java.io.IOException
import java.io.StringWriter

class Payload {

    /**
     * Returns the request payload created with proper values for the keys
     *
     * @param valueTemplate contains values to be added to the template of payload
     * @param template template for payload
     * @return content of payload that can be added as request body
     */
    fun getPayLoad(valueTemplate: Map<String, String>, template: Template): String {
        val stringWriter = StringWriter()
        try {
            template.process(valueTemplate, stringWriter)
        }  catch (e: TemplateException) {
            Assertions.fail("Error occurred while processing template: $e");
        } catch (e: IOException) {
            Assertions.fail("Error occurred while writing template: $e");
        }
        return stringWriter.toString();
    }

    /**
     * Loads the template of request payload from src/test/resources/templates folder
     * @param templateName path of filename
     * @return instance of template
     */
    fun getPayloadTemplate(templateName: String): Template? {
        val configuration = Configuration(Configuration.VERSION_2_3_32)
        configuration.setClassForTemplateLoading(Payload::class.java, "/templates")
        var template: Template? = null

        try {
            template = configuration.getTemplate(templateName)
        } catch (e: Exception) {
            Assertions.fail("Error occured while getting $templateName file $e");
        }
        return template
    }
}