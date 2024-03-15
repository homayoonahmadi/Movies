package ir.programmerplus.movies.utils

import timber.log.Timber
import java.nio.charset.StandardCharsets

/**
 * This function logs large contents to logcat
 *
 * @param priority Log priority, for example: Log.DEBUG
 * @param tag      Log tag
 * @param content  Log content
 */
fun log(priority: Int, tag: String, content: String) {
    try {
        val size = content.toByteArray(StandardCharsets.UTF_8).size

        if (size > MAX_LOG_BYTES) {
            val text: String = content.trim(MAX_LOG_BYTES)

            Timber.tag(tag).log(priority, text)
            log(priority, tag, content.substring(text.length))

        } else {
            Timber.tag(tag).log(priority, content)
        }
    } catch (e: Exception) {
        e.printStackTrace()

    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
    }
}

/**
 * This function trims the text by requested byte size
 *
 * @param sizeInBytes maximum byte size
 * @return trimmed text
 */
fun String.trim(sizeInBytes: Int): String {
    val inputBytes = toByteArray(StandardCharsets.UTF_8)
    val outputBytes = ByteArray(sizeInBytes)

    System.arraycopy(inputBytes, 0, outputBytes, 0, sizeInBytes)
    val result = String(outputBytes, StandardCharsets.UTF_8)

    // check if last character is truncated
    val lastIndex = result.length - 1

    return if (lastIndex > 0 && result[lastIndex] != this[lastIndex]) {
        // last character is truncated so remove the last character
        result.substring(0, lastIndex)
    } else result
}