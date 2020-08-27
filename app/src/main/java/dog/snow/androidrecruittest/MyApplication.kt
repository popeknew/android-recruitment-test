package dog.snow.androidrecruittest

import android.app.Application
import dog.snow.androidrecruittest.di.netModule
import dog.snow.androidrecruittest.di.repoModule
import dog.snow.androidrecruittest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(netModule, viewModelModule, repoModule)
            )
        }
    }
}