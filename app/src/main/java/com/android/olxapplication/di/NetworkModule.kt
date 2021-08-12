package com.android.olxapplication.di

import com.android.olxapplication.utils.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


    @Singleton
    @Provides
    fun provideHttpClint(
            interceptor: HttpLoggingInterceptor
        ): OkHttpClient =
            OkHttpClient.Builder().also {
                it.addInterceptor(interceptor)
            }.build()


    @VisionaryRetrofitBuild
    @Provides
    @Singleton
    fun provideMainRetrofit(clint: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .client(clint)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()



}


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class VisionaryRetrofitBuild






