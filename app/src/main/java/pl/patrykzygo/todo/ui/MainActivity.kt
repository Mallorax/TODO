package pl.patrykzygo.todo.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController


import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val drawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout,
            R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        setupNavigation()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navigateUp(navController, binding.drawerLayout)
    }

    private fun setupNavigation(){
        val navController = findNavController(R.id.nav_host_fragment)
        setSupportActionBar(binding.appToolbar)
        setupActionBarWithNavController(navController, binding.drawerLayout)
        binding.navView.setupWithNavController(navController)

    }


}
