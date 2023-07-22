package com.example.albertheijnsampleapp.di

import android.util.Log
import com.example.albertheijnsampleapp.data.repository.detail.BreedDetailRepository
import com.example.albertheijnsampleapp.data.repository.detail.BreedDetailRepositoryImpl
import com.example.albertheijnsampleapp.data.repository.list.BreedRepository
import com.example.albertheijnsampleapp.data.repository.list.BreedRepositoryImpl
import com.example.albertheijnsampleapp.data.source.BreedPagingSource
import com.example.albertheijnsampleapp.data.source.DataSource
import com.example.albertheijnsampleapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides dependencies for the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    /**
     * Provides an instance of the OkHttpClient.
     *
     * @param headerInterceptor The [Interceptor] for adding headers to the requests.
     * @param loggingInterceptor The [HttpLoggingInterceptor] for logging network requests.
     * @return An instance of [OkHttpClient].
     */
    @Singleton
    @Provides
    fun provideClient(
        headerInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Provides the API key used for network requests.
     *
     * @return The API key as a string.
     */
    @Singleton
    @Provides
    @Named("api_key")
    external fun getKey(): String

    /**
     * Provides an instance of the header interceptor.
     *
     * @return An instance of [Interceptor].
     */
    @Singleton
    @Provides
    fun provideHeaderInterceptor(@Named("api_key") apiKey: String): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()

            // Add header to the original request
            val modifiedRequest = originalRequest.newBuilder()
                .addHeader(
                    "x-api-key",
                    apiKey
                    //BuildConfig.ApiKey
                )
                .build()

            chain.proceed(modifiedRequest)
        }
    }

    /**
     * Provides an instance of the logging interceptor.
     *
     * @return An instance of [HttpLoggingInterceptor].
     */
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Log.d("API_LOG", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provides an instance of the Retrofit client.
     *
     * @param client The [OkHttpClient] for making network requests.
     * @return An instance of [Retrofit].
     */
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides an instance of the [DataSource] interface.
     *
     * @param retrofit The [Retrofit] dependency.
     * @return An instance of [DataSource].
     */
    @Singleton
    @Provides
    fun provideDataSource(retrofit: Retrofit): DataSource {
        return retrofit.create(DataSource::class.java)
    }

    /**
     * Provides an instance of the [BreedRepository] interface.
     *
     * @param breedPagingSource The [BreedPagingSource] dependency.
     * @return An instance of [BreedRepository].
     */
    @Singleton
    @Provides
    fun providesBreedRepository(
        breedPagingSource: BreedPagingSource
    ): BreedRepository {
        return BreedRepositoryImpl(breedPagingSource)
    }

    /**
     * Provides an instance of the [BreedDetailRepository] interface.
     *
     * @param dataSource The [DataSource] dependency.
     * @return An instance of [BreedDetailRepository].
     */
    @Singleton
    @Provides
    fun providesBreedDetailRepository(dataSource: DataSource): BreedDetailRepository {
        return BreedDetailRepositoryImpl(dataSource)
    }
}
