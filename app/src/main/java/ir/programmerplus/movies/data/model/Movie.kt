package ir.programmerplus.movies.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
	val id: Int? = null,
	val adult: Boolean? = null,
	val overview: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val popularity: Any? = null,

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("original_title")
	val originalTitle: String? = null,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int?>? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Any? = null,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null
)
