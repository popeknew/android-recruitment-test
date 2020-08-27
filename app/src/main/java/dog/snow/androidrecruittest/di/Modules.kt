package dog.snow.androidrecruittest.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dog.snow.androidrecruittest.repository.service.PhotoService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

val netModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { providePhotoService(get()) }
}

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient().newBuilder().build()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun providePhotoService(retrofit: Retrofit): PhotoService =
    retrofit.create(PhotoService::class.java)