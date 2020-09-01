package dog.snow.androidrecruittest.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.repository.AlbumRepository
import dog.snow.androidrecruittest.view.SplashViewModel
import dog.snow.androidrecruittest.repository.PhotoRepository
import dog.snow.androidrecruittest.repository.UserRepository
import dog.snow.androidrecruittest.repository.service.AlbumService
import dog.snow.androidrecruittest.repository.service.PhotoService
import dog.snow.androidrecruittest.repository.service.UserService
import dog.snow.androidrecruittest.ui.ListViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

fun getNetModule(context: Context) = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), context) }
    single { providePhotoService(get()) }
    single { provideAlbumService(get()) }
    single { provideUserService(get()) }
}

val repoModule = module {
    single { PhotoRepository(get()) }
    single { AlbumRepository(get()) }
    single { UserRepository(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get(), get(), get()) }
    viewModel { ListViewModel() }
}

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient().newBuilder().build()

private fun provideRetrofit(okHttpClient: OkHttpClient, context: Context): Retrofit =
    Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl(context.getString(R.string.base_url))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun providePhotoService(retrofit: Retrofit): PhotoService =
    retrofit.create(PhotoService::class.java)

private fun provideAlbumService(retrofit: Retrofit): AlbumService =
    retrofit.create(AlbumService::class.java)

private fun provideUserService(retrofit: Retrofit): UserService =
    retrofit.create(UserService::class.java)