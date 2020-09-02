package dog.snow.androidrecruittest.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.androidstudy.networkmanager.Monitor
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ext.CustomAppCompatActivity

class MainActivity : CustomAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        setContentView(R.layout.main_activity)

        checkInternetConnection(netConnectionChecker)
        putArgumentsToNavComp()
    }

    private val netConnectionChecker =
        Monitor.ConnectivityListener { _, isConnected, _ ->
            if (isConnected) {

            } else {

            }
        }

    private fun putArgumentsToNavComp() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.setGraph(R.navigation.nav_graph, intent.extras)

    }
}