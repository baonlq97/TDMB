package com.brandon.tmdb.di

import com.brandon.tmdb.BuildConfig
import com.brandon.tmdb.data.api.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val READ_TIME_OUT = 30L

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
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
    @Singleton
    fun provideMovieApiService(): MovieApiService =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)

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