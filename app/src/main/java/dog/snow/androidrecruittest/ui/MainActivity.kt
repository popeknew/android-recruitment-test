package dog.snow.androidrecruittest.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.custom.CustomAppCompatActivity

class MainActivity : CustomAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        setContentView(R.layout.main_activity)

        putArgumentsToNavComp()
    }

    private fun putArgumentsToNavComp() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.setGraph(R.navigation.nav_graph, intent.extras)
    }
}