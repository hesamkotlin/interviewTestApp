package com.example.interviewtestapp.di

import androidx.viewbinding.BuildConfig
import com.example.interviewtestapp.constants
import com.example.interviewtestapp.data.remote.BasicAuthInterceptor
import com.example.interviewtestapp.data.remote.NetworkDataSource
import com.example.interviewtestapp.data.remote.WebService
import com.example.interviewtestapp.domain.UserRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideApiService(
    ): WebService {
        val gson = GsonBuilder().setLenient().create()


        val httplogger = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httplogger)
            .addInterceptor(BasicAuthInterceptor(constants.USERNAME, constants.PASSWORD))
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WebService::class.java)

    }

    @Provides
    fun provideRepository(networkDataSource: NetworkDataSource) = UserRepository(networkDataSource)

}
