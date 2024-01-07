import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import ru.vsokolova.volumetric_table.di.ViewModelFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
class CoreModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(creators)
    }
}