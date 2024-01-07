package ru.vsokolova.volumetric_table.di

import dagger.Component
import ru.vsokolova.volumetric_table.ui.DensityFragment
import ru.vsokolova.volumetric_table.ui.DensityViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [DensityDataModule::class],
    dependencies = [AppProvider::class])
interface DensityFragmentComponent {

    fun inject(fragmentDensity: DensityFragment)

    fun provideDensityViewModel(): DensityViewModel

    companion object {
        fun create(appProvider: AppProvider): DensityFragmentComponent {
            return DaggerDensityFragmentComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}