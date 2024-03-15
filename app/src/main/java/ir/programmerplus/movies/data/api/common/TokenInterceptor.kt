package ir.programmerplus.movies.data.api.common

import ir.programmerplus.movies.utils.ACCEPT
import ir.programmerplus.movies.utils.APPLICATION_JSON
import ir.programmerplus.movies.utils.AUTHORIZATION
import ir.programmerplus.movies.utils.BEARER
import ir.programmerplus.movies.utils.TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


/**
 * Interceptor responsible for adding authentication headers, including the bearer token,
 * to the outgoing HTTP requests. This ensures that requests are properly authenticated
 * and include the necessary headers for secure communication.
 */
class TokenInterceptor @Inject constructor() : Interceptor {

    /**
     * Intercepts a request and adds the necessary headers for authentication and content type.
     * @param chain The Interceptor.Chain object containing the request to be intercepted.
     * @return The intercepted Response object.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestBuilder = originalRequest.newBuilder()
            .header(ACCEPT, APPLICATION_JSON)
            .header(AUTHORIZATION, "$BEARER $TOKEN")

        return chain.proceed(requestBuilder.build())
    }
}