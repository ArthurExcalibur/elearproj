package com.excalibur.enjoylearning.jetpack.navigation

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.excalibur.enjoylearning.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_main)
        // fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment ?
        val controller = navHostFragment!!.navController

        //bottom ui
        val bottom: BottomNavigationView? = findViewById(R.id.nav_view)

        //bind
        bottom?.setupWithNavController(controller)
//        NavigationUI.setupWithNavController(bottom!!, controller)
//        bottom?.setOnNavigationItemSelectedListener { menuItem ->
//            // 导航到与menuItem菜单项关联的NavDestination，即与menu.xml中item的id相同的destinationId
//            // destinationId即navigation/nav_graph.xml中fragment的id
//            NavigationUI.onNavDestinationSelected(menuItem, controller)
//        }
    }

}