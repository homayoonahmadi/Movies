package ir.programmerplus.movies.data.repository

import ir.programmerplus.movies.data.api.endpoints.MovieApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi
) : BaseRepository() {

    fun getMovies(page: Int) = flow {
        val response = callApi {
            movieApi.getMovies(page = page)
        }

        emit(response)
    }

}