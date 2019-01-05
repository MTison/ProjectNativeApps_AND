package com.example.matthiastison.emotionsapplication.Injection

import com.example.matthiastison.emotionsapplication.App
import com.example.matthiastison.emotionsapplication.ViewModels.SubjectViewModel
import com.example.matthiastison.emotionsapplication.ViewModels.ThemeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    fun inject(app: App)
    fun inject(subjectViewModel: SubjectViewModel)
    fun inject(themeViewModel: ThemeViewModel)
}