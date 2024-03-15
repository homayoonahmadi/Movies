package ir.programmerplus.movies.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.programmerplus.movies.R
import ir.programmerplus.movies.data.api.common.Resource
import ir.programmerplus.movies.databinding.FragmentHomeBinding
import ir.programmerplus.movies.ui.adapters.MovieAdapter
import ir.programmerplus.movies.ui.viewmodel.HomeViewModel
import ir.programmerplus.movies.utils.hideShimmer
import ir.programmerplus.movies.utils.observeOnce
import ir.programmerplus.movies.utils.setVisible
import ir.programmerplus.movies.utils.showShimmer
import ir.programmerplus.movies.utils.snackBar

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val binding: FragmentHomeBinding by viewBinding()

    private val movieAdapter by lazy { MovieAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        fetchMovies()
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        adapter = movieAdapter
        layoutManager = LinearLayoutManager(context)
        movieAdapter.onLoadMoreListener = { fetchMovies() }
    }

    private fun fetchMovies() = with(binding) {
        setProgressVisible(true)

        viewModel.getMovies().observeOnce { resource ->
            setProgressVisible(false)

            when (resource) {
                is Resource.Success -> resource.response.results?.let { movieAdapter.appendMovies(it) }
                is Resource.Error -> root.snackBar(getString(R.string.error_in_loading_movies))
            }
        }
    }

    private fun setProgressVisible(isVisible: Boolean) = with(binding) {
        progress.setVisible(isVisible)
        when {
            isVisible && movieAdapter.itemCount == 0 -> recyclerView.showShimmer()
            !isVisible -> recyclerView.hideShimmer()
        }
    }

}