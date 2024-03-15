package ir.programmerplus.movies.ui.viewmodel

import android.app.Application
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.programmerplus.movies.data.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val movieRepository: MovieRepository
) : BaseViewModel(application) {

    private var currentPage = 1

    fun getMovies() = movieRepository.getMovies(currentPage++).asLiveData()

}