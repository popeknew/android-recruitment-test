package dog.snow.androidrecruittest.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.ALBUMS
import dog.snow.androidrecruittest.PHOTOS
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.USERS
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import kotlinx.android.synthetic.main.splash_activity.progress_bar
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity(R.layout.splash_activity) {

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

        with(viewModel) {
            allPhotos.observe(this@SplashActivity, photosObserver)
            allAlbums.observe(this@SplashActivity, albumsObserver)
            allUsers.observe(this@SplashActivity, usersObserver)
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
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
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> /*tryAgain()*/ }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }
}