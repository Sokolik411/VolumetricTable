package ru.vsokolova.volumetric_table

import android.app.Application
import ru.vsokolova.volumetric_table.di.AppComponent
import ru.vsokolova.volumetric_table.di.AppProvider
import ru.vsokolova.volumetric_table.di.AppWithProvider
import ru.vsokolova.volumetric_table.di.DaggerAppComponent

class App :Application(), AppWithProvider {

    override fun getAppProvider(): AppProvider {
        return appProvider?: DaggerAppComponent.builder().application(this).build().also {
            appProvider = it
        }
    }

    override fun onCreate() {
        super.onCreate()
        getAppProvider()
    }

    companion object {
        private var appProvider: AppProvider? = null
    }
}