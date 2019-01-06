package com.example.matthiastison.emotionsapplication.Injection

import com.example.matthiastison.emotionsapplication.ViewModels.UnsplashViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
/**
 * All modules that are required to perform the injections into the listed objects should be listed
 * in this annotation
 */
@Component(modules = [NetworkModule::class])
interface ViewModelComponent {

    /**
     * Injects the dependencies into the specified UnsplashViewModel.
     * @param unsplashViewModel the [UnsplashViewModel] in which to inject the dependencies.
     */
    fun inject(unsplashViewModel: UnsplashViewModel)
}