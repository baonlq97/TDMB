package com.brandon.tmdb.di

import com.brandon.tmdb.BuildConfig
import com.brandon.tmdb.data.api.MovieApiService
import com.brandon.tmdb.data.repository.FavoriteMovieRepositoryImpl
import com.brandon.tmdb.domain.repository.FavoriteMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val READ_TIME_OUT = 30L

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl() = "https://api.themoviedb.org/3/"

    @Provides
    fun provideOkHttpClient() =
        OkHttpClient
            .Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        },
                    )
                    readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                }
            }.build()

    @Provides
    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService = retrofit.create(MovieApiService::class.java)

    @Provides
    fun provideFavoriteMovieRepository(apiService: MovieApiService): FavoriteMovieRepository = FavoriteMovieRepositoryImpl(apiService)

//    @Provides
//    @Singleton
//    fun provideDatabase(
//        @ApplicationContext context: Context,
//    ): MovieDatabase =
//        Room
//            .databaseBuilder(
//                context,
//                MovieDatabase::class.java,
//                "movies.db",
//            ).fallbackToDestructiveMigration()
//            .build()

//    @Provides
//    fun provideMovieDao(database: MovieDatabase) = database.movieDao()

//    @Provides
//    fun provideWorkManager(
//        @ApplicationContext context: Context,
//    ): WorkManager = WorkManager.getInstance(context)
}
