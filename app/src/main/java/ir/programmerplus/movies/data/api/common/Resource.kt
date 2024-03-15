package ir.programmerplus.movies.data.api.common

sealed class Resource<out T> {

    data class Success<out T>(val response: T) : Resource<T>()

    data class Error(
        val isNetworkError: Boolean,
        val errorCode: Int? = null,
        val errorBody: String? = null
    ) : Resource<Nothing>()

}