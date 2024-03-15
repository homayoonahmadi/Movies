package ir.programmerplus.movies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.programmerplus.movies.data.api.ApiService
import ir.programmerplus.movies.data.api.endpoints.MovieApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMovieApi(apiService: ApiService): MovieApi {
        return apiService.buildApi(MovieApi::class.java)
    }

}