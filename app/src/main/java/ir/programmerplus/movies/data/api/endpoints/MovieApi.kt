package ir.programmerplus.movies.data.api.endpoints

import ir.programmerplus.movies.data.model.BaseResponse
import ir.programmerplus.movies.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
    ): BaseResponse<List<Movie>>

}