package ru.vsokolova.volumetric_table.di.density

import dagger.Component
import ru.vsokolova.volumetric_table.di.MainActivityComponent
import ru.vsokolova.volumetric_table.ui.density.DensityFragment
import ru.vsokolova.volumetric_table.ui.density.DensityViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [DensityDataModule::class],
    dependencies = [MainActivityComponent::class])
interface DensityFragmentComponent {

    fun inject(fragmentDensity: DensityFragment)

    fun provideDensityViewModel(): DensityViewModel

    companion object {
        fun create(mainActivityComponent: MainActivityComponent): DensityFragmentComponent {
            return DaggerDensityFragmentComponent.builder()
                .mainActivityComponent(mainActivityComponent)
                .build()
        }
    }
}