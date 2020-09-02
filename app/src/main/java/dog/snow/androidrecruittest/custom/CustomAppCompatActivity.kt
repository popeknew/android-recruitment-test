package dog.snow.androidrecruittest.custom

import androidx.appcompat.app.AppCompatActivity
import com.androidstudy.networkmanager.Monitor
import com.androidstudy.networkmanager.Tovuti

open class CustomAppCompatActivity : AppCompatActivity() {

    fun checkInternetConnection(listener: Monitor.ConnectivityListener) {
        Tovuti.from(this).monitor(listener)
    }

    override fun onStop() {
        Tovuti.from(this).stop()
        super.onStop()
    }
}