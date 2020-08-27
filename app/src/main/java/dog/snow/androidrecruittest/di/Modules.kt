package dog.snow.androidrecruittest.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dog.snow.androidrecruittest.repository.AlbumRepository
import dog.snow.androidrecruittest.view.SplashViewModel
import dog.snow.androidrecruittest.repository.PhotoRepository
import dog.snow.androidrecruittest.repository.service.AlbumService
import dog.snow.androidrecruittest.repository.service.PhotoService
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

val netModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { providePhotoService(get()) }
    single { provideAlbumService(get()) }
}

val repoModule = module {
    single { PhotoRepository(get()) }
    single { AlbumRepository(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get(), get()) }
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

private fun provideAlbumService(retrofit: Retrofit): AlbumService =
    retrofit.create(AlbumService::class.java)