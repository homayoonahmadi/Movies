package ir.programmerplus.movies.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ir.programmerplus.movies.R
import ir.programmerplus.movies.data.model.Movie
import ir.programmerplus.movies.databinding.ItemMovieBinding
import ir.programmerplus.movies.utils.IMAGE_URL

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var isLoading = false
    private var lastPosition = -1
    private var movies = arrayListOf<Movie>()
    var onLoadMoreListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], position)

        if (position == movies.lastIndex && !isLoading) {
            isLoading = true
            onLoadMoreListener?.invoke()
        }
    }

    override fun getItemCount() = movies.size

    fun appendMovies(newList: List<Movie>) {
        isLoading = false
        lastPosition = -1
        movies.addAll(newList)

        notifyItemRangeInserted(movies.size - newList.size, newList.size)
    }

    inner class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, position: Int) = with(itemView.context) {
            binding.movie = movie
            applyAnimation(position)
        }

        /**
         * Applies a slide-in animation to the given item view based on its position in a list.
         *
         * @param position The position of the item in the list.
         */
        private fun Context.applyAnimation(position: Int) {
            if (position > lastPosition) {
                lastPosition = position

                val slideInAnimation = AnimationUtils.loadAnimation(this, R.anim.item_animation_fall_down)
                itemView.startAnimation(slideInAnimation)
            }
        }

    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {
            view.load("$IMAGE_URL$url") {
                crossfade(true)
                placeholder(R.drawable.bg_placeholder)
                error(R.drawable.bg_no_image)
            }
        }
    }
}