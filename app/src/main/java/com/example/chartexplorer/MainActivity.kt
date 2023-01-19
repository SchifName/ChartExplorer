package com.example.chartexplorer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager.widget.ViewPager
import com.example.chartexplorer.databinding.ActivityMainBinding
import com.example.chartexplorer.swipe.ScreenSlidePagerAdapter
import com.example.chartexplorer.swipe.fragment.SwipeFragment
import com.example.chartexplorer.ui.home.HomeFragment
import com.example.chartexplorer.ui.home.HomeFragmentDirections
import com.google.android.material.navigationrail.NavigationRailView
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = findViewById(R.id.Pager)
        viewPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.main_content)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        /*var badge = findViewById<NavigationRailView>(R.id.navigation_rail).getOrCreateBadge(R.id.swipe)
        badge.isVisible = true

//      In this case, the number 99 will be displayed in the badge
        badge = findViewById<NavigationRailView>(R.id.navigation_rail).getOrCreateBadge(R.id.home)
        badge.isVisible = true
        badge.number = 99*/

        fun FragmentManager.doTransaction(func: FragmentTransaction.() ->
        FragmentTransaction) {
            beginTransaction().func().commit()
        }

        fun AppCompatActivity.addFragment(frameId: Int, fragment: Fragment){
            supportFragmentManager.doTransaction { add(frameId, fragment) }
        }

        fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment) {
            supportFragmentManager.doTransaction{replace(frameId, fragment)}
        }

        fun AppCompatActivity.removeFragment(fragment: Fragment) {
            supportFragmentManager.doTransaction{remove(fragment)}
        }

        binding.navigationRail.setOnItemReselectedListener {  menuItem ->
            when (menuItem.itemId) {
                R.id.swipe -> {
                    addFragment(R.id.main_content, SwipeFragment())
                    replaceFragment(R.id.main_content, SwipeFragment())
                    removeFragment(HomeFragment())
                    true
                }
                R.id.home -> {
                    addFragment(R.id.main_content, HomeFragment())
                    replaceFragment(R.id.main_content, HomeFragment())
                    removeFragment(SwipeFragment())
                    true
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_content)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}