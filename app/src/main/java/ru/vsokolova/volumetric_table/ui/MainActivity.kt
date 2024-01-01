package ru.vsokolova.volumetric_table.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ru.vsokolova.volumetric_table.App
import ru.vsokolova.volumetric_table.Dependencies
import ru.vsokolova.volumetric_table.R
import ru.vsokolova.volumetric_table.databinding.ActivityMainBinding
import ru.vsokolova.volumetric_table.di.DaggerMainActivityComponent
import ru.vsokolova.volumetric_table.di.MainActivityComponent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var mainActivityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        Dependencies.init(applicationContext)
        super.onCreate(savedInstanceState)

        val applicationComponent = (application as App).getAppComponent()
        mainActivityComponent = DaggerMainActivityComponent
            .factory()
            .create(this, applicationComponent)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_volumetrc_table, R.id.navigation_density, R.id.navigation_gost
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}