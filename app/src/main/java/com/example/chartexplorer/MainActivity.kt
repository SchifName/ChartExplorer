package com.example.chartexplorer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.chartexplorer.databinding.ActivityMainBinding
import com.example.chartexplorer.swipe.fragment.SwipeFragmentDirections
import com.example.chartexplorer.ui.home.HomeFragmentDirections


class MainActivity : AppCompatActivity() {

    private var swipeMenu = false
    private var homeMenu = true
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    if (swipeMenu) {
                        val action = HomeFragmentDirections.actionHomeFragmentToSwipeFragment2()
                        navController.navigate(action)
                    }
                    /*removeFragment(HomeFragment())
                    addFragment(R.id.main_content, SwipeFragment())
                    replaceFragment(R.id.main_content, SwipeFragment())*/
                }
                R.id.home -> {
                    if (homeMenu) {
                        val action = SwipeFragmentDirections.actionSwipeFragmentToHomeFragment2()
                        navController.navigate(action)
                    }
                    /*removeFragment(SwipeFragment())
                    addFragment(R.id.main_content, HomeFragment())
                    replaceFragment(R.id.main_content, HomeFragment())*/
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.pieChartFragment || nd.id == R.id.barChartFragment) {
                //if (navView.visibility == View.GONE) railView?.visibility = View.GONE
                //else navView.visibility = View.GONE
                binding.navigationRail.visibility = View.GONE
            } else {
                binding.navigationRail.visibility = View.VISIBLE
                if (nd.id == R.id.homeFragment) {
                    //if (navView.visibility == View.GONE) railView?.visibility = View.GONE
                    //else navView.visibility = View.GONE
                    swipeMenu = true
                    homeMenu = false
                } else {
                    swipeMenu = false
                    homeMenu = true
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_content)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}