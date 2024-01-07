package ru.vsokolova.volumetric_table.di

import android.app.Application
import dagger.Component

@Component(dependencies = [AppProvider::class])
interface MainActivityComponent {

    companion object {
        fun create(application: Application): MainActivityComponent {
            return DaggerMainActivityComponent.builder()
                .appProvider(AppComponent.create(application))
                .build()
        }
    }
}