package utils

class WordCounter {

    companion object {
        fun wordRanker(titles: List<String>, bodies: List<String>): Map<String, Int> {
            val combinedList = titles + bodies

            // Use a Map to count the frequency of each word
            val wordCount = combinedList
                .flatMap { it.split("\\s+".toRegex()) }
                .groupingBy { it }
                .eachCount()

            return wordCount.entries
                .sortedByDescending { it.value }
                .take(10)
                .associate { it.key to it.value }
                .toMap()
        }
    }
}