package com.example.chartexplorer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager.widget.ViewPager
import com.example.chartexplorer.databinding.ActivityMainBinding
import com.example.chartexplorer.swipe.ScreenSlidePagerAdapter
import com.example.chartexplorer.swipe.fragment.SwipeFragmentDirections
import com.example.chartexplorer.ui.home.HomeFragmentDirections


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var swipeClick = false
        var homeClick = false

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = findViewById(R.id.Pager)
        viewPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)

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

        binding.navigationRail.setOnItemReselectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.swipe -> {
                    if (!swipeClick) {
                        val action = HomeFragmentDirections.actionHomeFragmentToSwipeFragment2()
                        navController.navigate(action)
                        homeClick = false
                        swipeClick = true
                    }
                    /*removeFragment(HomeFragment())
                    addFragment(R.id.main_content, SwipeFragment())
                    replaceFragment(R.id.main_content, SwipeFragment())*/
                    swipeClick = true
                }
                R.id.home -> {
                    if (!homeClick) {
                        val action = SwipeFragmentDirections.actionSwipeFragmentToHomeFragment2()
                        navController.navigate(action)
                        homeClick = true
                        swipeClick = false
                    }
                    /*removeFragment(SwipeFragment())
                    addFragment(R.id.main_content, HomeFragment())
                    replaceFragment(R.id.main_content, HomeFragment())*/
                }
                else -> false
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_content)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}