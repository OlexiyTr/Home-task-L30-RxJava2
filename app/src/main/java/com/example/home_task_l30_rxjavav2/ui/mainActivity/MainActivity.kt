package com.example.home_task_l30_rxjavav2.ui.mainActivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.home_task_l30_rxjavav2.App
import com.example.home_task_l30_rxjavav2.R
import com.example.home_task_l30_rxjavav2.databinding.ActivityMainBinding
import com.example.home_task_l30_rxjavav2.ui.showPostsScreen.PostsScreenFragment
import com.example.home_task_l30_rxjavav2.tools.UpdatingState
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDi()
        observeErrors()
        setupBinding()

        viewModel.updateRepo()
        supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment, PostsScreenFragment.newInstance())
            .commit()
    }

    private fun setupDi() {
        val app = this.application as App
        app.getComponent().inject(this)
    }

    private fun observeErrors() {
        viewModel.errorLiveData.observe(this) {
            it?.let {
                when (it) {
                    UpdatingState.ERROR -> showError()
                    UpdatingState.COMPLETED -> showSuccess()
                    UpdatingState.LOADING -> showLoading()
                }
            }
        }
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun showError() {
        Toast.makeText(this, R.string.error_text, Toast.LENGTH_LONG).show()
    }

    private fun showSuccess() {
        Toast.makeText(this, R.string.success_text, Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        Toast.makeText(this, R.string.loading_text, Toast.LENGTH_LONG).show()
    }
}