package dog.snow.androidrecruittest.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.vm.SplashViewModel
import dog.snow.androidrecruittest.repo.Repository
import dog.snow.androidrecruittest.net.RestApiService
import dog.snow.androidrecruittest.vm.ListViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

fun getNetModule(context: Context) = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), context) }
    single { providePhotoService(get()) }
}

val repoModule = module {
    single { Repository(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
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

private fun providePhotoService(retrofit: Retrofit): RestApiService =
    retrofit.create(RestApiService::class.java)