package com.brandon.tmdb.di

import android.content.Context
import androidx.work.WorkManager
import com.brandon.tmdb.BuildConfig
import com.brandon.tmdb.data.api.MovieApiService
import com.brandon.tmdb.data.repository.FavoriteMovieRepositoryImpl
import com.brandon.tmdb.domain.repository.FavoriteMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val READ_TIME_OUT = 30L

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun providesNetworkJson(): Json =
        Json {
            ignoreUnknownKeys = true
        }

    @Provides
    fun provideOkHttpClient() =
        OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url

                val url =
                    originalHttpUrl
                        .newBuilder()
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
                        .build()

                val request =
                    original
                        .newBuilder()
                        .url(url)
                        .build()

                chain.proceed(request)
            }.apply {
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
        json: Json,
        baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType()),
            ).build()

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

    @Provides
    fun provideWorkManager(
        @ApplicationContext context: Context,
    ): WorkManager = WorkManager.getInstance(context)
}
