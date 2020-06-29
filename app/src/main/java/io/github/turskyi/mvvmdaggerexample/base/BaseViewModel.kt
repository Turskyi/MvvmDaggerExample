package io.github.turskyi.mvvmdaggerexample.base

import androidx.lifecycle.ViewModel
import io.github.turskyi.mvvmdaggerexample.injection.component.DaggerViewModelInjector
import io.github.turskyi.mvvmdaggerexample.injection.module.NetworkModule
import io.github.turskyi.mvvmdaggerexample.ui.PostListViewModel
import io.github.turskyi.mvvmdaggerexample.injection.component.ViewModelInjector

//TODO: 7
abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector.builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this){is PostListViewModel -> injector.inject(this)}
    }
}