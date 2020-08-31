package dog.snow.androidrecruittest.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.repository.model.RawPhoto
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(R.layout.main_activity){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))

        val intencik = intent.getParcelableArrayExtra("photos")

        val navHostFragment = nav_host_fragment.findNavController().navInflater
/*//        val inflater = navHostFragment.navController.navInflater*/
        val graph = navHostFragment.inflate(R.navigation.nav_graph)

        val bundle = Bundle()
        bundle.putParcelableArray("bundle", intencik)

        nav_host_fragment.findNavController().setGraph(graph, bundle)

    }
}