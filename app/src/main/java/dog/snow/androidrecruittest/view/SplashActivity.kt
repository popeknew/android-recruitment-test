package dog.snow.androidrecruittest.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import kotlinx.android.synthetic.main.splash_activity.*
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity(R.layout.splash_activity) {

    private val viewModel by inject<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.allPhotos.observe(this, Observer<List<RawPhoto>> { photos ->
            viewModel.getAlbums(photos)
            Log.d("TAGGG", "$photos")
        })

        viewModel.allAlbums.observe(this, Observer<List<RawAlbum>> { albums ->
            viewModel.getUsers(albums)
            Log.d("TAGGG", "$albums")
        })

        viewModel.allUsers.observe(this, Observer<List<RawUser>> { users ->
            Log.d("TAGGG", "$users")
        })

        iv_logo_sd_symbol.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showError(errorMessage: String?) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message, errorMessage))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> /*tryAgain()*/ }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }
}