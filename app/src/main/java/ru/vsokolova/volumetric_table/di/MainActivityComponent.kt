package ru.vsokolova.volumetric_table.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [AppComponent::class])
interface MainActivityComponent {

    @Component.Factory
    interface ComponentFactory {
        fun create (
            @BindsInstance context: Context,
            appComponent: AppComponent
        ): MainActivityComponent
    }

    fun provideAppContext(): Context

}