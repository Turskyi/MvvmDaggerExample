package io.github.turskyi.mvvmdaggerexample.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.github.turskyi.mvvmdaggerexample.R
import io.github.turskyi.mvvmdaggerexample.databinding.ActivityMainBinding
import io.github.turskyi.mvvmdaggerexample.injection.ViewModelFactory

//TODO: 1
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PostListViewModel
    private lateinit var binding: ActivityMainBinding
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity = this))
            .get(PostListViewModel::class.java)
        binding.rvPostList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvPostList.setHasFixedSize(true)
        binding.viewModel = viewModel
        viewModel.errorMessage.observe(this, { message ->
            if (message != null) {
                showError(message)
            } else {
                hideError()
            }
        })
    }

    private fun showError(@StringRes errorMessage: Int) {
        snackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry, viewModel.errorClickListener)
        snackbar!!.show()
    }

    private fun hideError() {
        snackbar?.dismiss()
    }
}
