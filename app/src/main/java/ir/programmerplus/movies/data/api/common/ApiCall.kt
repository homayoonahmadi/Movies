package ir.programmerplus.movies.data.api.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class ApiCall {

    /**
     * Execute the given API call using a suspending function.
     *
     * @param api A suspending function that returns the result of the API call.
     * @return A [Resource] object that represents the result of the API call.
     */
    suspend fun <T> callApi(api: suspend () -> T): Resource<T> {
        return try {
            withContext(Dispatchers.IO) { Resource.Success(api.invoke()) }

        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(false, e.code(), e.response()?.errorBody()?.string())

        } catch (t: Throwable) {
            t.printStackTrace()
            Resource.Error(true)
        }
    }
}