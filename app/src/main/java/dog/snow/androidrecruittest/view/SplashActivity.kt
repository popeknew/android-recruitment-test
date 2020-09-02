package dog.snow.androidrecruittest.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.androidstudy.networkmanager.Monitor
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.ALBUMS
import dog.snow.androidrecruittest.PHOTOS
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.USERS
import dog.snow.androidrecruittest.ext.CustomAppCompatActivity
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import kotlinx.android.synthetic.main.splash_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : CustomAppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    private val photosObserver = Observer<List<RawPhoto>> { photos ->
        viewModel.getAlbums(photos)
    }
    private val albumsObserver = Observer<List<RawAlbum>> { albums ->
        viewModel.getUsers(albums)
    }
    private val usersObserver = Observer<List<RawUser>> { users ->
        if (users.isNotEmpty()) {
            progress_bar.visibility = View.GONE
            startMainActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        with(viewModel) {
            allPhotos.observe(this@SplashActivity, photosObserver)
            allAlbums.observe(this@SplashActivity, albumsObserver)
            allUsers.observe(this@SplashActivity, usersObserver)
        }

        checkInternetConnection(netConnectionChecker)
    }

    private val netConnectionChecker =
        Monitor.ConnectivityListener { _, isConnected, _ ->
            if (isConnected) {
                viewModel.getAllPhotos()
                offline_banner.visibility = View.GONE
            } else {
                showError("nie ma neta panie")
                offline_banner.visibility = View.VISIBLE
            }
        }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(PHOTOS, viewModel.allPhotos.value?.toTypedArray())
            putExtra(ALBUMS, viewModel.allAlbums.value?.toTypedArray())
            putExtra(USERS, viewModel.allUsers.value?.toTypedArray())
        }
        startActivity(intent)
    }

    private fun showError(errorMessage: String?) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message, errorMessage))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ ->
                checkInternetConnection(
                    netConnectionChecker
                )
            }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }
}