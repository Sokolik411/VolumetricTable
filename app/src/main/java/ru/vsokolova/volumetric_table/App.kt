package ru.vsokolova.volumetric_table

import android.app.Application
import ru.vsokolova.volumetric_table.di.AppComponent
import ru.vsokolova.volumetric_table.di.DaggerAppComponent

class App :Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}