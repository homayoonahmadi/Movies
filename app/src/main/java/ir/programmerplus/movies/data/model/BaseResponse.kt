package ir.programmerplus.movies.data.model

class BaseResponse<T>(
    val results: T? = null,
    val page: Int? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null
)