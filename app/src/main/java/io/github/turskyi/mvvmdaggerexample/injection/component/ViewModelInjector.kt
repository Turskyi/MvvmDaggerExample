package io.github.turskyi.mvvmdaggerexample.injection.component

import io.github.turskyi.mvvmdaggerexample.injection.module.NetworkModule
import io.github.turskyi.mvvmdaggerexample.ui.PostListViewModel
import dagger.Component
import javax.inject.Singleton

//TODO: 6
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}