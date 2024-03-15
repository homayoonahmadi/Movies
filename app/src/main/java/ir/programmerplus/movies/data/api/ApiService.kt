package ir.programmerplus.movies.data.api

import android.util.Log
import com.google.gson.Gson
import ir.programmerplus.movies.data.api.common.TokenInterceptor
import ir.programmerplus.movies.utils.BASE_URL
import ir.programmerplus.movies.utils.log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ApiService @Inject constructor(
    private val gson: Gson,
    private val tokenInterceptor: TokenInterceptor,
) {

    companion object {
        @JvmStatic
        private val TAG = ApiService::class.java.simpleName
    }

    /**
     * Builds an API object using Retrofit.
     * @param api The class of the API to be built.
     * @return The built API object.
     */
    fun <Api> buildApi(api: Class<Api>): Api {
        val okHttpClient = getRetrofitClient()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(api)
    }

    /**
     * This method creates a new instance of [OkHttpClient] and if the build type is debug,
     * adds a logging interceptor for detailed network logging.
     *
     * @return a new instance of [OkHttpClient]
     */
    private fun getRetrofitClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(getLoggingInterceptor())

        return builder.build()
    }

    /**
     * Returns an instance of HttpLoggingInterceptor with a custom logging implementation.
     * The logging level is set to BODY, and all request and response data will be logged.
     * It also checks for the presence of certain chars which are commonly used in file content.
     *
     * @return an instance of HttpLoggingInterceptor with a custom logging implementation
     */
    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { content: String ->
            log(Log.DEBUG, TAG, content)

        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

}