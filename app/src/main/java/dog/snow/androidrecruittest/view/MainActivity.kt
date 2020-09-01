package dog.snow.androidrecruittest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dog.snow.androidrecruittest.R

class MainActivity : AppCompatActivity(R.layout.main_activity){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))

        val photos = intent.getParcelableArrayExtra("photos")
        val albums = intent.getParcelableArrayExtra("albums")
        val users = intent.getParcelableArrayExtra("users")

       // val navHostFragment = nav_host_fragment.findNavController().navInflater
/*//        val inflater = navHostFragment.navController.navInflater*/

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

//        val navController = findNavController(R.id.nav_host_fragment)
        val graph = navController.navInflater.inflate(R.navigation.nav_graph)



        val bundle = Bundle()
        bundle.putParcelableArray("photos", photos)
        bundle.putParcelableArray("albums", albums)
        bundle.putParcelableArray("users", users)

        navController.setGraph(R.navigation.nav_graph, bundle)

    }
}