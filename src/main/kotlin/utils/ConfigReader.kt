package utils

import org.junit.jupiter.api.Assertions
import java.io.FileInputStream
import java.io.IOException
import java.util.*

class ConfigReader {
    private var properties: Properties? = null
    private val PATH = "src/test/resources/configs/config"

    fun loadConfig(): Properties? {
        var inputStream: FileInputStream? = null
        try {
            inputStream = FileInputStream(PATH)
            properties = Properties()
            //if not null
            properties?.load(inputStream)
        } catch (e: IOException) {
            Assertions.fail("Error reading config file: $e")
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    Assertions.fail("Error closing the input stream: $e");
                }
            }
        }
        return properties
    }

    fun getValue(key: String, properties: Properties?): String {
        val value: String = properties?.getProperty(key.uppercase(Locale.getDefault()))
            ?: try {
                throw Exception("The key \"" + key.uppercase(Locale.getDefault()) + "\" does not exist in config file.")
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        return value
    }
}