package com.example.chartexplorer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager.widget.ViewPager
import com.example.chartexplorer.databinding.ActivityMainBinding
import com.example.chartexplorer.swipe.ScreenSlidePagerAdapter
import com.example.chartexplorer.swipe.fragment.SwipeFragment
import com.example.chartexplorer.swipe.fragment.SwipeFragmentDirections
import com.example.chartexplorer.ui.home.HomeFragment
import com.example.chartexplorer.ui.home.HomeFragmentDirections
import com.example.chartexplorer.utils.addFragment
import com.example.chartexplorer.utils.refresh
import com.example.chartexplorer.utils.removeFragment
import com.example.chartexplorer.utils.replaceFragment


class MainActivity : AppCompatActivity() {

    private var swipeClick = true
    private var backClick = 0
    private var homeClick = false
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
                    if (!swipeClick || backClick == 1) {
                        val action = HomeFragmentDirections.actionHomeFragmentToSwipeFragment2()
                        navController.navigate(action)
                        homeClick = false
                        swipeClick = true
                    }
                    backClick = 0
                    /*removeFragment(HomeFragment())
                    addFragment(R.id.main_content, SwipeFragment())
                    replaceFragment(R.id.main_content, SwipeFragment())*/
                }
                R.id.home -> {
                    if (!homeClick) {
                        if (backClick != 1){
                            val action = SwipeFragmentDirections.actionSwipeFragmentToHomeFragment2()
                            navController.navigate(action)
                            homeClick = true
                            swipeClick = false
                        }
                    }
                    /*removeFragment(SwipeFragment())
                    addFragment(R.id.main_content, HomeFragment())
                    replaceFragment(R.id.main_content, HomeFragment())*/
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener{ _, nd: NavDestination, _->
            if(nd.id == R.id.pieChartFragment || nd.id == R.id.barChartFragment){
                //if (navView.visibility == View.GONE) railView?.visibility = View.GONE
                //else navView.visibility = View.GONE
                binding.navigationRail?.visibility = View.GONE
                backClick = 1
            }else{
                binding.navigationRail?.visibility = View.VISIBLE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        swipeClick = true
        homeClick = false
        val navController = findNavController(R.id.main_content)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}