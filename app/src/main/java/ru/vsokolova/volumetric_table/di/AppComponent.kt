package ru.vsokolova.volumetric_table.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component
interface AppComponent {

    @Component.Factory
    interface ComponentFactory {
        fun create (@BindsInstance context: Context): AppComponent
    }
}
